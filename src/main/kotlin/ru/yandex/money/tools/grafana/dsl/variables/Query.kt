package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.datasource.Datasource

/**
 * Variable with a "Query" type.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@Deprecated("This class will be deleted in 2.0.0", replaceWith = ReplaceWith("QueryVariable"))
class Query(
    name: String,
    datasource: Datasource,
    query: String,
    regex: String
) : Variable by QueryVariable(
    name = name,
    displayName = null,
    hidingMode = HidingMode.VARIABLE,
    query = query,
    datasource = datasource,
    refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE,
    regex = regex,
    sortOrder = SortOrder.NUMERICAL_ASC,
    multiValuesAllowed = false,
    includeAllValue = true,
    allValue = null
)
