package ru.yoomoney.tech.grafana.dsl

import ru.yoomoney.tech.grafana.dsl.dashboard.DashboardBuilder

/**
 * Returns JSON-string for dashboard import.
 *
 * @param title Dashboard title
 * @param build Dashboard builder closure
 * @return JSON-string
 */
fun dashboard(title: String, build: DashboardBuilder.() -> Unit): String {
    val builder = DashboardBuilder(title)
    builder.build()
    val dashboard = builder.createDashboard()
    return dashboard.toJson().toString()
}
