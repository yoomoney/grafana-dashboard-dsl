package ru.yoomoney.tech.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.json.JSONObject
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.json.set
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.functions.alias
import ru.yoomoney.tech.grafana.dsl.metrics.functions.aliasByNode
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.drawoptions.HoverTooltip
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Horizontal
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson
import ru.yoomoney.tech.grafana.dsl.time.h

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
                metric {
                    "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 0
                }
                metric {
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
            bars = true
            lines = false
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
            stack = true
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithStack.json")
    }

    @Test
    fun `should create graph panel with decimals`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            decimals = null
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithDecimals.json")
    }

    @Test
    fun `should create graph panel with stepped line`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            staircase = true
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithSteppedLine.json")
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
            nullValue = NullValue.CONNECTED
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithNullPointModeConnected.json")
    }

    @Test
    fun `should create graph panel with point mode null as zero`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            nullValue = NullValue.NULL_AS_ZERO
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithNullPointModeNullAsZero.json")
    }

    @Test
    fun `should create graph panel with point mode null`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            nullValue = NullValue.NULL
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithNullPointModeNull.json")
    }

    @Test
    fun `should create graph panel with filling`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            fill = 1
            fillGradient = 1
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
            leftYAxis = YAxis(unit = YAxis.Unit.MILLISECONDS)
            rightYAxis = YAxis(unit = YAxis.Unit.MILLISECONDS)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisFormatMilliseconds.json")
    }

    @Test
    fun `should create graph panel with left axis format decimal bytes`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(unit = YAxis.Unit.DECIMAL_BYTES)
            rightYAxis = YAxis(unit = YAxis.Unit.DECIMAL_BYTES)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisFormatDecimalBytes.json")
    }

    @Test
    fun `should create graph panel with left axis format bytes`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(unit = YAxis.Unit.BYTES)
            rightYAxis = YAxis(unit = YAxis.Unit.BYTES)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisFormatBytes.json")
    }

    @Test
    fun `should create graph panel with left axis format none`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        val build: GraphPanelBuilder.() -> Unit = {
            leftYAxis = YAxis(unit = YAxis.Unit.NONE)
            rightYAxis = YAxis(unit = YAxis.Unit.NONE)
        }
        testContainer.graphPanel(title = "Test Panel", build = build)

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisFormatNone.json")
    }

    @Test
    fun `should create graph panel with left axis format percent`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(unit = YAxis.Unit.PERCENT_0_100)
            rightYAxis = YAxis(unit = YAxis.Unit.PERCENT_0_100)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisFormatPercent.json")
    }

    @Test
    fun `should create graph panel with left axis scale log 2`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(scale = YAxis.Scale.LOG2)
            rightYAxis = YAxis(scale = YAxis.Scale.LOG2)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisScaleLog2.json")
    }

    @Test
    fun `should create graph panel with left axis scale log 10`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(scale = YAxis.Scale.LOG10)
            rightYAxis = YAxis(scale = YAxis.Scale.LOG10)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisScaleLog10.json")
    }

    @Test
    fun `should create graph panel with left axis scale log 32`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(scale = YAxis.Scale.LOG32)
            rightYAxis = YAxis(scale = YAxis.Scale.LOG32)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisScaleLog32.json")
    }

    @Test
    fun `should create graph panel with left axis scale log 1024`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(scale = YAxis.Scale.LOG1024)
            rightYAxis = YAxis(scale = YAxis.Scale.LOG1024)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisScaleLog1024.json")
    }

    @Test
    fun `should create graph panel with left axis min and max values`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            leftYAxis = YAxis(min = 10, max = 100)
            rightYAxis = YAxis()
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithYAxisMinMaxValues.json")
    }

    @Test
    fun `should create graph panel with custom hover tooltip`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            hoverTooltip = HoverTooltip(HoverTooltip.Mode.SINGLE, HoverTooltip.SortOrder.INCREASING, HoverTooltip.StackedValue.CUMULATIVE)
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithCustomHoverTooltip.json")
    }

    @Test
    fun `should create graph panel with series overrides`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" alias "total" override {
                bars = false
                lines = true
                lineWidth = 2
                stack = false
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithSeriesOverrides.json")
    }

    @Test
    fun `should create graph panel with series overrides 2`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Test Panel") {
            metrics {
                "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" alias "total" override {
                    bars = false
                    lines = true
                    lineWidth = 2
                    stack = false
                }
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithSeriesOverrides.json")
    }

    @Test
    fun `should create graph panel with histogram xAxis`() {

        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.graphPanel(title = "Histogram Panel") {
            xAxis = XAxis(mode = Histogram(buckets = 10))
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("GraphPanelWithXAxisHistogram.json")
    }

    @Test
    fun `should create repeating graph panel`() {

        val expectedDashboard = dashboard("repeat horizontal") {

            val hosts by variables.custom("host1", "host2")
            panels {
                graphPanel(title = "Histogram Panel") {
                    description = "description panel"
                    repeat(hosts) {
                        direction = Horizontal(6)
                    }
                    xAxis = XAxis(mode = Histogram(buckets = 10))
                }
            }
        }
        expectedDashboard shouldEqualToJson jsonFile("RepeatingGraphPanel.json")
    }

    @Test
    fun `should create repeating graph panel for grafana 6_0 and newer`() {

        val expectedDashboard = dashboard("repeat horizontal") {

            val hosts by variables.custom("host1", "host2")
            panels {
                graphPanel(title = "Histogram Panel") {
                    description = "description panel"
                    repeat(hosts) {
                        direction = Horizontal(maxPerRow = 4)
                    }
                    xAxis = XAxis(mode = Histogram(buckets = 10))
                }
            }
        }
        expectedDashboard shouldEqualToJson jsonFile("RepeatingGraphPanelWithMaxPerRow.json")
    }
}
