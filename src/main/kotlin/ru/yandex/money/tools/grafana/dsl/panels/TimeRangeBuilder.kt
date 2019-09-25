package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.time.Duration

/**
 * Builder for TimeRange.
 * @author Aleksey Antufev
 * @since 24.09.2019
 */
class TimeRangeBuilder {

    var lastTime: Duration? = null
    var timeShift: Duration? = null
    var hideTimeOverrideInfo: Boolean = false

    fun createTimeRanges(): TimeRange {
        return TimeRange(
            lastTime = lastTime,
            timeShift = timeShift,
            hideTimeOverrideInfo = hideTimeOverrideInfo
        )
    }
}
