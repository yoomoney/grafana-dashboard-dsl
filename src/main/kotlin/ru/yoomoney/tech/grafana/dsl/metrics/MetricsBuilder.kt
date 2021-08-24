package ru.yoomoney.tech.grafana.dsl.metrics

import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.metrics.functions.Alias
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverrideBuilder

@DashboardElement
class MetricsBuilder<DatasourceT : Datasource> {

    val metrics = mutableListOf<DashboardMetric>()
    internal val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()
    private val metricIdGenerator by lazy { MetricIdGenerator() }

    fun metric(referenceId: String? = null, hidden: Boolean = false, fn: () -> Metric) {
        metrics += ReferencedDashboardMetric(fn(), referenceId ?: generateMetricId(), hidden)
    }

    fun promQlMetric(format: String? = null, instant: Boolean = true, fn: () -> Metric) {
        metrics += PromQlMetric(fn(), format, instant)
    }

    private fun generateMetricId(): String {
        var generatedId: String
        do {
            generatedId = metricIdGenerator.nextMetricId()
        } while (metrics.map { (it as ReferencedDashboardMetric).id }.contains(generatedId))
        return generatedId
    }

    fun MetricsBuilder<Zabbix>.metricsQuery(build: ZabbixMetric.Builder.Metric.() -> Unit = {}) {
        val builder = ZabbixMetric.Builder.Metric()
        builder.build()
        metrics += builder.createMetric()
    }

    fun MetricsBuilder<Zabbix>.textQuery(build: ZabbixMetric.Builder.Text.() -> Unit = {}) {
        val builder = ZabbixMetric.Builder.Text()
        builder.build()
        metrics += builder.createText()
    }

    infix fun Alias.override(build: SeriesOverrideBuilder.() -> Unit): Alias {
        val builder = SeriesOverrideBuilder(this.aliasName)
        builder.build()
        seriesOverrides += builder.createSeriesOverride()
        return this
    }
}
