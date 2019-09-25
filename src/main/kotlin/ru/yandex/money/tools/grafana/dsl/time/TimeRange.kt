package ru.yandex.money.tools.grafana.dsl.time

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Time range.
 *
 * @property from Start
 * @property to End
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class TimeRange(private val from: Timestamp, private val to: Timestamp) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "from" to from
        "to" to to
    }
}
