package ru.yoomoney.tech.grafana.dsl.examples

import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.datasource.Graphite
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.json.set
import ru.yoomoney.tech.grafana.dsl.metrics.functions.ConsolidationFunction.MAX
import ru.yoomoney.tech.grafana.dsl.metrics.functions.ConsolidationFunction.SUM
import ru.yoomoney.tech.grafana.dsl.metrics.functions.StringMetric
import ru.yoomoney.tech.grafana.dsl.metrics.functions.alias
import ru.yoomoney.tech.grafana.dsl.metrics.functions.aliasByNode
import ru.yoomoney.tech.grafana.dsl.metrics.functions.asPercent
import ru.yoomoney.tech.grafana.dsl.metrics.functions.averageSeries
import ru.yoomoney.tech.grafana.dsl.metrics.functions.consolidateBy
import ru.yoomoney.tech.grafana.dsl.metrics.functions.group
import ru.yoomoney.tech.grafana.dsl.metrics.functions.groupByNode
import ru.yoomoney.tech.grafana.dsl.metrics.functions.groupByNodes
import ru.yoomoney.tech.grafana.dsl.metrics.functions.movingMedian
import ru.yoomoney.tech.grafana.dsl.metrics.functions.perSecond
import ru.yoomoney.tech.grafana.dsl.metrics.functions.scale
import ru.yoomoney.tech.grafana.dsl.metrics.functions.sortByTotal
import ru.yoomoney.tech.grafana.dsl.metrics.functions.summarize
import ru.yoomoney.tech.grafana.dsl.panels.Color
import ru.yoomoney.tech.grafana.dsl.panels.ContentMode
import ru.yoomoney.tech.grafana.dsl.panels.Histogram
import ru.yoomoney.tech.grafana.dsl.panels.Legend
import ru.yoomoney.tech.grafana.dsl.panels.NullValue
import ru.yoomoney.tech.grafana.dsl.panels.Series
import ru.yoomoney.tech.grafana.dsl.panels.ValueToTextType
import ru.yoomoney.tech.grafana.dsl.panels.XAxis
import ru.yoomoney.tech.grafana.dsl.panels.YAxis
import ru.yoomoney.tech.grafana.dsl.panels.graphPanel
import ru.yoomoney.tech.grafana.dsl.panels.metricPanel
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Horizontal
import ru.yoomoney.tech.grafana.dsl.panels.singleStat
import ru.yoomoney.tech.grafana.dsl.panels.tablePanel
import ru.yoomoney.tech.grafana.dsl.panels.textPanel
import ru.yoomoney.tech.grafana.dsl.time.h
import ru.yoomoney.tech.grafana.dsl.time.m
import ru.yoomoney.tech.grafana.dsl.time.now
import ru.yoomoney.tech.grafana.dsl.time.nowD
import ru.yoomoney.tech.grafana.dsl.time.off

/**
 * Dashboard with single-stat panels, rows, variables and charts
 *
 * @author Dmitry Komarov
 * @author Dmitry Pavlov
 * @author Aleksey Antufev
 * @since 04.12.2018
 */
dashboard(title = "Grafana Demo Layouts") {

    timeRange = nowD..now // Set time interval for metrics: from midnight to current time

    refresh = off // Disable metrics refresh

    val medianInterval by variables.interval(1.m, 10.m, 30.m, 1.h) // Introduce variable-interval for median. Name of variable in code will be equal to it's dashboard name

    val hosts by variables.query(datasource = Zabbix, query = "Service Hosts*.") {
        regex = ".*demo-service.*" // Set of applications' hosts. It can be used for repeat row for each value
    }

    val graphiteHosts by variables.query(datasource = Graphite, query = "*.*")

    panels {
        row(title = "Single Stat Metrics") {
            // Create row with single-stat metrics

            val singleStat = { title: String, metric: String ->
                // Create fabric-function for single-stat panels

                metricPanel(title = title) {

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

                    metrics(Graphite) {
                        metric("A") {
                            // Define a metric referencing above one (referenceId must be uniq for panel)
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

                metrics {
                    /*
                        use infix style function to define metric and save in variable
                     */
                    val refMetric = "apps.backend.*.counters.requests.count" movingMedian medianInterval aliasByNode 2 alias "some metric"

                    metric("B") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(MAX)
                            .averageSeries() // show average value for metric
                            .alias("another metric") // define alias
                            .perSecond() // show metric on value per second
                            .sortByTotal() // sort metrics in descending order by the sum of values across time period
                    }

                    metric("C") {
                        "apps.backend.*.counters.login" alias "Some" override { bars = true } // override parameter 'bars'
                    }

                    metric("D") {
                        // represents the ratio of metric to metric variable 'refMetric' as a percentage
                        "apps.backend.*.counters.responses.count" aliasByNode 1 asPercent refMetric
                    }
                    // When a graph is drawn where width of the graph size in pixels is smaller than
                    // the number of datapoints to be graphed,
                    // Graphite consolidates the values to to prevent line overlap. The consolidateBy()
                    // function changes the consolidation function from the default of ‘average’ to one of
                    // ‘sum’, ‘max’, ‘min’, ‘first’, or ‘last’. This is especially useful in sales graphs,
                    // where fractional values make no sense and a ‘sum’ of consolidated values is appropriate.
                    metric("E") {
                        "apps.backend.*.counters.responses.process_time.upper_90" groupByNode 0 consolidateBy SUM
                    }
                }
            }
        }

        row(title = "Repeated row", repeatFor = hosts) {
            // That row with all nested panels will be repeated for each values of hosts

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

        singleStat(hosts.asVariable()) {
            // Singlestat panel

            repeat(hosts) {
                // apply singlestat panel for all values from variable
                direction = Horizontal(2) // horizontal panels direction and 2 units between them
            }

            metrics<Zabbix> {
                // metric from datasource Zabbix
                textQuery {
                    // text query
                    host = hosts.asVariable()
                    application = "/App acquirer Ping General/"
                    item = "/service version/"
                    group = "/.*/"
                }
            }

            valueMappings<ValueToTextType> {
                // set value mappings that shows in singlestat panel
                valueToText {
                    "null" to "N/A" // null value shows 'N/A'
                }
            }

            timerange {
                timeShift = 5.m // set 5 min shift for repeat
                hideTimeOverrideInfo = true // dont show time override info on panel
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
                metrics {
                    metric("A") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(MAX)
                            .alias("another metric") // define alias
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
                metrics {
                    metric("A") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(MAX)
                            .alias("another metric") // define alias
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
                repeat(graphiteHosts) {
                    direction = Horizontal(6)
                }
                metrics {
                    metric("A") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(MAX)
                            .alias("another metric") // define alias
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
                repeat(graphiteHosts) {
                    direction = Horizontal(maxPerRow = 6)
                }
                metrics {
                    metric("A") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(MAX)
                            .alias("another metric") // define alias
                    }
                }
            }
        }

        row(title = "Graph panel with description") {

            graphPanel(title = "Graph") {
                description = "Graph description"
                metrics {
                    metric("A") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(MAX)
                            .alias("another metric") // define alias
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

                metrics {
                    metric("A", hidden = true) {
                        "*.*.service.processes.system.session-query.*.*.unknown_session.succeeded.count"
                            .groupByNodes("sum", 7).summarize(24.h, "sum").aliasByNode(0)
                    }
                    metric("B", hidden = true) {
                        "*.*.service.processes.system.session-query.*.*.*.succeeded.count"
                            .groupByNodes("sum", 7).summarize(24.h, "sum").aliasByNode(0)
                    }
                    metric {
                        ("#A" scale 100.0).group("#B").groupByNode(0, "divideSeries")
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
