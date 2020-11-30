package ru.yoomoney.tech.grafana.dsl.time

/**
 * Start of a day
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
object nowD : Timestamp {

    override fun toJson() = "now/d"

    override fun toString() = "now/d"
}
