package ru.yoomoney.tech.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.datasource.PromQl
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.functions.StringMetric
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

/**
 * Tests for [StatPanelBuilder]
 *
 * @author lokshin (lokshin@yamoney.ru)
 * @since 08.08.2021
 */
class StatPanelTest : AbstractPanelTest() {

    @Test
    fun `should create panel with promQl metric`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.statPanel(title = "PromQl Panel") {
            datasource = PromQl
            metrics {
                promQlMetric(format = "{{host}}:{{version}}") {
                    StringMetric("app_info{app_name=\"my_app\"}")
                }
            }
            options = StatPanel.StatPanelOptions(
                justifyMode = "center",
                orientation = "auto",
                textMode = "name",
                graphMode = "none"
            )
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("StatPanelWithPromQlMetric.json")
    }

}