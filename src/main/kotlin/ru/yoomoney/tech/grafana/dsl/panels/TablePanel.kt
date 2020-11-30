package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.json.JsonArray
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Repeat
import ru.yoomoney.tech.grafana.dsl.time.Duration

/**
 * The table panel is very flexible, supporting both multiple modes for time series as well as for table,
 * annotation and raw JSON data.
 * It also provides date formatting and value formatting and coloring options.
 *
 * @author Aleksandr Korkin
 * @since 12/12/19
 */
class TablePanel(
    private val basePanel: Panel,
    private val timeFrom: Duration? = null,
    private val repeat: Repeat? = null,
    private val styles: List<ColumnStyle> = emptyList(),
    private val columns: List<TableColumn> = emptyList(),
    private val transform: TableTransform
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "table"
        "timeFrom" to timeFrom?.toString()
        "columns" to JsonArray(columns)
        "fontSize" to "100%"
        "styles" to JsonArray(styles)
        "transform" to transform.transform
        if (repeat != null) {
            embed(repeat)
        }
    }
}
