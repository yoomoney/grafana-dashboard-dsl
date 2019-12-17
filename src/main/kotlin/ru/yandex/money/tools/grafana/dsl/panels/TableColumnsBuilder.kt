package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.DashboardElement

/**
 * Table column builder
 * @author Aleksandr Korkin (avkorkin@yamoney.ru)
 * @since 16.12.2019
 */
@DashboardElement
class TableColumnsBuilder {
    val columns = mutableListOf<TableColumn>()
    infix fun String.to(name: String) {
        columns += TableColumn(name = name, value = this)
    }
}
