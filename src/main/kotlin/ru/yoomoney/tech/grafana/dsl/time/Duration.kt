package ru.yoomoney.tech.grafana.dsl.time

/**
 * A time-based amount of time.
 *
 * @param number time duration
 * @param unit time unit. Examples: s (seconds), m (minutes), h (hours), d (days), w (weeks).
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
class Duration(private val number: Int, private val unit: String) : Refresh {

    override fun asRefreshPeriod() = "$number$unit"

    override fun toString() = asRefreshPeriod()
}

/**
 * Returns [Duration] in seconds
 */
val Int.s get() = Duration(this, "s")

/**
 * Returns [Duration] in minutes
 */
val Int.m get() = Duration(this, "m")

/**
 * Returns [Duration] in hours
 */
val Int.h get() = Duration(this, "h")

/**
 * Returns [Duration] in days
 */
val Int.d get() = Duration(this, "d")

/**
 * Returns [Duration] in weeks
 */
val Int.w get() = Duration(this, "w")
