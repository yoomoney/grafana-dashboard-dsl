package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.datasource.Graphite
import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.metrics.Metrics
import ru.yoomoney.tech.grafana.dsl.metrics.MetricsBuilder
import ru.yoomoney.tech.grafana.dsl.metrics.ReferenceMetricsHolder
import ru.yoomoney.tech.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Repeat
import ru.yoomoney.tech.grafana.dsl.panels.repeat.RepeatBuilder
import ru.yoomoney.tech.grafana.dsl.time.Duration
import ru.yoomoney.tech.grafana.dsl.variables.Variable

@DashboardElement
class TablePanelBuilder(
    private val title: String,
    private val panelLayoutGenerator: PanelLayoutGenerator
) : PanelBuilder {

    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    var stack = false

    val metrics = ReferenceMetricsHolder()

    var description: String? = null

    var timeFrom: Duration? = null

    var columns: List<TableColumn> = emptyList()

    var styles: List<ColumnStyle> = emptyList()

    var transform: TableTransform = TableTransform.TIMESERIES_AGGREGATIONS

    private var repeat: Repeat? = null

    private val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        propertiesSetters += propertiesSetter
    }

    fun metrics(build: MetricsBuilder<Graphite>.() -> Unit) {
        val builder = MetricsBuilder<Graphite>()
        builder.build()
        metrics += builder.metrics
        seriesOverrides += builder.seriesOverrides
    }

    fun <T : Datasource>metrics(datasource: T, build: MetricsBuilder<T>.() -> Unit) {
        val builder = MetricsBuilder<T>()
        builder.build()
        metrics += builder.metrics
        seriesOverrides += builder.seriesOverrides
        this.datasource = datasource
    }

    fun repeat(variable: Variable, build: RepeatBuilder.() -> Unit) {
        val builder = RepeatBuilder(variable)
        builder.build()
        repeat = builder.createRepeat()
    }

    fun columns(build: TableColumnsBuilder.() -> Unit) {
        val builder = TableColumnsBuilder()
        builder.build()
        columns = builder.columns
    }

    fun styles(build: ColumnStyleBuilder.() -> Unit) {
        val builder = ColumnStyleBuilder()
        builder.build()
        styles = builder.styles
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
        TablePanel(
            MetricPanel(
                BasePanel(
                    id = panelLayoutGenerator.nextId(),
                    title = title,
                    position = panelLayoutGenerator.nextPosition(bounds.first, bounds.second),
                    description = description
                ),
                datasource = datasource,
                metrics = Metrics(metrics.metrics)
            ),
            timeFrom = timeFrom,
            columns = columns,
            styles = styles,
            repeat = repeat,
            transform = transform
        )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.tablePanel(title: String, build: TablePanelBuilder.() -> Unit) {
    val builder = TablePanelBuilder(title, panelLayoutGenerator)
    builder.build()
    panels += builder.createPanel()
}
