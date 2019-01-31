package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.time.Duration

/**
 * Variable builder.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@DashboardElement
class VariableBuilder(private val datasource: Datasource) {

    private lateinit var variable: (String) -> Variable

    fun query(query: String, build: QueryBuilder.() -> Unit) {
        val builder = QueryBuilder(query)
        builder.build()
        variable = { builder.createQuery(it, datasource) }
    }

    fun interval(vararg durations: Duration) {
        variable = { Interval(it, durations) }
    }

    internal fun createDelegate(variables: MutableList<Variable>) = VariableDelegate(variable, variables)
}
