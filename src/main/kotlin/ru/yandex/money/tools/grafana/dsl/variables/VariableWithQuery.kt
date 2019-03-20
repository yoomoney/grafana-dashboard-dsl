package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Variable with **query** property.
 *
 * It's recommended to use instances of this class as delegates for other variable implementations.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class VariableWithQuery(private val delegate: Variable, private val query: String) : Variable by delegate {

    override fun toJson() = jsonObject(delegate.toJson()) {
        "query" to query
    }
}
