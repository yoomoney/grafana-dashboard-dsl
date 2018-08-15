package ru.yandex.money.tools.grafana.dsl.time

/**
 * Некоторый отрезок времени определенной продолжительности.
 *
 * @param number длина отрезка
 * @param unit единица времени, в которой измеряется длина отрезка. Например: s (секунды), m (минуты), h (часы),
 *             d (дни), w (недели).
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class Duration(private val number: Int, private val unit: String) : Refresh {

    override fun asRefreshPeriod() = "$number$unit"

    override fun toString() = asRefreshPeriod()
}

/**
 * Возвращает [Duration] в секундах
 */
val Int.s get() = Duration(this, "s")

/**
 * Возвращает [Duration] в минутах
 */
val Int.m get() = Duration(this, "m")

/**
 * Возвращает [Duration] в часах
 */
val Int.h get() = Duration(this, "h")

/**
 * Возвращает [Duration] в днях
 */
val Int.d get() = Duration(this, "d")

/**
 * Возвращает [Duration] в неделях
 */
val Int.w get() = Duration(this, "w")
