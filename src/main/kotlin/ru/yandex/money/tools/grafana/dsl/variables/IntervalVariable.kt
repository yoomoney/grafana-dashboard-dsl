package ru.yandex.money.tools.grafana.dsl.variables

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.time.Duration
import ru.yandex.money.tools.grafana.dsl.time.m

/**
 * Variable that contains interval values that can be used in queries.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
class IntervalVariable private constructor(
    private val delegate: Variable,
    private val auto: Boolean,
    private val stepCount: Int,
    private val minInterval: Duration
) : Variable by delegate {

    /**
     * Public constructor for [IntervalVariable].
     *
     * @param name see [BaseVariable.name]
     * @param displayName see [BaseVariable.displayName]
     * @param hidingMode see [BaseVariable.hidingMode]
     * @param values intervals that will be serialized as CSV string
     * @param auto when **true** the *Auto* option will be available to select on dashboard screen. The size of interval
     * calculated as `max(timerange / stepCount, minInterval)`.
     * @param stepCount how many times should the current time range be divided to calculate auto interval. Ignored
     * when [auto] is **false**.
     * @param minInterval the minimal interval that can be calculated for auto interval.
     * Ignored when [auto] is **false**.
     */
    constructor(
        name: String,
        displayName: String?,
        hidingMode: HidingMode,
        values: Array<out Duration>,
        auto: Boolean,
        stepCount: Int,
        minInterval: Duration
    ) : this(
        delegate = VariableWithQuery(
            delegate = BaseVariable(
                name = name,
                displayName = displayName,
                type = "interval",
                hidingMode = hidingMode
            ),
            query = values.joinToString(",")
        ),
        auto = auto,
        stepCount = stepCount,
        minInterval = minInterval
    )

    override fun toJson() = jsonObject(delegate.toJson()) {
        "auto" to auto
        if (auto) {
            "auto_count" to stepCount
            "auto_min" to minInterval
        }
    }

    /**
     * Mutable builder for setting up unnecessary properties for [IntervalVariable].
     */
    @DashboardElement
    class Builder {

        var displayName: String? = null

        var hidingMode: HidingMode = HidingMode.NONE

        var auto: Boolean = true

        var stepCount: Int = 30

        var minInterval: Duration = 1.m
    }
}
