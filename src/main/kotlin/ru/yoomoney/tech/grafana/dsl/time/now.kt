package ru.yoomoney.tech.grafana.dsl.time

/**
 * Current time.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
object now : Timestamp {

    override fun toJson() = "now"

    override fun toString() = "now"

    /**
     * Returns duration from now to specified value.
     *
     * @param duration Time range
     * @return Time left
     */
    operator fun minus(duration: Duration) = object : Timestamp {

        override fun toJson() = "now-$duration"

        override fun toString() = "now-$duration"
    }
}
