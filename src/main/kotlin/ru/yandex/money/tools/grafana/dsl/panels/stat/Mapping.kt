package ru.yandex.money.tools.grafana.dsl.panels.stat

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Value mapping interface
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
interface Mapping : Json<JSONObject> {
    val type: Int
    val name: String
}

/**
 * Mapping: value -> text
 * @author Aleksey Matveev
 * @since 03.11.2020
 * */
class ValueToTextMapping(
    private val value: String = "",
    private val text: String = ""
) : Mapping {
    override val type: Int = 1
    override val name: String = ""

    override fun toJson() = jsonObject {
        "type" to type
        "op" to "="
        "text" to text
        "value" to value
    }

    class Builder {
        var value: String = ""
        var text: String = ""

        val valueToTexts = mutableListOf<ValueToTextMapping>()

        infix fun String.to(text: String) {
            valueToTexts += ValueToTextMapping(this, text)
        }
    }
}

/**
 * Mapping: from..to -> text
 * @author Aleksey Matveev
 * @since 03.11.2020
 * */
class RangeToTextMapping(
    private val from: String = "",
    private val to: String = "",
    private val text: String = ""
) : Mapping {
    override val type: Int = 2
    override val name: String = "value to text"

    override fun toJson() = jsonObject {
        "type" to type
        "from" to from
        "text" to text
        "to" to to
    }

    class Builder {
        var from: String = ""
        var to: String = ""
        var text: String = ""

        val rangeToTexts = mutableListOf<RangeToTextMapping>()

        fun range(from: String, to: String, text: String) {
            rangeToTexts += RangeToTextMapping(from = from, to = to, text = text)
        }
    }
}