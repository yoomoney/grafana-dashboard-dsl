package ru.yoomoney.tech.grafana.dsl.panels.repeat

import ru.yoomoney.tech.grafana.dsl.variables.Variable

/**
 * Builder for Repeat
 * @author Aleksey Antufev
 * @since 20.09.2019
 */
class RepeatBuilder(private val variable: Variable) {

    var direction: Direction = Horizontal()

    internal fun createRepeat() = Repeat(
        variable = variable,
        direction = direction
    )
}
