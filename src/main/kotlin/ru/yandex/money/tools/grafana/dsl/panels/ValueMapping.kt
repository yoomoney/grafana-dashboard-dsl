package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

interface ValueMapping : Json<JSONObject>

class ValueToText(
    private val value: String = "",
    private val text: String = ""
) : ValueMapping {

    override fun toJson() = jsonObject {
        "op" to "="
        "text" to text
        "value" to value
    }

    class Builder {
        var value: String = ""
        var text: String = ""

        val valueToTexts = mutableListOf<ValueToText>()

        infix fun String.to(text: String) {
            valueToTexts += ValueToText(this, text)
        }
    }
}

class RangeToText(
    private val from: String = "",
    private val to: String = "",
    private val text: String = ""
) : ValueMapping {

    override fun toJson() = jsonObject {
        "from" to from
        "text" to text
        "to" to to
    }

    class Builder {
        var from: String = ""
        var to: String = ""
        var text: String = ""

        val rangeToTexts = mutableListOf<RangeToText>()

        fun range(from: String, to: String, text: String) {
            rangeToTexts += RangeToText(from = from, to = to, text = text)
        }
    }
}
