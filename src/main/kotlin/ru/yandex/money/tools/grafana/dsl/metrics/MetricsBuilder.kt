package ru.yandex.money.tools.grafana.dsl.metrics

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix

@DashboardElement
class MetricsBuilder<DatasourceT : Datasource> {

    val metrics = mutableListOf<DashboardMetric>()

    fun metric(referenceId: String, hidden: Boolean = false, fn: () -> Metric) {
        metrics += ReferencedDashboardMetric(fn(), referenceId, hidden)
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
}
