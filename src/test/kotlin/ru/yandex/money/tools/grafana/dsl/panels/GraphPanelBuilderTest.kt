package ru.yandex.money.tools.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.json.JSONObject
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.metrics.functions.aliasByNode
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.h

class GraphPanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create graph panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("EmptyGraphPanel.json")
    }

    @Test
    fun `should create graph panel with custom bounds`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            bounds = 12 to 6
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithCustomBounds.json")
    }

    @Test
    fun `should create graph panel with custom data source`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            datasource = Zabbix
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithCustomDataSource.json")
    }

    @Test
    fun `should create graph panel with additional properties`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            properties {
                it["alert"] = JSONObject()
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithAdditionalProperties.json")
    }

    @Test
    fun `should create graph panel with 2 metrics`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            metrics {
                metric("A") {
                    "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 0
                }
                metric("B") {
                    "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 1
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithMetrics.json")
    }

    @Test
    fun `should create graph panel with bars`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            type = "bars"
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithBars.json")
    }

    @Test
    fun `should create graph panel with custom time shift`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            timeShift = 1.h
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithCustomTimeShift.json")
    }

    @Test
    fun `should create graph panel with stacked draw mode on`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            stacked = true
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithStack.json")
    }

    @Test
    fun `should create graph panel with alias colors`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            aliasColors {
                "success" to Color.GREEN
                "fail" to Color.RED
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithCustomAliasColors.json")
    }

    @Test
    fun `should create graph panel with default legend`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            legend = Legend.DEFAULT
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithDefaultLegend.json")
    }

    @Test
    fun `should create graph panel with empty legend`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            legend = Legend.EMPTY
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithEmptyLegend.json")
    }

    @Test
    fun `should create graph panel with points`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            points = true
            pointradius = 3
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithPoints.json")
    }

    @Test
    fun `should create graph panel with point mode connected`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            nullPointMode = NullPointMode.CONNECTED
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithNullPointModeConnected.json")
    }

    @Test
    fun `should create graph panel with filling`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            fill = 1
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithFilling.json")
    }

    @Test
    fun `should create graph panel with left axis format milliseconds`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(format = YAxis.Format.MILLISECONDS)
            rightYAxis = YAxis(format = YAxis.Format.MILLISECONDS)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisFormatMilliseconds.json")
    }

}
