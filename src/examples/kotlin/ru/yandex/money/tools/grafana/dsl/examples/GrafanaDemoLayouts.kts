package ru.yandex.money.tools.grafana.dsl.examples

import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.metrics.functions.StringMetric
import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.movingMedian
import ru.yandex.money.tools.grafana.dsl.panels.graphPanel
import ru.yandex.money.tools.grafana.dsl.panels.metricPanel
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.now
import ru.yandex.money.tools.grafana.dsl.time.off

/**
 * Дашборд со single-stat панелями, строками и переменными.
 *
 * @see [Grafana Demo Layouts Test Dashboard](https://play.grafana.org/d/000000067/layout-test?orgId=1)
 * @author Dmitry Komarov
 * @since 04.12.2018
 */
dashboard(title = "Grafana Demo Layouts") {

    timeRange = now - 6.h .. now // зададим временной промежуток, за который будут запрошены метрики

    refresh = off // отключаем периодическое обновление метрик

    val medianInterval by variable {
        interval(1.m, 10.m, 30.m, 1.h) // зададим переменную-интервал для медианы. Имя переменной на дашборде == имени переменной в коде
    }

    panels {
        row(title = "Single Stat Metrics") { // создаем строку с single-stat метриками

            val singleStat = { title: String, metric: String -> // Создадим свою собственную функцию-фабрику для single-stat панелей
                metricPanel(title = title) {
                    /*
                    зададим размеры: 6x3 означает, что в ширину панель будет занимать четверть экрана (в Grafana по ширине
                    24 колонки), а в высоту 90px (в Grafana единица высоты = 30px)
                     */
                    bounds = 6 to 3

                    properties { // переопределим некоторые свойства в сыром JSON
                        it["type"] = "singlestat"
                        it["nullPointMode"] = "connected"
                        it["valueName"] = "avg"
                        it["sparkline"] = jsonObject {
                            "show" to true
                            "full" to true
                            "fillColor" to "rgba(31, 118, 189, 0.18)"
                            "lineColor" to "rgb(31, 120, 193)"
                        }
                        it["colorValue"] = true
                    }

                    metrics {
                        metric("A") { // Зададим метрику с ссылкой на нее. Требуется, чтобы эта ссылка была уникальна для панели
                            StringMetric(metric)
                        }
                    }
                }
            }

            /*
            Создадим несколько таких панелей
             */
            singleStat("Sign Ups", "apps.backend.backend_01.counters.requests.count")
            singleStat("Logins", "apps.backend.backend_02.counters.requests.count")
            singleStat("Sign Outs", "apps.backend.backend_03.counters.requests.count")
            singleStat("Support Calls", "apps.backend.backend_04.counters.requests.count")
        }

        row(title = "Graphs") {

            graphPanel(title = "Graph Metric") {
                bounds = 24 to 18

                type = "lines" // использовать для рисования графика линии
                stacked = true // метрики не должны прижиматься к оси Ox, а должны "накладываться" друг на друга

                metrics {
                    metric("A") {
                        /*
                        используем infix-функции для создания метрики
                         */
                        "apps.backend.*.counters.requests.count" movingMedian medianInterval aliasByNode 2
                    }
                }

                properties {
                    it["fill"] = 5
                    it["legend"] = jsonObject {
                        "show" to false
                    }
                }
            }
        }
    }
}
