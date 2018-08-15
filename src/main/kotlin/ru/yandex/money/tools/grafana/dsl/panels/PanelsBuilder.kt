package ru.yandex.money.tools.grafana.dsl.panels

/**
 * Билдер панелей.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class PanelsBuilder : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>()

    fun row(title: String, build: RowBuilder.() -> Unit) {
        val builder = RowBuilder(title)
        builder.build()
        panels += builder.createRow()
    }
}
