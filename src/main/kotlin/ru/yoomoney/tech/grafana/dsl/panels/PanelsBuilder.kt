package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.variables.Variable

/**
 * Panels builder.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class PanelsBuilder(override val panelLayoutGenerator: PanelLayoutGenerator) : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>()

    /**
     * This method is used to support backward compatibility.
     */
    fun row(title: String, build: RowBuilder.() -> Unit) {
        row(title, null, false, build)
    }

    fun row(title: String, repeatFor: Variable? = null, collapsed: Boolean = false, build: RowBuilder.() -> Unit) {
        val builder = RowBuilder(title, repeatFor?.name, panelLayoutGenerator, collapsed)
        builder.build()
        panels += builder.createRow()
    }
}
