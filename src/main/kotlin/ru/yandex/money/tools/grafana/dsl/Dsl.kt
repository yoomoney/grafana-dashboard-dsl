package ru.yandex.money.tools.grafana.dsl

import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder

/**
 * Возвращает JSON-строку для импорта дэшборда.
 *
 * @param title название дэшборда
 * @param build замыкание для построения элементов дэшборда
 * @return JSON-строку
 */
fun dashboard(title: String, build: DashboardBuilder.() -> Unit): String {
    val builder = DashboardBuilder(title)
    builder.build()
    val dashboard = builder.createDashboard()
    return dashboard.toJson().toString()
}
