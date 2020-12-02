package ru.yandex.money.tools.grafana.dsl.panels.alerting

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.metrics.ReferencedDashboardMetric
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.time.now

@DashboardElement
class ConditionBuilder {

    internal val conditions = mutableListOf<AlertingCondition>()

    fun query(metric: ReferencedDashboardMetric, duration: Duration) = AlertQuery(
            metric = metric,
            params = *arrayOf(metric.id, duration.toString(), now.toString())
    )

    infix fun AlertQuery.isAbove(value: Int) = QueryCondition(evaluator = AlertEvaluator("gt", value), query = this)

    fun sum(condition: AlertingCondition) = SumCondition(condition)

    fun max(condition: AlertingCondition) = MaxCondition(condition)

    infix fun AlertingCondition.and(condition: AlertingCondition): AlertingCondition {
        conditions += this
        return AndCondition(condition)
    }

    infix fun AlertingCondition.or(condition: AlertingCondition): AlertingCondition {
        conditions += this
        return OrCondition(condition)
    }
}
