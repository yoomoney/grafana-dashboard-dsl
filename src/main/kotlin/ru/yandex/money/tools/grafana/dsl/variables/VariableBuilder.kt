package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.time.m

/**
 * Variable builder.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@Deprecated("This class will be deleted in 2.0.0", ReplaceWith("VariablesBuilder"))
@DashboardElement
class VariableBuilder(private val datasource: Datasource?) {

    private lateinit var variable: (String) -> Variable

    fun query(query: String, build: QueryBuilder.() -> Unit) {
        if (datasource == null) {
            throw IllegalStateException("Datasource must be specified for Query variable")
        }

        val builder = QueryBuilder(query)
        builder.build()
        variable = { builder.createQuery(it, datasource) }
    }

    fun interval(vararg durations: Duration) {
        variable = {
            IntervalVariable(
                name = it,
                displayName = null,
                hidingMode = HidingMode.NONE,
                values = durations,
                auto = true,
                stepCount = 30,
                minInterval = 1.m
            )
        }
    }

    internal fun createDelegate(variables: MutableList<Variable>) = VariableDelegate(variables, variable)
}
