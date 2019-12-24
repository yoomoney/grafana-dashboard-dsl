package ru.yandex.money.tools.grafana.dsl.annotations

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Graphite
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.metrics.Metric
import ru.yandex.money.tools.grafana.dsl.metrics.functions.StringMetric
import ru.yandex.money.tools.grafana.dsl.panels.Color

/**
 * An annotation with [Graphite] datasource
 *
 * @param name see [BasicAnnotation.name]
 * @param enabled see [BasicAnnotation.enabled]
 * @param hidden see [BasicAnnotation.hidden]
 * @param color see [BasicAnnotation.color]
 * @param targetQuery the query to Graphite that uses to get events
 */
class GraphiteAnnotation(
    name: String,
    enabled: Boolean,
    hidden: Boolean,
    color: Color,
    private val targetQuery: Metric
) : Annotation {

    private val annotation = BasicAnnotation(
        name = name,
        enabled = enabled,
        hidden = hidden,
        color = color,
        datasource = Graphite
    )

    override fun toJson() = jsonObject(annotation.toJson()) {
        "target" to targetQuery.asString()
    }

    /**
     * Mutable builder that used to build [GraphiteAnnotation].
     */
    @DashboardElement
    class Builder(
        private val name: String,
        private val enabled: Boolean,
        private val hidden: Boolean,
        private val color: Color
    ) {
        var targetQuery: Metric = StringMetric("")

        fun asAnnotation(): Annotation = GraphiteAnnotation(
            name = name,
            enabled = enabled,
            hidden = hidden,
            color = color,
            targetQuery = targetQuery
        )
    }
}

/**
 * Creates a [GraphiteAnnotation] with given [AnnotationBuilder] context.
 *
 * Note that all basic information will be taken from [AnnotationBuilder] so you need fill [AnnotationBuilder] with
 * relevant information before call this function.
 */
fun AnnotationBuilder.graphite(build: GraphiteAnnotation.Builder.() -> Unit): Annotation {
    val builder = GraphiteAnnotation.Builder(
        name = name,
        enabled = enabled,
        hidden = hidden,
        color = color
    )
    builder.build()
    return builder.asAnnotation()
}
