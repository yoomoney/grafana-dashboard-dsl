package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Переменная в Grafana.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
interface Variable : Json<JSONObject> {

    /**
     * Имя переменной.
     */
    val name: String

    fun asVariable(): String
}
