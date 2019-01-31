package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.time.m

/**
 * Variable with a "Interval" type.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
class Interval(name: String, durations: Array<out Duration>) : Variable {

    override fun asVariable() = delegate.asVariable()

    private val delegate = BaseVariable(
            name = name,
            hidden = false,
            type = "interval",
            includeAll = false,
            query = durations.joinToString(",")
    )

    override val name get() = delegate.name

    override fun toJson() = jsonObject(delegate.toJson()) {
        "auto" to true
        "auto_count" to 30
        "auto_min" to 1.m
    }
}
