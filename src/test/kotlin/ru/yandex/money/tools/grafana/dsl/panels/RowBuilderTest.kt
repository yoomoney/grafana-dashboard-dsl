package ru.yandex.money.tools.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.generators.SimplePanelLayoutGenerator
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

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
}
