package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.datasource.Datasource

/**
 * Билдер для переменных типа "Query".
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
@DashboardElement
class QueryBuilder(private val query: String) {

    var regex = ".*"

    internal fun createQuery(name: String, datasource: Datasource) = Query(name, datasource, query, regex)
}
