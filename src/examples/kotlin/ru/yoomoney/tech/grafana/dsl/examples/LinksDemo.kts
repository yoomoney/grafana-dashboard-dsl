package ru.yoomoney.tech.grafana.dsl.examples

import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.dashboard.link.DashboardLinkIconShape.EXTERNAL_LINK
import ru.yoomoney.tech.grafana.dsl.dashboard.link.dashboardsWithTags
import ru.yoomoney.tech.grafana.dsl.dashboard.link.url

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
