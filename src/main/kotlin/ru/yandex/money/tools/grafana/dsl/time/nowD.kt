package ru.yandex.money.tools.grafana.dsl.time

/**
 * Start of a day
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
object nowD : Timestamp {

    override fun toJson() = "now/d"

    override fun toString() = "now/d"
}
