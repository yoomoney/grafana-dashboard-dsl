package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Panels builder.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class PanelsBuilder : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>()

    fun row(title: String, repeatFor: Variable? = null, build: RowBuilder.() -> Unit) {
        val builder = RowBuilder(title, repeatFor?.name)
        builder.build()
        panels += builder.createRow()
    }
}
