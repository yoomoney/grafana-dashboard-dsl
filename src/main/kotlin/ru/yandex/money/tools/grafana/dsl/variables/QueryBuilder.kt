package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource

/**
 * Builder for "Query" type variables.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@Deprecated("This class will be deleted in 2.0.0", ReplaceWith("QueryVariable.Builder"))
@DashboardElement
class QueryBuilder(private val query: String) {

    var regex = ".*"

    internal fun createQuery(name: String, datasource: Datasource) = QueryVariable(
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
}
