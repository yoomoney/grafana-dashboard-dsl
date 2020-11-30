package ru.yoomoney.tech.grafana.dsl.metrics

/**
 * Metric displayed on dashboard panels.
 *
 * @author Dmitry Komarov
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
