package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Описывает блок "Left Y" или "Right Y" для оси Y панели в разделе Legend
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
class YAxis(
    private val format: String = YAxis.Format.SHORT,
    private val logBase: Int = 1,
    private val show: Boolean = true
) : Json<JSONObject> {

    companion object Format {
        /**
         * short
         */
        val SHORT = "short"
        /**
         * Миллисекунды
         */
        val MILLISECONDS = "ms"
    }

    override fun toJson() = jsonObject {
        "format" to format
        "logBase" to logBase
        "show" to show
    }
}
