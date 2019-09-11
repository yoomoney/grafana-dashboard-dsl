package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.datasource.Graphite
import ru.yandex.money.tools.grafana.dsl.json.emptyJsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.metrics.Metrics

/**
 * Panel with metrics.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class MetricPanel(
    private val basePanel: Panel,
    private val datasource: Datasource = Graphite,
    private val nullValue: NullValue = NullValue.NULL,
    private val metrics: Metrics
) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "datasource" to datasource.asDatasourceName()
        "nullPointMode" to nullValue.value
        "targets" to metrics
        "editable" to true
        "links" to emptyJsonArray()
    }
}

fun PanelContainerBuilder.metricPanel(title: String, build: MetricPanelBuilder.() -> Unit) {
    val builder = MetricPanelBuilder(title)
    builder.build()
    panels += builder.createPanel()
}
