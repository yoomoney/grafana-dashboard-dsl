package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Describes block "Left Y" or "Right Y" for Y-axis panel in Legend block
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
class YAxis(
    private val format: String = SHORT,
    private val scale: Scale = Scale.LINEAR,
    private val show: Boolean = true,
    private val decimals: Int? = 1,
    private val min: Int? = null,
    private val max: Int? = null
) : Json<JSONObject> {

    companion object Format {
        /**
         * short
         */
        val SHORT = "short"
        /**
         * bytes
         */
        val BYTES = "bytes"
        /**
         * Decimal bytes
         */
        val DECIMAL_BYTES = "decbytes"
        /**
         * Milliseconds
         */
        val MILLISECONDS = "ms"
        /**
         * Percent 0-100
         */
        val PERCENT = "percent"
        val NONE = "none"
    }

    enum class Scale(val logBase: Int) {
        LINEAR(1),
        LOG2(2),
        LOG10(10),
        LOG32(32),
        LOG1024(1024)
    }

    override fun toJson() = jsonObject {
        "format" to format
        "logBase" to scale.logBase
        "show" to show
        "decimals" to decimals
        if (min != null) {
            "min" to min
        }
        if (max != null) {
            "max" to max
        }
    }
}
