package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator

/**
 * Builder for panels containers, for example: strings.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
@DashboardElement
interface PanelContainerBuilder {

    val panels: MutableList<Panel>
    val panelLayoutGenerator: PanelLayoutGenerator
}
