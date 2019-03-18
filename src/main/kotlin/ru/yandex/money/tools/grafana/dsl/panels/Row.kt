package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.json.emptyJsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Row on a dashboard.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Row(
    private val basePanel: Panel,
    private val repeat: String?
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "row"
        "repeat" to repeat
        "collapsed" to false
        "panels" to emptyJsonArray()
    }
}
