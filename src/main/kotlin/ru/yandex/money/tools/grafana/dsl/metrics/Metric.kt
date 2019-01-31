package ru.yandex.money.tools.grafana.dsl.metrics

/**
 * Metric displayed on dashboard panels.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
interface Metric {

    /**
     * Returns string representation of a metric.
     *
     * @return String representation of a metric
     */
    fun asString(): String
}
