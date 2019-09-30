package ru.yandex.money.tools.grafana.dsl.generators

import ru.yandex.money.tools.grafana.dsl.panels.Position
import kotlin.math.min

/**
 * Simple realization generator id and positions for panels and rows.
 * @author Aleksey Antufev
 * @since 27.09.2019
 */
class SimplePanelLayoutGenerator : PanelLayoutGenerator {

    private var id: Long = 1L
    private var x = 0
    private var y = 0

    override fun nextPosition(width: Int, height: Int): Position {

        val nextPosition = Position(
            x = x,
            y = y,
            width = min(width, maxWidth),
            height = height
        )

        x += width
        if (x >= maxWidth) {
            x = 0
        }

        y += height

        return nextPosition
    }

    override fun nextId() = id++

    companion object {
        private const val maxWidth: Int = 24
    }
}
