package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.datasource.Graphite
import ru.yandex.money.tools.grafana.dsl.metrics.Metrics
import ru.yandex.money.tools.grafana.dsl.metrics.MetricsBuilder
import ru.yandex.money.tools.grafana.dsl.metrics.ReferenceMetricsHolder
import ru.yandex.money.tools.grafana.dsl.time.Duration

class GraphPanelBuilder(private val title: String) : PanelBuilder {

    private val propertiesSetters = mutableListOf<(JSONObject) -> Unit>()

    override var bounds = PanelBuilder.DEFAULT_BOUNDS

    var datasource: Datasource = Graphite

    var type = "lines"

    var timeShift: Duration? = null

    var stacked = false

    val metrics = ReferenceMetricsHolder()

    var legend = Legend.DEFAULT

    var points = false

    var pointradius = 5

    var nullPointMode = NullPointMode.NULL_AS_ZERO

    var fill = 0

    var aliasColors: AliasColors? = null

    var leftYAxis: YAxis? = null

    var rightYAxis: YAxis? = null

    override fun properties(propertiesSetter: (JSONObject) -> Unit) {
        propertiesSetters += propertiesSetter
    }

    fun aliasColors(build: AliasColorsBuilder.() -> Unit) {
        val builder = AliasColorsBuilder()
        builder.build()
        aliasColors = builder.aliasColors
    }

    fun metrics(build: MetricsBuilder.() -> Unit) {
        val builder = MetricsBuilder()
        builder.build()
        metrics += builder.metrics
    }

    internal fun createPanel() = AdditionalPropertiesPanel(
            GraphPanel(
                    MetricPanel(
                            BasePanel(
                                    id = idGenerator++,
                                    title = title,
                                    position = nextPosition(bounds.first, bounds.second)
                            ),
                            datasource = datasource,
                            metrics = Metrics(metrics.metrics)
                    ),
                    type = type,
                    timeShift = timeShift,
                    stack = stacked,
                    legend = legend,
                    points = points,
                    pointradius = pointradius,
                    nullPointMode = nullPointMode,
                    fill = fill,
                    aliasColors = aliasColors,
                    leftYAxis = leftYAxis,
                    rightYAxis = rightYAxis
            )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.graphPanel(title: String, build: GraphPanelBuilder.() -> Unit) {
    val builder = GraphPanelBuilder(title)
    builder.build()
    panels += builder.createPanel()
}
