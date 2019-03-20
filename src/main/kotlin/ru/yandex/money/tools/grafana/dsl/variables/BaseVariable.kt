package ru.yandex.money.tools.grafana.dsl.variables

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Basic data of a variable, e.g. name and type.
 *
 * It's recommended to use this class as some variable implementation's delegate (see [VariableWithQuery] for example).
 *
 * Mutable builder is not provided for this class because it's not recommended to use this class as is.
 *
 * @param name name of a variable. You can refer to variable via this name.
 * @param type type of a variable
 * @param displayName display name of a variable. This name will be used as a label for variable at dashboard
 * when [HidingMode.NONE] is used. When this value is missed so [name] will be used for variable label.
 * @param hidingMode hiding mode of a variable
 *
 * @author Dmitry Komarov
 * @since 25.07.2018
 */
class BaseVariable(
    override val name: String,
    private val type: String,
    private val displayName: String?,
    private val hidingMode: HidingMode
) : Variable {

    override fun toJson() = jsonObject {
        "name" to name
        "type" to type
        "label" to displayName
        "hide" to hidingMode.mode
    }

    companion object {

        /**
         * Create a [BaseVariable] to support backward compatibility.
         *
         * @param name name of a variable
         * @param hidden if **true** then variable will not be shown at dashboard screen
         * @param includeAll if **true** then *All* option will be available for variable value
         * @param query a string query for variable
         * @param type type of a variable
         *
         * @return variable
         */
        @Deprecated("This method created to support backward compatibility and will be deleted in 2.0.0")
        operator fun invoke(
            name: String,
            hidden: Boolean = true,
            includeAll: Boolean,
            query: String,
            type: String
        ): Variable {
            val delegate = VariableWithQuery(
                delegate = BaseVariable(
                    name = name,
                    type = type,
                    displayName = null,
                    hidingMode = if (hidden) HidingMode.VARIABLE else HidingMode.NONE
                ),
                query = query
            )

            return object : Variable by delegate {
                override fun toJson(): JSONObject = jsonObject(delegate.toJson()) {
                    "includeAll" to includeAll
                    "multi" to false
                    "refresh" to RefreshMode.ON_TIME_RANGE_CHANGE.mode
                }
            }
        }
    }
}
