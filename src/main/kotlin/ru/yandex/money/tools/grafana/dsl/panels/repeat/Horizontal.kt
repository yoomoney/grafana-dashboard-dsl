package ru.yandex.money.tools.grafana.dsl.panels.repeat

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

class Horizontal(private val minWidth: Int? = null) : Direction {

    override fun toJson() = jsonObject {
        "repeatDirection" to "h"
        "minSpan" to minWidth
    }
}
