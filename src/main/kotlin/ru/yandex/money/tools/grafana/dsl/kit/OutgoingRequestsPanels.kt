package ru.yandex.money.tools.grafana.dsl.kit

import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.groupByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.summarize
import ru.yandex.money.tools.grafana.dsl.panels.PanelContainerBuilder
import ru.yandex.money.tools.grafana.dsl.panels.PanelsBuilder
import ru.yandex.money.tools.grafana.dsl.panels.alerting.alert
import ru.yandex.money.tools.grafana.dsl.panels.graphPanel
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Панель исходящих запросов с разбивкой по ручкам.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.outgoingRequestsByEndpoints(componentName: String, interval: Variable) {
    graphPanel(title = "Outgoing requests count") {
        bounds = 8 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.outgoing.*.*.error.count" groupByNode 6 summarize interval
                        aliasByNode 0)
            }

            metric("B") {
                ("*.*.$componentName.requests.outgoing.*.*.response_time.*.count" groupByNode 6 summarize interval
                        aliasByNode 0)
            }
        }
    }
}

/**
 * Панель времени обработки исходящих запросов с разбивкой по ручкам.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.outgoingRequestsMaxProcessTimeByEndpoints(componentName: String, interval: Variable) {
    graphPanel(title = "Max process time outgoing requests") {
        bounds = 8 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.outgoing.*.*.response_time.*.upper" groupByNode 6 summarize interval
                        aliasByNode 0)
            }
        }
    }
}

/**
 * Панель исходящих запросов, которые завершились неудачно, с разбивкой по ручкам.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.failedOutgoingRequestsByEndpoints(
    componentName: String,
    interval: Variable,
    notifications: List<Long>,
    thresholdValue: Int?
) {
    graphPanel(title = "Failed outgoing requests") {
        bounds = 8 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.outgoing.*.*.response_time.failed.count" groupByNode 6
                        summarize interval aliasByNode 0)
            }

            metric("B") {
                ("*.*.$componentName.requests.outgoing.*.*.error.count" groupByNode 6 summarize interval
                        aliasByNode 0)
            }

            metric("C", hidden = true) {
                ("*.*.$componentName.requests.outgoing.*.*.response_time.failed.count" groupByNode 6
                        aliasByNode 0)
            }

            metric("D", hidden = true) {
                "*.*.$componentName.requests.outgoing.*.*.error.count" groupByNode 6
            }
        }

        if (thresholdValue != null) {
            alert("$componentName outgoing requests fail alert") {
                notificationIds += notifications

                thresholds {
                    threshold { it gt thresholdValue }
                }

                conditions {
                    (sum(query(this@graphPanel.metrics["C"], 10.m) isAbove thresholdValue)
                            or sum(query(this@graphPanel.metrics["D"], 10.m) isAbove thresholdValue))
                }
            }
        }
    }
}

fun PanelsBuilder.outgoingRequests(
    componentName: String,
    interval: Variable,
    notifications: List<Long>,
    thresholdValue: Int?
) {
    this.row("Outgoing requests") {
        outgoingRequestsByEndpoints(componentName, interval)
        outgoingRequestsMaxProcessTimeByEndpoints(componentName, interval)
        failedOutgoingRequestsByEndpoints(componentName, interval, notifications, thresholdValue)
    }
}
