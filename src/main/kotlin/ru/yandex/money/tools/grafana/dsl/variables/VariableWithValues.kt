package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Variable with values that can be selected at dashboard screen.
 *
 * It's recommended to use instances of this class as delegates for other variable implementations.
 *
 * @param delegate a variable delegate
 * @param values a list of variables that can be selected
 * @param selectedIndex index of selected value
 *
 * @author Dmitry Komarov
 * @since 20.03.2019
 */
class VariableWithValues(
    private val delegate: Variable,
    private val values: List<VariableValue>,
    private val selectedIndex: Int = 0
) : Variable by delegate {

    override fun toJson() = jsonObject(delegate.toJson()) {
        "current" to values[selectedIndex]
        "options" to JSONArray(values.mapIndexed { i, it ->
            jsonObject(it.toJson()) {
                "selected" to (i == selectedIndex)
            }
        })
    }
}
