package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.metrics.DashboardMetric
import ru.yoomoney.tech.grafana.dsl.metrics.Metrics
import ru.yoomoney.tech.grafana.dsl.metrics.MetricsBuilder
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Repeat
import ru.yoomoney.tech.grafana.dsl.panels.repeat.RepeatBuilder
import ru.yoomoney.tech.grafana.dsl.variables.Variable

/**
 * Builder for Singlestat tab
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
class SingleStatPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    private var repeat: Repeat? = null

    private var timerange = Timerange()

    var metrics: List<DashboardMetric> = mutableListOf()

    var valueMappings: ValueMappings = ValueMappings(ValueToTextType)

    var datasource: Datasource = Zabbix

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    inline fun <reified T : Datasource> metrics(build: MetricsBuilder<T>.() -> Unit) {
        datasource = T::class.objectInstance ?: Zabbix
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics = builder.metrics
    }

    inline fun <reified T : ValueMappingType> valueMappings(build: ValueMappingsBuilder<T>.() -> Unit) {
        val builder = ValueMappingsBuilder<T>()
        builder.build()
        valueMappings = builder.createValueMappings<T>()
    }

    fun timerange(build: TimerangeBuilder.() -> Unit) {
        val builder = TimerangeBuilder()
        builder.build()
        timerange = builder.createTimerange()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            SingleStatPanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
                ),
                valueMappings = valueMappings,
                repeat = repeat,
                timerange = timerange
            ),
            propertiesSetter
        )
    }
}

fun PanelContainerBuilder.singleStat(title: String, build: SingleStatPanelBuilder.() -> Unit) {
    val builder = SingleStatPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
