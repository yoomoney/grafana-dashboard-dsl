package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.DashboardElement

/**
 * Build columnStyle element
 * @author Aleksandr Korkin (avkorkin@yamoney.ru)
 * @since 16.12.2019
 */
@DashboardElement
class ColumnStyleBuilder {
    val styles = mutableListOf<ColumnStyle>()
    var decimals: Int? = null
    var unit: YAxis.Unit = YAxis.Unit.SHORT

    fun style(pattern: String, build: ColumnStyleBuilder.() -> Unit) {
        val builder = ColumnStyleBuilder()
        builder.build()
        styles += ColumnStyle(pattern = pattern, decimals = builder.decimals, unit = builder.unit)
    }
}
