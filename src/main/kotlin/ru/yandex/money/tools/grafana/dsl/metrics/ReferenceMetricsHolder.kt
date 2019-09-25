package ru.yandex.money.tools.grafana.dsl.metrics

class ReferenceMetricsHolder {

    internal val metrics = mutableListOf<DashboardMetric>()

    operator fun get(id: String) = metrics
        .filterIsInstance<ReferencedDashboardMetric>()
        .find { it.id == id }!!

    internal operator fun plusAssign(metrics: Collection<DashboardMetric>) {
        this.metrics += metrics
    }
}
