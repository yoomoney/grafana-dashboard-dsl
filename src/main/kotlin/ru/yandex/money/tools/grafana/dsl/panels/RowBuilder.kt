package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.generators.PanelLayoutGenerator

/**
 * Builder for a string, containing panels.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class RowBuilder(
    title: String,
    repeat: String?,
    override val panelLayoutGenerator: PanelLayoutGenerator
) : PanelContainerBuilder {

    override val panels = mutableListOf<Panel>(
            Row(BasePanel(
                id = panelLayoutGenerator.nextId(),
                title = title,
                position = panelLayoutGenerator.nextPosition(24, 1)
            ), repeat)
    )

    internal fun createRow() = panels.toList()
}
