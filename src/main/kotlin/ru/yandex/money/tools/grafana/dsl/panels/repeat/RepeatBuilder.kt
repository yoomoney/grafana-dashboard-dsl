package ru.yandex.money.tools.grafana.dsl.panels.repeat

import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * TODO:
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
