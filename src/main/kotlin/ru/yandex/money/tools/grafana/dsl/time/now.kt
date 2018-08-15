package ru.yandex.money.tools.grafana.dsl.time

/**
 * Текущая метка времени.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
object now : Timestamp {

    override fun toJson() = "now"

    override fun toString() = "now"

    /**
     * Возвращает метку времени, отстающую относительно текущей на некоторый отрезок времени.
     *
     * @param duration отрезок времени
     * @return отстающая метка времени
     */
    operator fun minus(duration: Duration) = object : Timestamp {

        override fun toJson() = "now-$duration"

        override fun toString() = "now-$duration"
    }
}
