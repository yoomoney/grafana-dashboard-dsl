package ru.yoomoney.tech.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard.DashboardBuilder
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.generators.SimplePanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class RowBuilderTest : AbstractPanelTest() {

    @Test
    fun `should create row with 1 panel`() {
        // given
        val panelsBuilder = PanelsBuilder(SimplePanelLayoutGenerator())
        val dashboardBuilder = DashboardBuilder("Test Dashboard")
        val values by dashboardBuilder.variables.query(datasource = Zabbix, query = "My variable") {
            regex = ".*"
        }

        // when
        panelsBuilder.row("Test Row", repeatFor = values) {
            panel("Test Panel") {}
        }
        // then
        val panels = panelsBuilder.panels
        panels.size shouldBe 2
        panels[0].toJson().toString() shouldEqualToJson jsonFile("Row.json")
    }

    @Test
    fun `should create collapsed row with 1 panel`() {
        // given
        val panelsBuilder = PanelsBuilder(SimplePanelLayoutGenerator())

        panelsBuilder.row("Collapsed row", collapsed = true) {
            panel("Test panel") {}
        }
        // then
        val panels = panelsBuilder.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("CollapsedRow.json")
    }
}
