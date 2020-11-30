package ru.yoomoney.tech.grafana.dsl.variables

import ru.yoomoney.tech.grafana.dsl.json.jsonObject

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
}
