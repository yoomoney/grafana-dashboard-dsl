package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.time.Duration

/**
 * Stat panel representation.
 *
 * @author lokshin (lokshin@yamoney.ru)
 * @since 08.08.2021
 */
class StatPanel(
    private val basePanel: Panel,
    private val timeShift: Duration? = null,
    private val nullValue: NullValue = NullValue.NULL,
    private val options: StatPanelOptions? = null
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "stat"
        "timeShift" to timeShift?.toString()
        "nullPointMode" to nullValue.value
        "options" to options
    }

    class StatPanelOptions(
        private val justifyMode: String? = null,
        private val orientation: String? = null,
        private val textMode: String? = null,
        private val graphMode: String? = null
    ) : Json<JSONObject> {

        override fun toJson() = jsonObject {
            "justifyMode" to justifyMode
            "orientation" to orientation
            "textMode" to textMode
            "graphMode" to graphMode
        }

    }

}
