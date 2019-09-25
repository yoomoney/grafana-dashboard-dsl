package ru.yandex.money.tools.grafana.dsl.panels.repeat

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

class Vertical : Direction {

    override fun toJson() = jsonObject {
        "repeatDirection" to "v"
    }
}
