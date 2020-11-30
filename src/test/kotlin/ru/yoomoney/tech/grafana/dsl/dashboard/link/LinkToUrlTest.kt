package ru.yoomoney.tech.grafana.dsl.dashboard.link

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class LinkToUrlTest {

    @Test
    fun `should create and serialize minimal working url link`() {
        val builder = DashboardLinkBuilder("Test link")
        val toUrl = builder.url("http://test.url")

        toUrl.toJson().toString() shouldEqualToJson jsonFile("LinkToUrlMinimal.json")
    }
}