package ru.yandex.money.tools.grafana.dsl.examples

import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.metrics.functions.ConsolidationFunction
import ru.yandex.money.tools.grafana.dsl.metrics.functions.alias
import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.asPercent
import ru.yandex.money.tools.grafana.dsl.metrics.functions.averageSeries
import ru.yandex.money.tools.grafana.dsl.metrics.functions.consolidateBy
import ru.yandex.money.tools.grafana.dsl.metrics.functions.groupByNode
import ru.yandex.money.tools.grafana.dsl.metrics.functions.groupByNodes
import ru.yandex.money.tools.grafana.dsl.metrics.functions.movingMedian
import ru.yandex.money.tools.grafana.dsl.metrics.functions.perSecond
import ru.yandex.money.tools.grafana.dsl.metrics.functions.sortByTotal
import ru.yandex.money.tools.grafana.dsl.panels.graphPanel
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m

/**
 * Examples of using functions for metrics
 * @author Aleksandr Korkin
 * @since 30.09.2019
 */
dashboard(title = "Grafana Functions Demo") {
    tags += arrayOf("functions", "demo")
    val autoInterval by variables.interval(1.m, 10.m, 30.m, 1.h) // Introduce variable-interval for median. Name of variable in code will be equal to it's dashboard name

    panels {
        row(title = "Graphs") {

            graphPanel(title = "Graph Metric") {

                metrics {

                    // Function call using the targeting approach
                    metric("B") {
                        "*.another.metric.mean"
                            .groupByNodes(0)
                            .consolidateBy(ConsolidationFunction.MAX)
                            .averageSeries() // show average value for metric
                            .alias("another metric") // define alias
                            .perSecond() // show metric on value per second
                            .sortByTotal() // sort metrics in descending order by the sum of values across time period
                    }

                    // use infix style function to define metric and save in variable
                    val refMetric = "apps.backend.*.counters.requests.count" movingMedian autoInterval aliasByNode 2 alias "some metric"

                    metric("C") {
                        // represents the ratio of metric to metric variable 'refMetric' as a percentage
                        "apps.backend.*.counters.responses.count" aliasByNode 1 asPercent refMetric
                    }

                    // When a graph is drawn where width of the graph size in pixels is smaller than
                    // the number of datapoints to be graphed,
                    // Graphite consolidates the values to to prevent line overlap. The consolidateBy()
                    // function changes the consolidation function from the default of ‘average’ to one of
                    // ‘sum’, ‘max’, ‘min’, ‘first’, or ‘last’. This is especially useful in sales graphs,
                    // where fractional values make no sense and a ‘sum’ of consolidated values is appropriate.
                    metric("D") {
                        "apps.backend.*.counters.responses.process_time.upper_90" groupByNode 0 consolidateBy ConsolidationFunction.SUM
                    }
                }
            }
        }
    }
}
