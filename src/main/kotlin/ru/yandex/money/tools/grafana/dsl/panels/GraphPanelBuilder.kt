package ru.yandex.money.tools.grafana.dsl.panels

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.datasource.Graphite
import ru.yandex.money.tools.grafana.dsl.metrics.Metrics
import ru.yandex.money.tools.grafana.dsl.metrics.MetricsBuilder
import ru.yandex.money.tools.grafana.dsl.metrics.ReferenceMetricsHolder
import ru.yandex.money.tools.grafana.dsl.metrics.functions.Alias
import ru.yandex.money.tools.grafana.dsl.panels.graph.display.drawoptions.HoverTooltip
import ru.yandex.money.tools.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverride
import ru.yandex.money.tools.grafana.dsl.panels.graph.display.seriesoverrides.SeriesOverrideBuilder
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

    var decimals: Int? = 2

    var points = false

    var pointradius = 5

    var nullPointMode = NullPointMode.NULL_AS_ZERO

    var fill = 0

    var lineWidth = 2

    var staircase = false

    var hoverTooltip = HoverTooltip()

    var aliasColors: AliasColors? = null

    var leftYAxis: YAxis? = null

    var rightYAxis: YAxis? = null

    private val seriesOverrides: MutableList<SeriesOverride> = mutableListOf()

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

    infix fun Alias.override(build: SeriesOverrideBuilder.() -> Unit): Alias {
        val builder = SeriesOverrideBuilder(this.aliasName)
        builder.build()
        seriesOverrides += builder.createSeriesOverride()
        return this
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
                    decimals = decimals,
                    points = points,
                    pointradius = pointradius,
                    nullPointMode = nullPointMode,
                    fill = fill,
                    lineWidth = lineWidth,
                    staircase = staircase,
                    hoverTooltip = hoverTooltip,
                    aliasColors = aliasColors,
                    leftYAxis = leftYAxis,
                    rightYAxis = rightYAxis,
                    seriesOverrides = seriesOverrides
            )
    ) { json -> propertiesSetters.forEach { it(json) } }
}

fun PanelContainerBuilder.graphPanel(title: String, build: GraphPanelBuilder.() -> Unit) {
    val builder = GraphPanelBuilder(title)
    builder.build()
    panels += builder.createPanel()
}
