package ru.yandex.money.tools.grafana.dsl.panels.alerting

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.metrics.ReferencedDashboardMetric
import ru.yandex.money.tools.grafana.dsl.metrics.functions.sumSeries
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

/**
 * Тест для [ru.yandex.money.tools.grafana.dsl.panels.alerting.MaxCondition]
 * @author abramovgerman
 * @since 02.12.2020
 */
class MaxConditionTest {
    @Test
    fun `should create max alerting condition`() {
        // given
        val metric = "*.*.count".sumSeries()
        val maxCondition = MaxCondition(
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
        maxCondition.toJson().toString() shouldEqualToJson jsonFile("MaxCondition.json")
    }
}