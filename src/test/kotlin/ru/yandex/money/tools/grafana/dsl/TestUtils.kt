package ru.yandex.money.tools.grafana.dsl

import org.apache.commons.io.IOUtils
import org.skyscreamer.jsonassert.JSONAssert

infix fun String.shouldEqualToJson(json: String) {
    JSONAssert.assertEquals(json, this, true)
}

fun Any.jsonFile(fileName: String): String {
    return IOUtils.toString(this::class.java.getResourceAsStream(fileName))
}
