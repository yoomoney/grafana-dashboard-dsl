package ru.yoomoney.tech.grafana.dsl.panels.alerting

import ru.yoomoney.tech.grafana.dsl.DashboardElement

@DashboardElement
class ThresholdsBuilder {

    internal val thresholds = mutableListOf<Threshold>()

    fun threshold(fn: (ThresholdDsl) -> Threshold) {
        thresholds += fn(ThresholdDsl)
    }
}

object ThresholdDsl {
    infix fun gt(value: Int) = Threshold("gt", value)
}
