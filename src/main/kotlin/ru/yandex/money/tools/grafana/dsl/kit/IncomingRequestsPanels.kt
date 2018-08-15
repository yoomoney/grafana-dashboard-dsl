package ru.yandex.money.tools.grafana.dsl.kit

import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.exclude
import ru.yandex.money.tools.grafana.dsl.metrics.functions.groupByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.summarize
import ru.yandex.money.tools.grafana.dsl.panels.PanelContainerBuilder
import ru.yandex.money.tools.grafana.dsl.panels.PanelsBuilder
import ru.yandex.money.tools.grafana.dsl.panels.alerting.alert
import ru.yandex.money.tools.grafana.dsl.panels.graphPanel
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Панель входящих запросов с разбивкой по хостам.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.incomingRequestsByHosts(componentName: String) {
    graphPanel(title = "Incoming requests by hosts") {
        bounds = 12 to 10
        timeShift = 2.m

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.incoming.*.*.process_time.*.count" groupByNode 1 summarize 1.m
                        aliasByNode 0)
            }
        }
    }
}

/**
 * Панель входящих запросов с разбивкой по ручкам.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.incomingRequestsByEndpoints(componentName: String, interval: Variable) {
    graphPanel(title = "Incoming requests by endpoints") {
        bounds = 12 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.incoming.*.*.process_time.*.count" groupByNode 6 summarize interval
                        exclude "ping" exclude "state" exclude "jolokia" aliasByNode 0)
            }
        }
    }
}

/**
 * Панель входящих запросов, которые завершились неудачно.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.failedIncomingRequests(
    componentName: String,
    interval: Variable,
    notifications: List<Long>,
    thresholdValue: Int?
) {
    graphPanel(title = "Failed incoming requests") {
        bounds = 12 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.incoming.*.*.*.failed.count" groupByNode 6 summarize interval
                        aliasByNode 0)
            }

            metric("B", hidden = true) {
                "*.*.$componentName.requests.incoming.*.*.*.failed.count" groupByNode 6 aliasByNode 0
            }
        }

        if (thresholdValue != null) {
            alert("$componentName failed incoming requests alert") {
                notificationIds += notifications

                thresholds {
                    threshold { it gt thresholdValue }
                }

                conditions {
                    sum(query(this@graphPanel.metrics["B"], 10.m) isAbove thresholdValue)
                }
            }
        }
    }
}

/**
 * Панель времени обработки входящих запросов с разбивкой по ручкам.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.incomingRequestsMaxProcessTimeByEndpoints(componentName: String, interval: Variable) {
    graphPanel(title = "Max process time incoming requests") {
        bounds = 12 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.requests.incoming.*.*.process_time.*.upper".groupByNode(1, "maxSeries")
                        summarize interval aliasByNode 0)
            }
        }
    }
}

fun PanelsBuilder.incomingRequests(
    componentName: String,
    interval: Variable,
    notifications: List<Long>,
    thresholdValue: Int?
) {
    this.row("Incoming requests") {
        incomingRequestsByHosts(componentName)
        incomingRequestsByEndpoints(componentName, interval)
        failedIncomingRequests(componentName, interval, notifications, thresholdValue)
        incomingRequestsMaxProcessTimeByEndpoints(componentName, interval)
    }
}
