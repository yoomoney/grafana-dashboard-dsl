package ru.yandex.money.tools.grafana.dsl.examples

import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.dashboard.link.DashboardLinkIconShape.EXTERNAL_LINK
import ru.yandex.money.tools.grafana.dsl.dashboard.link.dashboardsWithTags
import ru.yandex.money.tools.grafana.dsl.dashboard.link.url

dashboard(title = "Grafana Links Demo") {

    tags += arrayOf("links", "demo")

    links {
        link("Test url link") {
            url("/test") {
                tooltip = "Open test url link"
                icon = EXTERNAL_LINK
                includeVariableValues = true
                keepTimeRange = true
                openInNewTab = true
            }
        }

        link("Test dashboards link") {
            dashboardsWithTags("my_dashboards", "generated_dashboard") {
                showAsDropdown = true
                includeVariableValues = true
                keepTimeRange = true
                openInNewTab = true
            }
        }
    }
}
