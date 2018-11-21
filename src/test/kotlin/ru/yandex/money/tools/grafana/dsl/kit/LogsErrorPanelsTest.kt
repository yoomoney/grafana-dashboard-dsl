package ru.yandex.money.tools.grafana.dsl.kit

import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.*
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.m


class LogsErrorPanelsTest {

    @AfterMethod
    fun after() {
        x = 0
        y = 0
        idGenerator = 1L
    }

    @Test
    fun `should create dashboard contains all logs error panels`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        val autoInterval by dashboardBuilder.variable {
            interval(1.m)
        }
        dashboardBuilder.panels {
            logs("test-component", autoInterval)
        }

        // then
        val dashboard = dashboardBuilder.createDashboard()
        dashboard.toJson().toString() shouldEqualToJson jsonFile("DashboardWithAllErrorsPanels.json")
    }

    @Test
    fun `should create dashboard contains logs error count panel`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        dashboardBuilder.panels {
            errorLogsCount("test-component")
        }

        // then
        val dashboard = dashboardBuilder.createDashboard()
        dashboard.toJson().toString() shouldEqualToJson jsonFile("DashboardWithErrorsCountPanel.json")
    }

    @Test
    fun `should create dashboard contains logs error count by node panel`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        val autoInterval by dashboardBuilder.variable {
            interval(1.m)
        }
        dashboardBuilder.panels {
            errorLogsCountByNode("test-component", autoInterval)
        }

        // then
        val dashboard = dashboardBuilder.createDashboard()
        dashboard.toJson().toString() shouldEqualToJson jsonFile("DashboardWithErrorsCountByNodePanel.json")
    }

    @Test
    fun `should create dashbord contains error alerts panel`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")
        dashboardBuilder.panels {
            errorAlert("test-component")
        }

        // then
        val dashboard = dashboardBuilder.createDashboard()
        dashboard.toJson().toString() shouldEqualToJson jsonFile("DashboardWithErrorAlertsPanel.json")
    }
}
