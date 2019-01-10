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
    private val stack: Boolean = true,
    private val legend: Legend = Legend.DEFAULT,
    private val points: Boolean = false,
    private val pointradius: Int = 5,
    private val nullPointMode: NullPointMode = NullPointMode.NULL_AS_ZERO,
    private val fill: Int = 0,
    private val aliasColors: AliasColors? = null,
    private val leftYAxis: YAxis? = null,
    private val rightYAxis: YAxis? = null
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "graph"
        "legend" to legend
        "bars" to (type == "bars")
        "lines" to (type == "lines")
        "dashes" to false
        "steppedLine" to false
        "points" to points
        "stack" to stack
        "dashLength" to 10
        "decimals" to 2
        "linewidth" to 2
        "spaceLength" to 10
        "pointradius" to pointradius
        "percentage" to false
        "renderer" to "flot"
        "fill" to fill
        "seriesOverrides" to emptyJsonArray()
        "thresholds" to emptyJsonArray()
        "timeShift" to timeShift?.toString()
        "nullPointMode" to nullPointMode.value
        "aliasColors" to aliasColors
        "xaxis" to jsonObject {
            "mode" to "time"
            "show" to true
            "values" to emptyJsonArray()
        }
        "yaxis" to jsonObject {
            "align" to false
        }
        "yaxes" to jsonArray[leftYAxis ?: YAxis(), rightYAxis ?: YAxis()]
    }
}

/**
 * Описывает значения для свойства "nullPointMode"
 */
enum class NullPointMode(val value: String) {

    CONNECTED("connected"),
    NULL("null"),
    NULL_AS_ZERO("null as zero")
}
