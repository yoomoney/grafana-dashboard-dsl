package ru.yoomoney.tech.grafana.dsl.metrics

import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.ZabbixDatasource
import ru.yoomoney.tech.grafana.dsl.metrics.functions.Alias
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.PrometheusMetric
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverrideBuilder

@DashboardElement
class MetricsBuilder<DatasourceT : Datasource> {

    val metrics = mutableListOf<DashboardMetric>()
    internal val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()
    private val metricIdGenerator by lazy { MetricIdGenerator() }

    fun metric(referenceId: String? = null, hidden: Boolean = false, fn: () -> Metric): String {
        val id = referenceId ?: generateMetricId()
        metrics += ReferencedDashboardMetric(fn(), id, hidden)
        return id
    }

    fun prometheusMetric(
        legendFormat: String? = null,
        instant: Boolean = false,
        referenceId: String? = null,
        hidden: Boolean = false,
        fn: () -> PrometheusMetric
    ): String {
        val id = referenceId ?: generateMetricId()
        metrics += PromQlMetric(fn(), legendFormat, instant, id, hidden)
        return id
    }

    private fun generateMetricId(): String {
        var generatedId: String
        do {
            generatedId = metricIdGenerator.nextMetricId()
        } while (metrics.map { it.id }.contains(generatedId))
        return generatedId
    }

    fun MetricsBuilder<out ZabbixDatasource>.metricsQuery(build: ZabbixMetric.Builder.Metric.() -> Unit = {}) {
        val builder = ZabbixMetric.Builder.Metric()
        builder.build()
        metrics += builder.createMetric()
    }

    fun MetricsBuilder<out ZabbixDatasource>.textQuery(build: ZabbixMetric.Builder.Text.() -> Unit = {}) {
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
