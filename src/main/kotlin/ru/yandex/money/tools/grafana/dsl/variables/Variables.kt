package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

/**
 * Переменные, используемые для запроса метрик.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Variables(private val variables: List<Variable>) : Json<JSONArray> by JsonArray(variables)
