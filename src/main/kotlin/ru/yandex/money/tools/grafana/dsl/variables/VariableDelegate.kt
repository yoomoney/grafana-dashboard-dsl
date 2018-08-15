package ru.yandex.money.tools.grafana.dsl.variables

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class VariableDelegate(
    private val variableFactory: (String) -> Variable,
    private val variables: MutableList<Variable>
) {

    operator fun provideDelegate(thisRef: Nothing?, property: KProperty<*>): ReadOnlyProperty<Nothing?, Variable> {
        val variable = variableFactory(property.name)
        variables += variable

        return object : ReadOnlyProperty<Nothing?, Variable> {
            override fun getValue(thisRef: Nothing?, property: KProperty<*>) = variable
        }
    }
}
