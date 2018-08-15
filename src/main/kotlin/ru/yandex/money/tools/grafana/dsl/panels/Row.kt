package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.json.emptyJsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Строка на дэшборде.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Row(private val basePanel: Panel) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "row"
        "collapsed" to false
        "panels" to emptyJsonArray()
    }
}
