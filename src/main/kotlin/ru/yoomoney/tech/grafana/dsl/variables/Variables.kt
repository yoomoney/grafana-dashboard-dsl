package ru.yoomoney.tech.grafana.dsl.variables

import org.json.JSONArray
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.JsonArray

/**
 * Variables, used in metric queries.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Variables(private val variables: List<Variable>) : Json<JSONArray> by JsonArray(variables)
