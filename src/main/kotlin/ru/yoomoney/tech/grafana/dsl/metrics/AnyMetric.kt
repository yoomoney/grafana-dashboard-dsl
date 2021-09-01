package ru.yoomoney.tech.grafana.dsl.metrics

/**
 * Any datasource's metric displayed on dashboard panels.
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 27.08.2021
 */
interface AnyMetric {
    /**
     * Returns string representation of a metric.
     *
     * @return String representation of a metric
     */
    fun asString(): String
}