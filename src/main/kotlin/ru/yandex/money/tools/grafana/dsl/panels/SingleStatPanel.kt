package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.panels.repeat.Repeat

class SingleStatPanel(
    private val basePanel: Panel,
    private val valueMappings: ValueMappings = ValueMappings(ValueToTextType),
    private val timeRange: TimeRange = TimeRange(),
    private val repeat: Repeat? = null
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "singlestat"
        embed(valueMappings)
        embed(timeRange)
        if (repeat != null) {
            embed(repeat)
        }
    }
}
