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
 * Панель, содержащая информацию о времени обработки задач в очереди.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.queueMaxProcessTime(componentName: String, interval: Variable) {
    graphPanel(title = "Max process time queue") {
        bounds = 12 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.processes.queue.*.execution_time.*.upper" groupByNode 5 summarize interval
                        aliasByNode 0)
            }
        }
    }
}

/**
 * Панель, содержащая информацию о задачах в очереди, которые завершились неудачно.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
fun PanelContainerBuilder.failedQueue(
    componentName: String,
    interval: Variable,
    notifications: List<Long>,
    thresholdValue: Int?
) {
    graphPanel(title = "Failed queue") {
        bounds = 12 to 10
        type = "bars"
        stacked = true

        metrics {
            metric("A") {
                ("*.*.$componentName.processes.queue.*.error.count" groupByNode 5 summarize interval
                        aliasByNode 0)
            }

            metric("B") {
                ("*.*.$componentName.processes.queue.*.execution_time.failed.count" groupByNode 5
                        summarize interval aliasByNode 0)
            }

            metric("C", hidden = true) {
                "*.*.$componentName.processes.queue.*.error.count" groupByNode 5 aliasByNode 0
            }

            metric("D", hidden = true) {
                "*.*.$componentName.processes.queue.*.execution_time.failed.count" groupByNode 5 aliasByNode 0
            }
        }

        if (thresholdValue != null) {
            alert("$componentName queue fail alert") {
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

fun PanelsBuilder.queue(componentName: String, interval: Variable, notifications: List<Long>, thresholdValue: Int?) {
    this.row("Queue") {
        queueMaxProcessTime(componentName, interval)
        failedQueue(componentName, interval, notifications, thresholdValue)
    }
}
