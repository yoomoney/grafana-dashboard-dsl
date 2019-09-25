package ru.yandex.money.tools.grafana.dsl.json

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.time.Duration

operator fun JSONObject.set(key: String, value: Any?) {
    this.put(key, value)
}

class JsonBuilder(private val properties: MutableMap<String, Any?> = mutableMapOf()) {

    infix fun String.to(duration: Duration?) {
        if (duration != null) {
            properties[this] = duration.toString()
        }
    }

    infix fun String.to(value: Json<*>?) {
        if (value != null) {
            properties[this] = value.toJson()
        }
    }

    infix fun String.to(obj: JSONObject) {
        properties[this] = obj
    }

    infix fun String.to(array: JSONArray) {
        properties[this] = array
    }

    infix fun String.to(value: Any?) {
        properties[this] = value
    }

    fun embed(obj: Json<JSONObject>) {
        obj.toJson().toMap().forEach { (k, v) ->
            properties[k] = v
        }
    }

    internal fun toJson() = JSONObject(properties)
}

fun jsonObject(build: JsonBuilder.() -> Unit): JSONObject {
    val builder = JsonBuilder()
    builder.build()
    return builder.toJson()
}

fun jsonObject(basic: JSONObject, build: JsonBuilder.() -> Unit): JSONObject {
    val builder = JsonBuilder(basic.toMap())
    builder.build()
    return builder.toJson()
}

fun emptyJsonArray() = JSONArray()

class JsonArrayBuilder {
    operator fun get(vararg values: Any?) = JSONArray(values.map {
        when (it) {
            is Boolean -> it
            is Int -> it
            is Long -> it
            is Json<*> -> it.toJson()
            is JSONObject -> it
            is JSONArray -> it
            else -> it?.toString()
        }
    })
}

val jsonArray = JsonArrayBuilder()
