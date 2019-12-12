package ru.yandex.money.tools.grafana.dsl.panels.repeat

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Horizontal direction singlestat group panels
 *
 * @param minWidth minimum panel width (grafana version 5.4 and later)
 * @param maxPerRow how many panels per row you want at most (grafana version 6.0 and newer)
 * @author Aleksey Antufev
 * @since 19.09.2019
 */
class Horizontal(private val minWidth: Int? = null, private val maxPerRow: Int? = null) : Direction {

    override fun toJson() = jsonObject {
        "repeatDirection" to "h"
        "minSpan" to minWidth
        "maxPerRow" to maxPerRow
    }
}
