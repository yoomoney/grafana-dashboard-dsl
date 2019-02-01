package ru.yandex.money.tools.grafana.dsl.dashboard

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Tags, used to search for dashboard in Grafana.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Tags(private val values: List<String>) : Json<JSONArray> {

    override fun toJson() = JSONArray(values)
}
