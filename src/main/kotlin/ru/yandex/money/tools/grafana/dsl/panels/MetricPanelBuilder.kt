package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.datasource.Graphite
import ru.yandex.money.tools.grafana.dsl.generators.PanelLayoutGenerator
import ru.yandex.money.tools.grafana.dsl.generators.SimplePanelLayoutGenerator
import ru.yandex.money.tools.grafana.dsl.metrics.DashboardMetric
import ru.yandex.money.tools.grafana.dsl.metrics.Metrics
import ru.yandex.money.tools.grafana.dsl.metrics.MetricsBuilder

class MetricPanelBuilder(private val title: String) : PanelBuilder {

    private var propertiesSetter: (JSONObject) -> Unit = {}

    private val metrics = mutableListOf<DashboardMetric>()

    private val panelLayoutGenerator: PanelLayoutGenerator = SimplePanelLayoutGenerator()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        this.propertiesSetter = propertiesSetter
    }

    fun metrics(build: MetricsBuilder<Graphite>.() -> Unit) {
        val builder = MetricsBuilder<Graphite>()
        builder.build()
        metrics += builder.metrics
    }

    internal fun createPanel(): Panel = AdditionalPropertiesPanel(
            MetricPanel(
                    BasePanel(
                        id = panelLayoutGenerator.nextId(),
                        title = title,
                        position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second)
                    ),
                    datasource = datasource,
                    metrics = Metrics(metrics)
            ),
            propertiesSetter
    )
}
