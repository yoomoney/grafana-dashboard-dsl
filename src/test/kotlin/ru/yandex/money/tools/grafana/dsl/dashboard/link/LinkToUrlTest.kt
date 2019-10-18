package ru.yandex.money.tools.grafana.dsl.dashboard.link

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class LinkToUrlTest {

    @Test
    fun `should create and serialize minimal working url link`() {
        val builder = DashboardLinkBuilder("Test link")
        val toUrl = builder.url("http://test.url")

        toUrl.toJson().toString() shouldEqualToJson jsonFile("LinkToUrlMinimal.json")
    }
}