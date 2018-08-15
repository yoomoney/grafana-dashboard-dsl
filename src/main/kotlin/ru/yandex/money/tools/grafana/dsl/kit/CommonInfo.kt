package ru.yandex.money.tools.grafana.dsl.kit

import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Добавляет на дэшборд панели, содержащие информацию о: входящих и исходящих запросах, информацию о состоянии очередей.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun DashboardBuilder.commonInfo(
    hosts: Variable,
    interval: Variable,
    componentName: Component,
    commonInfoAlerting: CommonInfoAlertingBuilder.() -> Unit = {}
) {
    val builder = CommonInfoAlertingBuilder()
    builder.commonInfoAlerting()

    this.tags += "common-info"

    this.panels {
        versions(hosts, componentName.pingName)
        incomingRequests(
                componentName.name,
                interval,
                builder.notificationIds,
                builder.thresholdValues[INCOMING_REQUESTS]
        )
        outgoingRequests(
                componentName.name,
                interval,
                builder.notificationIds,
                builder.thresholdValues[OUTGOING_REQUESTS]
        )
        queue(componentName.name, interval, builder.notificationIds, builder.thresholdValues[QUEUE])
    }
}
