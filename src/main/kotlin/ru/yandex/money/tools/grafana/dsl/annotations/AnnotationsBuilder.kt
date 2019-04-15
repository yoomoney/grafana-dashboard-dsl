package ru.yandex.money.tools.grafana.dsl.annotations

import ru.yandex.money.tools.grafana.dsl.DashboardElement

/**
 * Mutable builder for building annotations collection.
 */
@DashboardElement
class AnnotationsBuilder {

    internal val annotations: MutableList<Annotation> = mutableListOf()

    /**
     * Adds new annotation to [annotations] with given [name].
     */
    fun annotation(name: String, build: AnnotationBuilder.() -> Annotation) {
        val builder = AnnotationBuilder(name)
        annotations += builder.build()
    }
}
