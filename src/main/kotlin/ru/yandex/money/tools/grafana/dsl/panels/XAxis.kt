package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.emptyJsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Describes block "xAxis" in Axes block
 *
 * @property show ability to hide the axis

 * @author Aleksandr Korkin
 * @since 02.10.2019
 */

class XAxis(private val show: Boolean = true, private val mode: XAxisMode = Time()) : Json<JSONObject> {
    override fun toJson() = jsonObject {
        "show" to show
        embed(mode)
    }
}

/**
 * XAxis mode marker
 */
interface XAxisMode : Json<JSONObject>

/**
 * Implementation of the X-axis as a histogram
 *
 * @property buckets Number of baskets for the histogram
 */
class Histogram(
    private val buckets: Int? = null
) : XAxisMode {

    override fun toJson() = jsonObject {
        "buckets" to buckets
        "mode" to "histogram"
        "values" to emptyJsonArray()
    }
}

/**
 * Implementation of the X-axis as a time series
 */
class Time : XAxisMode {
    override fun toJson() = jsonObject {
        "mode" to "time"
        "values" to emptyJsonArray()
    }
}

/**
 * Implementation of the X-axis as a series graph
 *
 * @property value Specifies the type of grouping for the series
 */
class Series(
    private val value: ValueType = ValueType.TOTAL
) : XAxisMode {

    override fun toJson() = jsonObject {
        "mode" to "series"
        "values" to jsonArray[value.type]
    }

    /**
     * Type of grouping metrics in XAxis block
     *
     * @author Aleksandr Korkin
     * @since 02.10.2019
     */
    enum class ValueType(val type: String) {
        /**
         * Average of all values returned from metric query
         */
        AVG("avg"),

        /**
         * Minimum of all values returned from metric query
         */
        MIN("min"),

        /**
         * Maximum of all values returned from the metric query
         */
        MAX("max"),

        /**
         * Sum of all values returned from metric query
         */
        TOTAL("total"),

        /**
         * Count of all values returned from metric query
         */
        COUNT("count"),

        /**
         * Last value returned from metric query
         */
        CURRENT("current")
    }
}
