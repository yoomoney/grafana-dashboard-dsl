package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Variable that contains list of user predefined values.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
class CustomVariable private constructor(
    private val delegate: Variable,
    private val multiValuesAllowed: Boolean,
    private val includeAllValue: Boolean,
    private val allValue: String?
) : Variable by delegate {

    /**
     * Public constructor for custom variable.
     *
     * @param name see [BaseVariable.name]
     * @param displayName see [BaseVariable.displayName]
     * @param hidingMode see [BaseVariable.hidingMode]
     * @param multiValuesAllowed enables multiple values selected at the same time
     * @param includeAllValue when **true** the *All* option is available to select
     * @param allValue optional value that will be used when *All* option is selected. Ignored by Grafana
     * when [includeAllValue] is false
     * @param values array of options for this variable. This array will be serialized as a CSV string.
     */
    constructor(
        name: String,
        displayName: String?,
        hidingMode: HidingMode,
        multiValuesAllowed: Boolean,
        includeAllValue: Boolean,
        allValue: String?,
        values: Array<out String>
    ) : this(
        delegate = VariableWithValues(
            delegate = VariableWithQuery(
                delegate = BaseVariable(
                    name = name,
                    displayName = displayName,
                    hidingMode = hidingMode,
                    type = "custom"
                ),
                query = values.joinToString(",")
            ),
            values = insertAllValueIfNeeded(values, includeAllValue),
            selectedIndex = if (includeAllValue) 1 else 0
        ),
        multiValuesAllowed = multiValuesAllowed,
        includeAllValue = includeAllValue,
        allValue = allValue
    )

    override fun toJson() = jsonObject(delegate.toJson()) {
        "multi" to multiValuesAllowed
        "includeAll" to includeAllValue
        if (includeAllValue) {
            "allValue" to allValue
        }
    }

    /**
     * Mutable for builder for setting up unnecessary properties of [CustomVariable].
     */
    @DashboardElement
    class Builder {

        var displayName: String? = null

        var hidingMode: HidingMode = HidingMode.NONE

        var multiValuesAllowed: Boolean = false

        var includeAllValue: Boolean = false

        var allValue: String? = null
    }

    companion object {

        private fun insertAllValueIfNeeded(values: Array<out String>, includeAllValue: Boolean): List<VariableValue> =
            if (includeAllValue) {
                listOf(VariableValue("\$__all", "All")) + values.map { VariableValue(it) }
            } else {
                values.map { VariableValue(it) }
            }
    }
}