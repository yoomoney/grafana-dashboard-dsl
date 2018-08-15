package ru.yandex.money.tools.grafana.dsl.metrics

import ru.yandex.money.tools.grafana.dsl.DashboardElement

@DashboardElement
class MetricsBuilder {

    internal val metrics = mutableListOf<ReferencedDashboardMetric>()

    fun metric(referenceId: String, hidden: Boolean = false, fn: () -> Metric) {
        metrics += ReferencedDashboardMetric(fn(), referenceId, hidden)
    }
}
