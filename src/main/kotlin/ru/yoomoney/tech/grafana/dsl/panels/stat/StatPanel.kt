package ru.yoomoney.tech.grafana.dsl.panels.stat

import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.panels.Panel
import ru.yoomoney.tech.grafana.dsl.panels.Timerange
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Repeat

/**
 * Stat panel presents text from defined metric
 * https://grafana.com/docs/grafana/latest/panels/visualizations/stat-panel/
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
