package ru.yoomoney.tech.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.functions.aliasByNode
import ru.yoomoney.tech.grafana.dsl.metrics.functions.group
import ru.yoomoney.tech.grafana.dsl.metrics.functions.groupByNode
import ru.yoomoney.tech.grafana.dsl.metrics.functions.groupByNodes
import ru.yoomoney.tech.grafana.dsl.metrics.functions.scale
import ru.yoomoney.tech.grafana.dsl.metrics.functions.summarize
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson
import ru.yoomoney.tech.grafana.dsl.time.h

class TablePanelBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create table panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {}

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/EmptyTablePanel.json")
    }

    @Test
    fun `should create table panel with custom bounds`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            bounds = 12 to 8
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/TablePanelWithCustomBounds.json")
    }

    @Test
    fun `should create table panel with custom data source`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            datasource = Zabbix
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/TablePanelWithCustomDataSource.json")
    }

    @Test
    fun `should create table panel with referenced metrics`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
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
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/TablePanelWithMetrics.json")
    }

    @Test
    fun `should create table panel with custom relative time`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            timeFrom = 1.h
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/TablePanelWithCustomTimeShift.json")
    }

    @Test
    fun `should create table panel with custom columns`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
            timeFrom = 1.h
            columns {
                "custom" to "Custom"
                "first" to "First"
            }
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/TablePanelWithColumns.json")
    }

    @Test
    fun `should create full table panel`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.tablePanel(title = "Test Panel") {
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

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("table/TablePanel.json")
    }
}
