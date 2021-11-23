package ru.yoomoney.tech.grafana.dsl.panels.alerting

import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.panels.GraphPanelBuilder
import ru.yoomoney.tech.grafana.dsl.time.m

@DashboardElement
class AlertBuilder(private val name: String) {

    var message = ""
    var handler = 1
    var frequency = 1.m
    var pendingFor = 0.m
    var onNoData: AlertingState = Ok
    var onExecutionError: AlertingState = Alerting

    val notificationUids = mutableListOf<String>()
    @Deprecated("Use notificationUids instead")
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
                    notificationUids = notificationUids,
                    conditions = AlertingConditions(conditions),
                    pendingFor = pendingFor
            ),
            thresholds = Thresholds(thresholds)
    )
}

fun GraphPanelBuilder.alert(name: String, build: AlertBuilder.() -> Unit) {
    val builder = AlertBuilder(name)
    builder.build()
    properties(builder.createAlertingPanel())
}
