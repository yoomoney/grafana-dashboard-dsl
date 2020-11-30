package ru.yoomoney.tech.grafana.dsl.time

/**
 * Refresh settings, that disables refresh.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
object off : Refresh {
    override fun asRefreshPeriod() = false
}
