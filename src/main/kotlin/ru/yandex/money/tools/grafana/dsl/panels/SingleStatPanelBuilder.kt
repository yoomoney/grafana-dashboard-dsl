package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.metrics.DashboardMetric
import ru.yandex.money.tools.grafana.dsl.metrics.Metrics
import ru.yandex.money.tools.grafana.dsl.metrics.MetricsBuilder
import ru.yandex.money.tools.grafana.dsl.panels.repeat.Repeat
import ru.yandex.money.tools.grafana.dsl.panels.repeat.RepeatBuilder
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Builder for Singlestat tab
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
class SingleStatPanelBuilder(private val title: String) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    private var repeat: Repeat? = null

    private var timeRange = TimeRange()

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

    fun timeRange(build: TimeRangeBuilder.() -> Unit) {
        val builder = TimeRangeBuilder()
        builder.build()
        timeRange = builder.createTimeRanges()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            SingleStatPanel(
                MetricPanel(
                    BasePanel(
                        id = idGenerator++,
                        title = title,
                        position = nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
                ),
                valueMappings = valueMappings,
                repeat = repeat,
                timeRange = timeRange
            ),
            propertiesSetter
        )
    }
}

fun PanelContainerBuilder.singleStat(title: String, build: SingleStatPanelBuilder.() -> Unit) {
    val builder = SingleStatPanelBuilder(title)
    builder.build()
    panels += builder.createPanel()
}
