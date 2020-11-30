package ru.yoomoney.tech.grafana.dsl.dashboard.link

/**
 * Type of dashboard's link
 */
enum class DashboardLinkType(val value: String) {
    /**
     * Link to other dashboards
     */
    DASHBOARDS("dashboards"),

    /**
     * Link to arbitrary url address
     */
    URI_LINK("link")
}
