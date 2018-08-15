package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.datasource.Datasource
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Переменная типа "Query".
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Query(name: String, private val datasource: Datasource, query: String, private val regex: String) : Variable {

    override fun asVariable() = variable.asVariable()

    private val variable = BaseVariable(name = name, query = query, type = "query", includeAll = true)

    override val name get() = variable.name

    override fun toJson() = jsonObject(variable.toJson()) {
        "datasource" to datasource.asDatasourceName()
        "sort" to NUMERICAL_ASCENDING_SORT
        "regex" to regex
    }

    companion object {
        private const val NUMERICAL_ASCENDING_SORT = 3
    }
}
