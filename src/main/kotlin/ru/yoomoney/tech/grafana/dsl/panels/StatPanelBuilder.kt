package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.Graphite
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.metrics.Metrics
import ru.yoomoney.tech.grafana.dsl.metrics.MetricsBuilder
import ru.yoomoney.tech.grafana.dsl.metrics.ReferenceMetricsHolder

/**
 * Builder for a [StatPanel].
 *
 * @author lokshin (lokshin@yamoney.ru)
 * @since 08.08.2021
 */
class StatPanelBuilder(private val title: String, private val generator: PanelLayoutGenerator) : PanelBuilder {

    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var options: StatPanel.StatPanelOptions? = null

    var datasource: Datasource = Graphite

    var description: String? = null

    val metrics = ReferenceMetricsHolder()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        propertiesSetters += propertiesSetter
    }

    fun metrics(build: MetricsBuilder<Graphite>.() -> Unit) {
        val builder = MetricsBuilder<Graphite>()
        builder.build()
        metrics += builder.metrics
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
        StatPanel(
            MetricPanel(
                BasePanel(
                    id = generator.nextId(),
                    title = title,
                    position = generator.nextPosition(bounds.first, bounds.second),
                    description = description
                ),
                datasource = datasource,
                metrics = Metrics(metrics.metrics)
            ),
            options = options
        )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.statPanel(title: String = "", build: StatPanelBuilder.() -> Unit) {
    val builder = StatPanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
