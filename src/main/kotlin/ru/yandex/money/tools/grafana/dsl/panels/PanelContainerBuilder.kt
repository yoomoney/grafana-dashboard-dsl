package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.DashboardElement

var x = 0
var y = 0

fun nextPosition(width: Int, height: Int): Position {
    val pos = Position(x, y, width, height)
    x += width
    if (x >= 24) {
        x = 0
    }
    y += height
    return pos
}

/**
 * Builder for panels containers, for example: strings.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@DashboardElement
interface PanelContainerBuilder {

    val panels: MutableList<Panel>
}
