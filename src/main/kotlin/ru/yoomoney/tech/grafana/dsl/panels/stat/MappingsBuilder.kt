package ru.yoomoney.tech.grafana.dsl.panels.stat

/**
 * Builder for mappings tab
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
class MappingsBuilder {
    val mappings = mutableListOf<Mapping>()

    fun valueToText(build: ValueToTextMapping.Builder.() -> Unit) {
        val builder = ValueToTextMapping.Builder()
        builder.build()
        mappings.addAll(builder.valueToTexts)
    }

    fun rangeToText(build: RangeToTextMapping.Builder.() -> Unit) {
        val builder = RangeToTextMapping.Builder()
        builder.build()
        mappings.addAll(builder.rangeToTexts)
    }
}
