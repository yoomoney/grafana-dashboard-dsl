package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.DashboardElement

/**
 * Build columnStyle element
 * @author Aleksandr Korkin
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
