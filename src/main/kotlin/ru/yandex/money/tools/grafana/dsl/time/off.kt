package ru.yandex.money.tools.grafana.dsl.time

/**
 * Refresh settings, that disables refresh.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
object off : Refresh {
    override fun asRefreshPeriod() = false
}
