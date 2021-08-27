{
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
                        """app_info""".asPrometheusMetric()
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
                val refMetric = "requests_total[5m]".asRangeVector().avgOverTime()

                prometheusMetric(legendFormat = "{{label}}") {
                    "http_request_duration_seconds_bucket[10m]".asRangeVector().rate().histogramQuantile(0.5)
                }

                prometheusMetric(legendFormat = "{{label}}") {
                    // represents the ratio of metric to metric variable 'refMetric'
                    "responses_total[5m]".asRangeVector().avgOverTime() / refMetric
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
                    "requests_total{host=\"\$hosts\"}".asInstantVector()
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
                    "some_metroc[1m]".asRangeVector().avgOverTime()
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
                    "some_metroc[1m]".asRangeVector().avgOverTime()
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
                    "some_metroc[1m]".asRangeVector().avgOverTime()
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
                    "some_metroc[1m]".asRangeVector().avgOverTime()
                }
            }
        }
    }

    row(title = "Graph panel with description") {

        graphPanel(title = "Graph") {
            description = "Graph description"
            metrics(PromQl) {
                prometheusMetric(legendFormat = "{{label}}") {
                    "some_metroc[1m]".asRangeVector().avgOverTime()
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
                    "process_failed_total[1m]".asRangeVector().avgOverTime() /
                            "process_succeeded_total[1m]".asRangeVector().avgOverTime()
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