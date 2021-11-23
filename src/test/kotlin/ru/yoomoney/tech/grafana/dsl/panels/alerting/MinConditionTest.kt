package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.ReferencedDashboardMetric
import ru.yoomoney.tech.grafana.dsl.metrics.functions.sumSeries
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

/**
 * Tests for [ru.yoomoney.tech.grafana.dsl.panels.alerting.MinCondition]
 */
class MinConditionTest {
    @Test
    fun `should create min alerting condition`() {
        // given
        val metric = "*.*.count".sumSeries()
        val minCondition = MinCondition(
                QueryCondition(
                        evaluator = AlertEvaluator("gt", 100),
                        query = AlertQuery(
                                metric = ReferencedDashboardMetric(
                                        metric = metric,
                                        id = "1",
                                        hidden = true
                                )
                        )
                )
        )

        // then
        minCondition.toJson().toString() shouldEqualToJson jsonFile("MinCondition.json")
    }
}