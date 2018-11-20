package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.sumSeries
import ru.yandex.money.tools.grafana.dsl.metrics.functions.summarize
import ru.yandex.money.tools.grafana.dsl.metrics.functions.divideSeries
import ru.yandex.money.tools.grafana.dsl.metrics.functions.scale
import ru.yandex.money.tools.grafana.dsl.metrics.functions.alias
import ru.yandex.money.tools.grafana.dsl.metrics.functions.transformNull
import ru.yandex.money.tools.grafana.dsl.metrics.functions.movingMedian
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Панель, содержащая информацию о количестве логов с уровнем ERROR
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
fun PanelContainerBuilder.errorLogsCount(componentName: String) {
    graphPanel(title = "Error logs count") {
        type = "bars"
        bounds = 12 to 10
        metrics {
            metric("A") {
                "*.*.$componentName.logs.ERROR.count".sumSeries()
            }
        }
    }
}

/**
 * Панель, содержащая информацию о количестве логов с уровнем ERROR по нодам
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
fun PanelContainerBuilder.errorLogsCountByNode(componentName: String, interval: Variable) {
    graphPanel(title = "Error logs count") {
        type = "bars"
        bounds = 12 to 10
        stacked = true
        metrics {
            metric("A") {
                "*.*.$componentName.logs.ERROR.count" summarize interval aliasByNode 1
            }
        }
    }
}

/**
 * Панель содержащая 2 графика: отношение числа ошибок к общему числу логов, а также медиана отношения
 * числа ошибок к общему числу логов за 30 минут
 *
 * @author iryabtsev (Igor Ryabtsev)
 * @since 15.11.2018
 */
fun PanelContainerBuilder.errorAlert(componentName: String) {
    graphPanel(title = "Error logs count") {
        type = "lines"
        metrics {
            metric("A") {
                (("*.*.$componentName.logs.ERROR.count".sumSeries()) divideSeries
                        ("*.*.$componentName.logs.*.count".sumSeries()) scale 100000.0 transformNull 0.0
                        alias "Scaled ratio of errors to the total number of logs")
            }
            metric("B") {
                (("*.*.$componentName.logs.ERROR.count".sumSeries()) divideSeries
                        ("*.*.$componentName.logs.*.count".sumSeries()) scale 100000.0 transformNull 0.0
                        movingMedian 30.m
                        alias "Moving median of scaled ratio of errors to the total number of logs over 30 minutes")
            }
        }
    }
}

fun PanelsBuilder.logs(
    componentName: String,
    interval: Variable
) {
    this.row("ERROR logs") {
        errorLogsCount(componentName)
        errorLogsCountByNode(componentName, interval)
        errorAlert(componentName)
    }
}
