package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.json.emptyJsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.time.Duration

/**
 * Обычный график со стандартными настройками легенды, осей и threshold'ов.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
class GraphPanel(
    private val basePanel: Panel,
    private val type: String,
    private val timeShift: Duration? = null,
    private val stack: Boolean = true
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "graph"
        "legend" to jsonObject {
            "alignAsTable" to true
            "avg" to true
            "current" to true
            "hideEmpty" to true
            "hideZero" to true
            "max" to true
            "min" to true
            "rightSide" to false
            "show" to true
            "sort" to "avg"
            "sortDesc" to true
            "total" to true
            "values" to true
        }
        "bars" to (type == "bars")
        "lines" to (type == "lines")
        "dashes" to false
        "steppedLine" to false
        "points" to false
        "stack" to stack
        "dashLength" to 10
        "decimals" to 2
        "linewidth" to 2
        "spaceLength" to 10
        "pointradius" to 5
        "percentage" to false
        "renderer" to "flot"
        "fill" to 0
        "seriesOverrides" to emptyJsonArray()
        "thresholds" to emptyJsonArray()
        "timeShift" to timeShift?.toString()
        "xaxis" to jsonObject {
            "mode" to "time"
            "show" to true
            "values" to emptyJsonArray()
        }
        "yaxis" to jsonObject {
            "align" to false
        }
        "yaxes" to jsonArray[
            jsonObject {
                "format" to "short"
                "logBase" to 1
                "show" to true
            },
            jsonObject {
                "format" to "short"
                "logBase" to 1
                "show" to true
            }
        ]
    }
}
