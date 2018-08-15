package ru.yandex.money.tools.grafana.dsl.time

/**
 * Настройки обновления метрик, при которых они не будут обновляться.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
object off : Refresh {
    override fun asRefreshPeriod() = false
}
