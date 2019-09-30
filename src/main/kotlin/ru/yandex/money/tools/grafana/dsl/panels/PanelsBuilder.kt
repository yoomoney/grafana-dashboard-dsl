package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.generators.PanelLayoutGenerator
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Panels builder.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class PanelsBuilder(override val panelLayoutGenerator: PanelLayoutGenerator) : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>()

    /**
     * This method is used to support backward compatibility.
     */
    fun row(title: String, build: RowBuilder.() -> Unit) {
        row(title, null, build)
    }

    fun row(title: String, repeatFor: Variable? = null, build: RowBuilder.() -> Unit) {
        val builder = RowBuilder(title, repeatFor?.name, panelLayoutGenerator)
        builder.build()
        panels += builder.createRow()
    }
}
