package ru.yoomoney.tech.grafana.dsl.metrics

class ReferenceMetricsHolder {

    internal val metrics = mutableListOf<DashboardMetric>()

    operator fun get(id: String) = metrics
        .filterIsInstance<ReferencedDashboardMetric>()
        .first { it.id == id }

    internal operator fun plusAssign(metrics: Collection<DashboardMetric>) {
        this.metrics += metrics
    }
}
