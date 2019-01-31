package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Basic data of a varaible.
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class BaseVariable(
    override val name: String,
    private val hidden: Boolean = true,
    private val includeAll: Boolean,
    private val query: String,
    private val type: String
) : Variable {

    override fun asVariable() = "$$name"

    override fun toJson() = jsonObject {
        "name" to name
        "hide" to (if (hidden) HIDE_VARIABLE else SHOW_VARIABLE)
        "includeAll" to includeAll
        "query" to query
        "type" to type
        "multi" to false
        "refresh" to REFRESH_ON_TIME_RANGE_CHANGE
    }

    companion object {
        private const val HIDE_VARIABLE = 2
        private const val SHOW_VARIABLE = 0
        private const val REFRESH_ON_TIME_RANGE_CHANGE = 2
    }
}
