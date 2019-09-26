@file:JvmName("ValueMappingKt")

package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

interface ValueMappingType : Json<JSONObject> {

    val name: String
    val id: Int

    override fun toJson() = jsonObject {
        "name" to name
        "value" to id
    }
}

object ValueToTextType : ValueMappingType {
    override val name: String
        get() = "value to text"
    override val id: Int
        get() = 1
}

object RangeToTextType : ValueMappingType {
    override val name: String
        get() = "range to text"
    override val id: Int
        get() = 2
}
