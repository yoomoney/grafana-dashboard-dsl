package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.time.m

/**
 * Variable with a "Interval" type.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
@Deprecated("This class will be deleted in 2.0.0", replaceWith = ReplaceWith("IntervalVariable"))
class Interval(name: String, durations: Array<out Duration>) : Variable by IntervalVariable(
    name = name,
    displayName = null,
    hidingMode = HidingMode.NONE,
    values = durations,
    auto = true,
    stepCount = 30,
    minInterval = 1.m
)
