package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.generators.PanelLayoutGenerator

/**
 * Builder for panels containers, for example: strings.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@DashboardElement
interface PanelContainerBuilder {

    val panels: MutableList<Panel>
    val panelLayoutGenerator: PanelLayoutGenerator
}
