package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Represents "legend" block on a panel
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
class Legend(
    private val alignAsTable: Boolean = true,
    private val avg: Boolean = true,
    private val current: Boolean = true,
    private val hideEmpty: Boolean = true,
    private val hideZero: Boolean = true,
    private val max: Boolean = true,
    private val min: Boolean = true,
    private val rightSide: Boolean = false,
    private val show: Boolean = true,
    private val sort: Sort? = Sort.AVG,
    private val sortDesc: Boolean = true,
    private val total: Boolean = true,
    private val values: Boolean = true
) : Json<JSONObject> {

    companion object Factory {

        /**
         * Default block "legend"
         * Metrics will be displayed as table with columns:  "name", "min", "max", "avg", "current", "total"
         */
        val DEFAULT = Legend(
                alignAsTable = true,
                avg = true,
                current = true,
                hideEmpty = true,
                hideZero = true,
                max = true,
                min = true,
                rightSide = false,
                show = true,
                sort = Sort.AVG,
                sortDesc = true,
                total = true,
                values = true
        )

        /**
         * Empty block "legend"
         * Only metrics names will be displayed in a flat list
         */
        val EMPTY = Legend(
                alignAsTable = false,
                avg = false,
                current = false,
                hideEmpty = false,
                hideZero = false,
                max = false,
                min = false,
                rightSide = false,
                show = true,
                sort = null,
                sortDesc = false,
                total = false,
                values = false
        )
    }

    override fun toJson() = jsonObject {
        "alignAsTable" to alignAsTable
        "avg" to avg
        "current" to current
        "hideEmpty" to hideEmpty
        "hideZero" to hideZero
        "max" to max
        "min" to min
        "rightSide" to rightSide
        "show" to show
        "sort" to sort?.value
        "sortDesc" to sortDesc
        "total" to total
        "values" to values
    }
}

/**
 * Sorting type for legend block values
 */
enum class Sort(val value: String) {
    AVG("avg"),
    MIN("min"),
    MAX("max"),
    CURRENT("current"),
    TOTAL("total")
}
