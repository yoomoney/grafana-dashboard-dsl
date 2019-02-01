package ru.yandex.money.tools.grafana.dsl.panels

/**
 * Builder for a string, containing panels.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class RowBuilder(title: String) : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>(
            Row(BasePanel(id = idGenerator++, title = title, position = nextPosition(24, 1)))
    )

    internal fun createRow() = panels.toList()
}
