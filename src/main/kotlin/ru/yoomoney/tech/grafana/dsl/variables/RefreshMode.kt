package ru.yoomoney.tech.grafana.dsl.variables

/**
 * A strategy that defines when variable values will be queried and updated.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
enum class RefreshMode(val mode: Int) {

    /**
     * Values will be queried only when dashboard will be created and never updated.
     */
    NEVER(0),

    /**
     * Values will be queried and updated every time when you loading page with dashboard.
     */
    ON_DASHBOARD_LOAD(1),

    /**
     * Value will be queried and update every time when you changing time range.
     */
    ON_TIME_RANGE_CHANGE(2)
}
