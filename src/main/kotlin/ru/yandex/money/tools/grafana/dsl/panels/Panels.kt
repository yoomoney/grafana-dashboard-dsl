package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

/**
 * Панели дэшборда.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Panels(private val panels: List<Panel>) : Json<JSONArray> by JsonArray(panels)
