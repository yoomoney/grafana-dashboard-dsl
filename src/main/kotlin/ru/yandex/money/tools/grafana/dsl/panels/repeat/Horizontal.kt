package ru.yandex.money.tools.grafana.dsl.panels.repeat

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Horizontal direction singlestat group panels
 *
 * @author Aleksey Antufev
 * @since 19.09.2019
 */
class Horizontal(private val minWidth: Int? = null) : Direction {

    override fun toJson() = jsonObject {
        "repeatDirection" to "h"
        "minSpan" to minWidth
    }
}
