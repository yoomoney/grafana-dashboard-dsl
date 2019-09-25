package ru.yandex.money.tools.grafana.dsl.dashboard

import org.json.JSONArray
import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.annotations.Annotations
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.panels.Panels
import ru.yandex.money.tools.grafana.dsl.time.Refresh
import ru.yandex.money.tools.grafana.dsl.time.Time
import ru.yandex.money.tools.grafana.dsl.time.d
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.s
import ru.yandex.money.tools.grafana.dsl.variables.Variables

/**
 * Dashboard. Represents root of JSON-document, used for importing into Grafana.
 *
 * @property title Display title
 * @property time Metrics time range
 * @property refresh Metrics refresh rate
 * @property tags Tags
 * @property variables Variables that should be reused for querying metrics
 * @property panels Panels
 * @property annotations Annotations that displayed on graphs. Has default value for backward compatibility.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Dashboard(
    private val uid: String? = null,
    private val title: String,
    private val time: Time,
    private val refresh: Refresh,
    private val tags: Tags,
    private val variables: Variables,
    private val panels: Panels,
    private val annotations: Annotations = Annotations(emptyList())
) : Json<JSONObject> {

    override fun toJson() = jsonObject {
        "uid" to uid
        "title" to title
        "time" to time
        "refresh" to refresh.asRefreshPeriod()
        "tags" to tags
        "panels" to panels
        "templating" to jsonObject {
            "list" to variables
        }
        "annotations" to jsonObject {
            "list" to annotations
        }
        "editable" to false
        "graphTooltip" to 0
        "links" to JSONArray()
        "schemaVersion" to 16
        "style" to "dark"
        "timepicker" to jsonObject {
            "refresh_intervals" to jsonArray[5.s, 10.s, 30.s, 1.m, 5.m, 15.m, 30.m, 1.h, 2.h, 1.d]
            "time_options" to jsonArray[5.m, 15.m, 1.h, 6.h, 12.h, 24.h, 2.d, 7.d, 30.d]
        }
        "timezone" to "browser"
    }
}
