package ru.yandex.money.tools.grafana.dsl.panels.alerting

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.panels.GraphPanelBuilder
import ru.yandex.money.tools.grafana.dsl.time.m

@DashboardElement
class AlertBuilder(private val name: String) {

    var message = ""
    var handler = 1
    var frequency = 1.m
    var onNoData = Ok
    var onExecutionError = Alerting

    val notificationIds = mutableListOf<Long>()

    private var conditions = emptyList<AlertingCondition>()
    private val thresholds = mutableListOf<Threshold>()

    fun conditions(build: ConditionBuilder.() -> AlertingCondition) {
        val builder = ConditionBuilder()
        val condition = builder.build()
        conditions = builder.conditions + condition
    }

    fun thresholds(build: ThresholdsBuilder.() -> Unit) {
        val builder = ThresholdsBuilder()
        builder.build()
        thresholds += builder.thresholds
    }

    internal fun createAlertingPanel() = AlertingPanelDecorator(
            alert = Alert(
                    name = name,
                    message = message,
                    handler = handler,
                    frequency = frequency,
                    onNoData = onNoData,
                    onExecutionError = onExecutionError,
                    notificationIds = notificationIds,
                    conditions = AlertingConditions(conditions)
            ),
            thresholds = Thresholds(thresholds)
    )
}

fun GraphPanelBuilder.alert(name: String, build: AlertBuilder.() -> Unit) {
    val builder = AlertBuilder(name)
    builder.build()
    properties(builder.createAlertingPanel())
}
