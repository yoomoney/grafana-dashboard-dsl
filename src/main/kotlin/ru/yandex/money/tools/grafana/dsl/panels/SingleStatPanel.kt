package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.panels.repeat.Repeat

/**
 * Singlestat panel presents text from defined metric.
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
class SingleStatPanel(
    private val basePanel: Panel,
    private val valueMappings: ValueMappings = ValueMappings(ValueToTextType),
    private val timerange: Timerange = Timerange(),
    private val repeat: Repeat? = null
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "singlestat"
        embed(valueMappings)
        embed(timerange)
        if (repeat != null) {
            embed(repeat)
        }
    }
}
