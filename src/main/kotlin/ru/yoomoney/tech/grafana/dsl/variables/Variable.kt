package ru.yoomoney.tech.grafana.dsl.variables

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json

/**
 * Variable in Grafana.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
interface Variable : Json<JSONObject> {

    /**
     * Variable name.
     */
    val name: String

    /**
     * Returns string representation of this variable.
     */
    fun asVariable(): String = "$$name"
}
