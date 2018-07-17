package ru.yandex.money.tools.grafana.dsl

interface GrafanaTimeUnit

@Suppress("ClassNaming")
object now : GrafanaTimeUnit {
    override fun toString() = "now"
}

class GrafanaDuration internal constructor(private val count: Int, private val unit: String) {
    override fun toString() = "$count$unit"
}

val Int.d get() = GrafanaDuration(this, "d")
val Int.h get() = GrafanaDuration(this, "h")
val Int.m get() = GrafanaDuration(this, "m")
val Int.s get() = GrafanaDuration(this, "s")

private class ComputedTimeUnit(private val representation: String) : GrafanaTimeUnit {
    override fun toString() = representation
}

operator fun now.minus(duration: GrafanaDuration): GrafanaTimeUnit = ComputedTimeUnit("$this-$duration")

data class GrafanaTimeRange(val from: GrafanaTimeUnit, val to: GrafanaTimeUnit)

operator fun GrafanaTimeUnit.rangeTo(other: GrafanaTimeUnit) = GrafanaTimeRange(this, other)
