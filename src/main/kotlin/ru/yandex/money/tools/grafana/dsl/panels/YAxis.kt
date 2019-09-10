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
    private val unit: Unit = Unit.SHORT,
    private val scale: Scale = Scale.LINEAR,
    private val show: Boolean = true,
    private val decimals: Int? = null,
    private val min: Int? = null,
    private val max: Int? = null
) : Json<JSONObject> {

    enum class Unit(val unit: String) {
        SHORT("short"),
        BYTES("bytes"),
        DECIMAL_BYTES("decbytes"),
        MILLISECONDS("ms"),
        PERCENT_0_100("percent"),
        NONE("none"),
    }

    enum class Scale(val logBase: Int) {
        LINEAR(1),
        LOG2(2),
        LOG10(10),
        LOG32(32),
        LOG1024(1024)
    }

    override fun toJson() = jsonObject {
        "format" to unit.unit
        "logBase" to scale.logBase
        "show" to show
        "decimals" to decimals
        "min" to min
        "max" to max
    }
}
