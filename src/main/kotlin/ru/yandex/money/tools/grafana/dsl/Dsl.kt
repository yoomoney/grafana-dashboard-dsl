package ru.yandex.money.tools.grafana.dsl

import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder

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
