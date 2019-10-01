package ru.yandex.money.tools.grafana.dsl.generators

import ru.yandex.money.tools.grafana.dsl.panels.Position

/**
 * Generates id and position for panels and rows.
 * @author Aleksey Antufev
 * @since 27.09.2019
 */
interface PanelLayoutGenerator {
    fun nextId(): Long

    fun nextPosition(width: Int = 0, height: Int = 9): Position
}
