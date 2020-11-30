package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator

/**
 * Builder for a string, containing panels.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class RowBuilder(
    title: String,
    repeat: String?,
    override val panelLayoutGenerator: PanelLayoutGenerator,
    private val collapsed: Boolean = false
) : PanelContainerBuilder {

    private val row: Row = Row(BasePanel(
        id = panelLayoutGenerator.nextId(),
        title = title,
        position = panelLayoutGenerator.nextPosition(24, 1)
    ), repeat, collapsed)

    override val panels = mutableListOf<Panel>(row)

    internal fun createRow() = if (!collapsed || panels.size == 1) {
        panels.toList()
    } else {
        val row: Row = row
        row.panels = Panels(panels.drop(1).toList())
        listOf(row)
    }
}
