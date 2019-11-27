package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Variable that uses query from given datasource to define variable values.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class QueryVariable private constructor(
    private val delegate: Variable,
    private val datasource: Datasource,
    private val refreshMode: RefreshMode,
    private val regex: String?,
    private val sortOrder: SortOrder,
    private val multiValuesAllowed: Boolean,
    private val includeAllValue: Boolean,
    private val allValue: String?,
    private val tags: VariableTags?
) : Variable by delegate {

    /**
     * Public constructor for [QueryVariable].
     *
     * @param name see [BaseVariable.name]
     * @param displayName see [BaseVariable.displayName]
     * @param hidingMode see [BaseVariable.hidingMode]
     * @param query query that will be used to retrieve variable values from given datasource. Syntax of query depends
     * on datasource.
     * @param datasource a datasource for variable values
     * @param refreshMode a strategy that defines when variable values will be queried and updated.
     * @param regex a regular expression that can be used to filter values that received from datasource. If this value
     * is missing all received values will be used.
     * @param sortOrder an order that declares how values will be sorted. It may matter if this variable is used for
     * templating purposes.
     * @param multiValuesAllowed enables multiple values selected at the same time
     * @param includeAllValue when **true** the *All* option is available to select
     * @param allValue optional value that will be used when *All* option is selected. Ignored by Grafana
     * when [includeAllValue] is false
     * @param tags used to group the values into selectable tags
     */
    constructor(
        name: String,
        displayName: String?,
        hidingMode: HidingMode,
        query: String,
        datasource: Datasource,
        refreshMode: RefreshMode,
        regex: String?,
        sortOrder: SortOrder,
        multiValuesAllowed: Boolean,
        includeAllValue: Boolean,
        allValue: String?,
        tags: VariableTags?
    ) : this(
        delegate = VariableWithQuery(
            delegate = BaseVariable(
                name = name,
                displayName = displayName,
                type = "query",
                hidingMode = hidingMode
            ),
            query = query
        ),
        datasource = datasource,
        refreshMode = refreshMode,
        regex = regex,
        sortOrder = sortOrder,
        multiValuesAllowed = multiValuesAllowed,
        includeAllValue = includeAllValue,
        allValue = allValue,
        tags = tags
    )

    override fun toJson() = jsonObject(delegate.toJson()) {
        "datasource" to datasource.asDatasourceName()
        "refresh" to refreshMode.mode
        "regex" to regex
        "sort" to sortOrder.code
        "multi" to multiValuesAllowed
        "includeAll" to includeAllValue
        if (includeAllValue) {
            "allValue" to allValue
        }
        if (tags != null) {
            embed(tags)
        }
    }

    /**
     * Mutable builder for setting up unnecessary properties for [QueryVariable].
     */
    @DashboardElement
    class Builder {

        var displayName: String? = null

        var hidingMode: HidingMode = HidingMode.VARIABLE

        var refreshMode: RefreshMode = RefreshMode.ON_DASHBOARD_LOAD

        var regex: String? = null

        var sortOrder: SortOrder = SortOrder.NUMERICAL_ASC

        var multiValuesAllowed: Boolean = false

        var includeAllValue: Boolean = false

        var allValue: String? = null

        var tags: VariableTags? = null
    }
}
