package ru.yoomoney.tech.grafana.dsl.examples

import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.datasource.Graphite
import ru.yoomoney.tech.grafana.dsl.datasource.PromQl
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.json.set
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions.avgOverTime
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions.histogramQuantile
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions.rate
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.instantVector
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.operators.div
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.rangeVector
import ru.yoomoney.tech.grafana.dsl.panels.Color
import ru.yoomoney.tech.grafana.dsl.panels.ContentMode
import ru.yoomoney.tech.grafana.dsl.panels.Histogram
import ru.yoomoney.tech.grafana.dsl.panels.Legend
import ru.yoomoney.tech.grafana.dsl.panels.NullValue
import ru.yoomoney.tech.grafana.dsl.panels.Series
import ru.yoomoney.tech.grafana.dsl.panels.XAxis
import ru.yoomoney.tech.grafana.dsl.panels.YAxis
import ru.yoomoney.tech.grafana.dsl.panels.graphPanel
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Horizontal
import ru.yoomoney.tech.grafana.dsl.panels.tablePanel
import ru.yoomoney.tech.grafana.dsl.panels.textPanel
import ru.yoomoney.tech.grafana.dsl.time.h
import ru.yoomoney.tech.grafana.dsl.time.m
import ru.yoomoney.tech.grafana.dsl.time.now
import ru.yoomoney.tech.grafana.dsl.time.nowD
import ru.yoomoney.tech.grafana.dsl.time.off

/**
 * Dashboard with single-stat panels, rows, variables and charts for prometheus
 *
 * @author Dmitry Komarov
 * @author Dmitry Pavlov
 * @author Aleksey Antufev
 * @author Peter Zinin
 * @since 29.08.2021
 */
dashboard(title = "Grafana Demo Prometheus Layouts") {

    timeRange = nowD..now // Set time interval for metrics: from midnight to current time

    refresh = off // Disable metrics refresh

    val medianInterval by variables.interval(
        1.m,
        10.m,
        30.m,
        1.h
    ) // Introduce variable-interval for median. Name of variable in code will be equal to it's dashboard name

    val hosts by variables.query(datasource = Zabbix, query = "Service Hosts*.") {
        regex = ".*demo-service.*" // Set of applications' hosts. It can be used for repeat row for each value
    }

    val graphiteHosts by variables.query(datasource = Graphite, query = "*.*")

    panels {
        row(title = "Single Stat Metrics") {
            // Create row with single-stat metrics

            val singleStat = { title: String, metric: String ->
                // Create fabric-function for single-stat panels

                graphPanel(title = title) {

                    /*
                    Panel size, 6 will be quarter of screen, as grafana has 24 columns,
                    and 3 will be 90px, as height is measured in 30px intervals
                     */
                    bounds = 6 to 3

                    properties {
                        // Override some properties in JSON
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

                    metrics(PromQl) {
                        prometheusMetric(legendFormat = "{{label}}") {
                            // Define a metric referencing above one
                            instantVector(metricName = "app_info", labels = mapOf("app_name" to "example"))
                        }
                    }
                }
            }

            /*
            Create a few of this panels
             */
            singleStat("Sign Ups", """rate(incoming_requests_total{type="example_1"}[1m])""")
            singleStat("Logins", """rate(incoming_requests_total{type="example_2"}[1m])""")
            singleStat("Sign Outs", """rate(incoming_requests_total{type="example_3"}[1m])""")
            singleStat("Support Calls", """rate(incoming_requests_total{type="example_4"}[1m])""")
        }

        row(title = "Graphs") {

            graphPanel(title = "Graph Metric") {

                bounds = 24 to 18
                lines = true // Use lines in chart
                stack = true // Metrics must not be stacked onto Ox axis, and should overlap each other
                legend = Legend.EMPTY // Show only aliases for metrics
                fill = 1 // Fill rate
                staircase = true // enable staircase line vizualization
                decimals = 2 // set decimal precision
                nullValue = NullValue.NULL // How to show null values
                points = true // Show chart points
                pointradius = 2 // Chart points radius
                leftYAxis = YAxis(unit = YAxis.Unit.MILLISECONDS, min = 0, max = 100, scale = YAxis.Scale.LOG10)
                rightYAxis = YAxis(unit = YAxis.Unit.PERCENT_0_100, scale = YAxis.Scale.LOG1024)

                aliasColors {
                    "some metric" to Color.GREEN // Use predefined color
                    "another metric" to Color.of(0xBF1B00) // Use custom RGB color
                }

                metrics(PromQl) {
                    /**
                     * use function style to define metric and save in variable
                     */
                    val refMetric = rangeVector(metricName = "requests_total", interval = medianInterval.asVariable())
                        .avgOverTime()

                    prometheusMetric(legendFormat = "{{label}}") {
                        rangeVector(metricName = "http_request_duration_seconds_bucket", interval = medianInterval.asVariable())
                            .rate()
                            .histogramQuantile(0.5)
                    }

                    prometheusMetric(legendFormat = "{{label}}") {
                        // represents the ratio of metric to metric variable 'refMetric'
                        rangeVector(metricName = "responses_total", interval = medianInterval.asVariable())
                            .avgOverTime() / refMetric
                    }
                }
            }
        }

        row(title = "Repeated row", repeatFor = hosts) {
            // That row with all nested panels will be repeated for each values of hosts

            graphPanel(title = "Graph panel with per-host metrics") {

                metrics(PromQl) {

                    prometheusMetric(legendFormat = "{{label}}") {
                        /**
                         * You can use variable, specified in the definition above
                         */
                        rangeVector(
                            metricName = "requests_total",
                            labels = mapOf("host" to hosts.asVariable()),
                            interval = medianInterval.asVariable()
                        ).rate().histogramQuantile(0.5)
                    }
                }
            }
        }

        // Using the text panel to display the dashboard description
        textPanel("Description") {
            mode = ContentMode.MARKDOWN
            content = "### Text Panel with MD content"
        }

        row(title = "Graphs") {

            graphPanel(title = "Graph Metric in histogram mode") {
                bounds = 24 to 18
                fill = 1 // Fill rate
                staircase = true // enable staircase line vizualization
                decimals = 2 // set decimal precision
                nullValue = NullValue.NULL // How to show null values
                xAxis = XAxis(mode = Histogram(buckets = 10))
                metrics(PromQl) {
                    prometheusMetric(legendFormat = "{{label}}") {
                        rangeVector(metricName = "some_metric", interval = medianInterval.asVariable()).avgOverTime()
                    }
                }
            }

            graphPanel(title = "Graph Metric in series mode") {
                bounds = 24 to 18
                fill = 1 // Fill rate
                staircase = true // enable staircase line vizualization
                decimals = 2 // set decimal precision
                nullValue = NullValue.NULL // How to show null values
                xAxis = XAxis(mode = Series(Series.ValueType.COUNT))
                metrics(PromQl) {
                    prometheusMetric(legendFormat = "{{label}}") {
                        rangeVector(metricName = "some_metric", interval = medianInterval.asVariable()).avgOverTime()
                    }
                }
            }
        }

        row(title = "Repeating panels/ Grafana 5.4 and leter") {

            graphPanel(title = "Graph Metric in histogram mode") {
                bounds = 24 to 18
                fill = 1 // Fill rate
                staircase = true // enable staircase line vizualization
                decimals = 2 // set decimal precision
                nullValue = NullValue.NULL // How to show null values
                xAxis = XAxis(mode = Histogram(buckets = 10))
                repeat(hosts) {
                    direction = Horizontal(6)
                }
                metrics(PromQl) {
                    prometheusMetric(legendFormat = "{{label}}") {
                        rangeVector(metricName = "some_metric", interval = medianInterval.asVariable()).avgOverTime()
                    }
                }
            }
        }

        row(title = "Repeating panels / Grafana 6.0 and newer") {

            graphPanel(title = "Graph Metric in histogram mode") {
                bounds = 24 to 18
                fill = 1 // Fill rate
                staircase = true // enable staircase line vizualization
                decimals = 2 // set decimal precision
                nullValue = NullValue.NULL // How to show null values
                xAxis = XAxis(mode = Histogram(buckets = 10))
                repeat(hosts) {
                    direction = Horizontal(maxPerRow = 6)
                }
                metrics(PromQl) {
                    prometheusMetric(legendFormat = "{{label}}") {
                        rangeVector(metricName = "some_metric", interval = medianInterval.asVariable()).avgOverTime()
                    }
                }
            }
        }

        row(title = "Graph panel with description") {

            graphPanel(title = "Graph") {
                description = "Graph description"
                metrics(PromQl) {
                    prometheusMetric(legendFormat = "{{label}}") {
                        rangeVector(metricName = "some_metric", interval = medianInterval.asVariable()).avgOverTime()
                    }
                }
            }
        }

        row(title = "Collapsed row", collapsed = true) {
            textPanel("Description") {
                mode = ContentMode.MARKDOWN
                content = "### Text Panel with MD content"
            }
        }

        row(title = "Table Panel") {
            tablePanel(title = "Test Panel") {
                timeFrom = 1.h

                metrics(PromQl) {
                    prometheusMetric(legendFormat = "{{label}}") {
                        "process_failed_total[${medianInterval.asVariable()}]".asRangeVector().avgOverTime() /
                                "process_succeeded_total[${medianInterval.asVariable()}]".asRangeVector().avgOverTime()
                    }
                }

                columns {
                    "current" to "Current"
                }

                styles {
                    style("Current") {
                        unit = YAxis.Unit.PERCENT_0_100
                        decimals = 2
                    }
                    style("/.*/") {
                        decimals = 2
                    }
                }
            }
        }
    }
}