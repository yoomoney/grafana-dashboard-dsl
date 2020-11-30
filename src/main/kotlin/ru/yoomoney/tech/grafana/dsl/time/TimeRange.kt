package ru.yoomoney.tech.grafana.dsl.time

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * Time range.
 *
 * @property from Start
 * @property to End
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class TimeRange(private val from: Timestamp, private val to: Timestamp) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "from" to from
        "to" to to
    }
}
