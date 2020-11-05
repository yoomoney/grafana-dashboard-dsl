package ru.yandex.money.tools.grafana.dsl.panels.stat

import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.panels.Panel
import ru.yandex.money.tools.grafana.dsl.panels.Timerange
import ru.yandex.money.tools.grafana.dsl.panels.repeat.Repeat

/**
 * Stat panel presents text from defined metric
 * @author Aleksey Matveev
 * @since 29.10.2020
 */
class StatPanel(
    private val basePanel: Panel,
    private val timerange: Timerange = Timerange(),
    private val repeat: Repeat? = null,
    private val fieldConfig: StatPanelFieldConfig = StatPanelFieldConfig(),
    private val options: StatPanelDisplayOptions = StatPanelDisplayOptions()
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "stat"
        embed(timerange)
        repeat?.let { embed(it) }
        "fieldConfig" to fieldConfig
        "options" to options
    }
}