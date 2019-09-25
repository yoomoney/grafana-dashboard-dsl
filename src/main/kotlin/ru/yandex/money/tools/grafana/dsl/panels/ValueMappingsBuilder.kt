package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.DashboardElement

@DashboardElement
class ValueMappingsBuilder<T : ValueMappingType> {

    val map: MutableMap<ValueMappingType, List<ValueMapping>> = mutableMapOf(
        ValueToTextType to mutableListOf(),
        RangeToTextType to mutableListOf()
    )

    inline fun <reified T : ValueMappingType> createValueMappings(): ValueMappings {
        val type = T::class.objectInstance ?: ValueToTextType
        val valueMappings = map[type] ?: emptyList()

        return ValueMappings(
            type,
            valueMappings
        )
    }

    fun ValueMappingsBuilder<ValueToTextType>.valueToText(build: ValueToText.Builder.() -> Unit) {
        val builder = ValueToText.Builder()
        builder.build()
        map[ValueToTextType] = builder.valueToTexts
    }

    fun ValueMappingsBuilder<RangeToTextType>.rangeToText(build: RangeToText.Builder.() -> Unit) {
        val builder = RangeToText.Builder()
        builder.build()
        map[RangeToTextType] = builder.rangeToTexts
    }
}
