package ru.yandex.money.tools.grafana.dsl.examples

import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.metrics.functions.StringMetric
import ru.yandex.money.tools.grafana.dsl.metrics.functions.alias
import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.averageSeries
import ru.yandex.money.tools.grafana.dsl.metrics.functions.movingMedian
import ru.yandex.money.tools.grafana.dsl.panels.Color
import ru.yandex.money.tools.grafana.dsl.panels.Legend
import ru.yandex.money.tools.grafana.dsl.panels.NullPointMode
import ru.yandex.money.tools.grafana.dsl.panels.graphPanel
import ru.yandex.money.tools.grafana.dsl.panels.metricPanel
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.now
import ru.yandex.money.tools.grafana.dsl.time.nowD
import ru.yandex.money.tools.grafana.dsl.time.off

/**
 * Dashboard with single-stat panels, rows, variables and charts
 *
 * @author Dmitry Komarov
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 04.12.2018
 */
dashboard(title = "Grafana Demo Layouts") {

    timeRange = nowD..now // Set time interval for metrics: from midnight to current time

    refresh = off // Disable metrics refresh

    val medianInterval by variables.interval(1.m, 10.m, 30.m, 1.h) // Introduce variable-interval for median. Name of variable in code will be equal to it's dashboard name

    val hosts by variables.query(datasource = Zabbix, query = "Service Hosts*.") {
        regex = ".*demo-service.*" // Set of applications' hosts. It can be used for repeat row for each value
    }

    panels {
        row(title = "Single Stat Metrics") { // Create row with single-stat metrics

            val singleStat = { title: String, metric: String -> // Create fabric-function for single-stat panels

                metricPanel(title = title) {

                    /*
                    Panel size, 6 will be quarter of screen, as grafana has 24 columns,
                    and 3 will be 90px, as height is measured in 30px intervals
                     */
                    bounds = 6 to 3

                    properties { // Override some properties in JSON
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
                        metric("A") { // Define a metric referencing above one (referenceId must be uniq for panel)
                            StringMetric(metric)
                        }
                    }
                }
            }

            /*
            Create a few of this panels
             */
            singleStat("Sign Ups", "apps.backend.backend_01.counters.requests.count")
            singleStat("Logins", "apps.backend.backend_02.counters.requests.count")
            singleStat("Sign Outs", "apps.backend.backend_03.counters.requests.count")
            singleStat("Support Calls", "apps.backend.backend_04.counters.requests.count")
        }

        row(title = "Graphs") {

            graphPanel(title = "Graph Metric") {

                bounds = 24 to 18
                type = "lines" // Use lines in chart
                stacked = true // Metrics must not be stacked onto Ox axis, and should overlap each other
                legend = Legend.EMPTY // Show only aliases for metrics
                fill = 1 // Fill rate
                nullPointMode = NullPointMode.NULL // How to show null values
                points = true // Show chart points
                pointradius = 2 // Chart points radius

                aliasColors {
                    "some metric" to Color.GREEN // Use predefined color
                    "another metric" to Color.of(0xBF1B00) // Use custom RGB color
                }

                metrics {
                    metric("A") {
                        /*
                        use infix style function to define metric
                         */
                        "apps.backend.*.counters.requests.count" movingMedian medianInterval aliasByNode 2 alias "some metric"
                    }

                    metric("B") {
                        "*.another.metric.mean"
                                .averageSeries() // show average value for metric
                                .alias("another metric") // define alias
                    }

                }

            }
        }

        row(title = "Repeated row", repeatFor = hosts) { // That row with all nested panels will be repeated for each values of hosts

            graphPanel(title = "Graph panel with per-host metrics") {

                metrics {

                    metric("A") {
                        /*
                        You can use variable, specified in the definition above
                         */
                        StringMetric("apps.backend.\$hosts.counters.requests.count")
                    }

                }

            }

        }
    }
}
