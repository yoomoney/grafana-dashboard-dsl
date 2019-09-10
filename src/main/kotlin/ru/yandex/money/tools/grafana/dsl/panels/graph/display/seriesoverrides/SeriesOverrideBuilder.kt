package ru.yandex.money.tools.grafana.dsl.panels.graph.display.seriesoverrides

/**
 * Builder for SeriesOverride
 * @author Aleksey Antufev
 * @since 06.09.2019
 */
class SeriesOverrideBuilder(private val alias: String) {

    var bars: Boolean = false
    var lines: Boolean = false
    var stack: Boolean = false
    var lineWidth: Int = 0

    internal fun createSeriesOverride() = SeriesOverride(
        alias = alias,
        bars = bars,
        lines = lines,
        stack = stack,
        lineWidth = lineWidth
    )
}
