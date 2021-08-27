package ru.yoomoney.tech.grafana.dsl.panels.stat

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.metrics.DashboardMetric
import ru.yoomoney.tech.grafana.dsl.metrics.Metrics
import ru.yoomoney.tech.grafana.dsl.metrics.MetricsBuilder
import ru.yoomoney.tech.grafana.dsl.panels.AdditionalPropertiesPanel
import ru.yoomoney.tech.grafana.dsl.panels.BasePanel
import ru.yoomoney.tech.grafana.dsl.panels.MetricPanel
import ru.yoomoney.tech.grafana.dsl.panels.Panel
import ru.yoomoney.tech.grafana.dsl.panels.PanelBuilder
import ru.yoomoney.tech.grafana.dsl.panels.PanelContainerBuilder
import ru.yoomoney.tech.grafana.dsl.panels.Timerange
import ru.yoomoney.tech.grafana.dsl.panels.TimerangeBuilder
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Repeat
import ru.yoomoney.tech.grafana.dsl.panels.repeat.RepeatBuilder
import ru.yoomoney.tech.grafana.dsl.variables.Variable

/**
 * Builder for Stat tab
 * @author Aleksey Matveev
 * @since 29.10.2020
 */
class StatPanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {
    override var bounds: Pair<Int, Int> = PanelBuilder.DEFAULT_BOUNDS

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private var timerange = Timerange()

    private var repeat: Repeat? = null

    var metrics: List<DashboardMetric> = mutableListOf()

    var datasource: Datasource = Zabbix

    var options: StatPanelDisplayOptions = StatPanelDisplayOptions()

    var fieldConfig: StatPanelFieldConfig = StatPanelFieldConfig()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun options(build: StatPanelDisplayOptionsBuilder.() -> Unit) {
        val builder = StatPanelDisplayOptionsBuilder()
        builder.build()
        options = builder.createStatPanelDisplayOptions()
    }

    fun fieldConfig(build: StatPanelFieldConfigBuilder.() -> Unit) {
        val builder = StatPanelFieldConfigBuilder()
        builder.build()
        fieldConfig = builder.createStatPanelFieldConfig()
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

    fun <T : Datasource> metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        this.metrics = builder.metrics
        this.datasource = datasource
    }

    fun timerange(build: TimerangeBuilder.() -> Unit) {
        val builder = TimerangeBuilder()
        builder.build()
        timerange = builder.createTimerange()
    }

    internal fun createPanel(): Panel {
        return AdditionalPropertiesPanel(
            StatPanel(
                MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
                ),
                repeat = repeat,
                timerange = timerange,
                options = options,
                fieldConfig = fieldConfig
            ),
            propertiesSetter
        )
    }
}

fun PanelContainerBuilder.statPanel(title: String, build: StatPanelBuilder.() -> Unit) {
    val builder = StatPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
