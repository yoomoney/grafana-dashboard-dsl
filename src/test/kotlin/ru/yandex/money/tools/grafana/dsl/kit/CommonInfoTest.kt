package ru.yandex.money.tools.grafana.dsl.kit

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.m

class CommonInfoTest {

    @Test
    fun `should create common info panels`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        val hosts by dashboardBuilder.variable {
            query("HostsQuery") {}
        }

        val autoInterval by dashboardBuilder.variable {
            interval(1.m)
        }

        // when
        dashboardBuilder.commonInfo(hosts, autoInterval, Component("test-component"))

        // then
        val dashboard = dashboardBuilder.createDashboard()

        dashboard.toJson().toString() shouldEqualToJson jsonFile("CommonInfo.json")
    }
}
