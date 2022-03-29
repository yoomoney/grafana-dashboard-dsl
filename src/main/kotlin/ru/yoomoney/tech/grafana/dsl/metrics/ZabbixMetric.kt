package ru.yoomoney.tech.grafana.dsl.metrics

import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.json.emptyJsonArray
import ru.yoomoney.tech.grafana.dsl.json.jsonObject

/**
 * Metric for Zabbix datasource that integrates for panels.
 * @author Aleksey Antufev
 * @since 25.09.2019
 */
class ZabbixMetric private constructor(
    private val mode: Mode,
    private val group: String = "",
    private val application: String = "",
    private val host: String = "",
    private val item: String = "",
    private val showDisabledItems: Boolean = false,
    private val useCaptureGroups: Boolean = false,
    private val textFilter: String? = null
) : DashboardMetric {

    override val id: String? get() = null

    override fun toJson() = jsonObject {
        "group" to jsonObject {
            "filter" to group
        }
        "application" to jsonObject {
            "filter" to application
        }
        "host" to jsonObject {
            "filter" to host
        }
        "item" to jsonObject {
            "filter" to item
        }
        "functions" to emptyJsonArray()
        "mode" to mode.id
        "options" to jsonObject {
            "showDisabledItems" to showDisabledItems
        }
        "useCaptureGroups" to useCaptureGroups
        if (textFilter != null) {
            "textFilter" to textFilter
        }
    }

    @DashboardElement
    class Builder {

        abstract class QueryMode {
            var group = ""

            var application = ""

            var host = ""

            var item = ""

            var showDisabledItems = false

            var textFilter: String? = null
        }

        class Metric : QueryMode() {

            internal fun createMetric() = ZabbixMetric(
                mode = Mode.METRICS,
                group = group,
                application = application,
                host = host,
                item = item,
                showDisabledItems = showDisabledItems
            )
        }

        class Text : QueryMode() {
            var useCaptureGroups = false

            internal fun createText() = ZabbixMetric(
                mode = Mode.TEXT,
                group = group,
                application = application,
                host = host,
                item = item,
                showDisabledItems = showDisabledItems,
                useCaptureGroups = useCaptureGroups,
                textFilter = textFilter
            )
        }
    }

    private enum class Mode(val id: Int) {
        METRICS(0),
        TEXT(2)
    }
}
