package ru.yandex.money.tools.grafana.dsl.time

/**
 * Refresh interval.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
interface Refresh {

    /**
     * Returns metric refresh period.
     *
     * @return Metric period
     */
    fun asRefreshPeriod(): Any
}
