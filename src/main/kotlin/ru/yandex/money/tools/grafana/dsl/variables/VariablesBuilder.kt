package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.time.Duration

/**
 * Mutable builder to create variables on dashboard.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class VariablesBuilder {

    /**
     * Variables on dashboard.
     *
     * This field made public to make [VariablesBuilder] extensible.
     */
    val variables = mutableListOf<Variable>()

    /**
     * Creates *interval* variable.
     *
     * @param durations durations that used as values for variable
     * @param build builder function to setting up variable properties
     */
    fun interval(vararg durations: Duration, build: IntervalVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = IntervalVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            IntervalVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                values = durations,
                auto = builder.auto,
                stepCount = builder.stepCount,
                minInterval = builder.minInterval
            )
        }
    }

    /**
     * Creates *query* variable.
     *
     * @param datasource datasource that used to query variable values
     * @param query a string query that will be executed to retrieve variable values
     * @param build builder function to setting up variable properties
     */
    fun query(datasource: Datasource, query: String, build: QueryVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = QueryVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            QueryVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                query = query,
                datasource = datasource,
                refreshMode = builder.refreshMode,
                regex = builder.regex,
                sortOrder = builder.sortOrder,
                multiValuesAllowed = builder.multiValuesAllowed,
                includeAllValue = builder.includeAllValue,
                allValue = builder.allValue
            )
        }
    }

    /**
     * Creates *custom* variable.
     *
     * @param values values of variable
     * @param build builder function to setting up variable properties
     */
    fun custom(vararg values: String, build: CustomVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = CustomVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            CustomVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                values = values,
                multiValuesAllowed = builder.multiValuesAllowed,
                includeAllValue = builder.includeAllValue,
                allValue = builder.allValue
            )
        }
    }

    /**
     * Creates *constant*.
     *
     * @param value a value of constant
     * @param build builder function to setting up variable properties
     */
    fun constant(value: String, build: ConstantVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = ConstantVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            ConstantVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                value = value
            )
        }
    }

    /**
     * Creates *textBox* variable.
     *
     * @param defaultValue a default value of text box
     * @param build builder function to setting up variable properties
     */
    fun textBox(defaultValue: String = "", build: TextBoxVariable.Builder.() -> Unit = {}): VariableDelegate {
        val builder = TextBoxVariable.Builder()
        builder.build()

        return VariableDelegate(variables) {
            TextBoxVariable(
                name = it,
                displayName = builder.displayName,
                hidingMode = builder.hidingMode,
                defaultValue = defaultValue
            )
        }
    }
}
