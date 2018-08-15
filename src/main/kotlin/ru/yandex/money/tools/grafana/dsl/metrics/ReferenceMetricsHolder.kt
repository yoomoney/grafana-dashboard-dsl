package ru.yandex.money.tools.grafana.dsl.metrics

class ReferenceMetricsHolder {

    internal val metrics = mutableListOf<ReferencedDashboardMetric>()

    operator fun get(id: String) = metrics.find { it.id == id }!!

    internal operator fun plusAssign(metrics: Collection<ReferencedDashboardMetric>) {
        this.metrics += metrics
    }
}
