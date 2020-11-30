package ru.yoomoney.tech.grafana.dsl.dashboard

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.annotations.Annotations
import ru.yoomoney.tech.grafana.dsl.dashboard.link.DashboardLinks
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.jsonArray
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.panels.Panels
import ru.yoomoney.tech.grafana.dsl.time.Refresh
import ru.yoomoney.tech.grafana.dsl.time.TimeRange
import ru.yoomoney.tech.grafana.dsl.time.d
import ru.yoomoney.tech.grafana.dsl.time.h
import ru.yoomoney.tech.grafana.dsl.time.m
import ru.yoomoney.tech.grafana.dsl.time.s
import ru.yoomoney.tech.grafana.dsl.variables.Variables

/**
 * Dashboard. Represents root of JSON-document, used for importing into Grafana.
 *
 * @property title Display title
 * @property timeRange Metrics time range
 * @property refresh Metrics refresh rate
 * @property tags Tags
 * @property variables Variables that should be reused for querying metrics
 * @property panels Panels
 * @property editable Ability to edit the dashboard
 * @property annotations Annotations that displayed on graphs. Has default value for backward compatibility.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Dashboard(
    private val uid: String? = null,
    private val title: String,
    private val timeRange: TimeRange,
    private val refresh: Refresh,
    private val tags: Tags,
    private val variables: Variables,
    private val panels: Panels,
    private val editable: Boolean,
    private val annotations: Annotations = Annotations(emptyList()),
    private val links: DashboardLinks = DashboardLinks(emptyList())
) : Json<JSONObject> {

    init {
        if (uid != null && uid.length > 40) {
            throw IllegalArgumentException("uid must be between 0 and 40")
        }
    }

    override fun toJson() = jsonObject {
        "uid" to uid
        "title" to title
        "time" to timeRange
        "refresh" to refresh.asRefreshPeriod()
        "tags" to tags
        "panels" to panels
        "templating" to jsonObject {
            "list" to variables
        }
        "annotations" to jsonObject {
            "list" to annotations
        }
        "editable" to editable
        "graphTooltip" to 0
        "links" to links
        "schemaVersion" to 16
        "style" to "dark"
        "timepicker" to jsonObject {
            "refresh_intervals" to jsonArray[5.s, 10.s, 30.s, 1.m, 5.m, 15.m, 30.m, 1.h, 2.h, 1.d]
            "time_options" to jsonArray[5.m, 15.m, 1.h, 6.h, 12.h, 24.h, 2.d, 7.d, 30.d]
        }
        "timezone" to "browser"
    }
}
