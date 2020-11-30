package ru.yoomoney.tech.grafana.dsl.dashboard.link

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class LinkToDashboardsTest {

    @Test
    fun `should create and serialize minimal working url link`() {
        val builder = DashboardLinkBuilder("Test link")
        val toDashboards = builder.dashboardsWithTags("tag1", "tag2")

        toDashboards.toJson().toString() shouldEqualToJson jsonFile("LinkToDashboardsMinimal.json")
    }
}