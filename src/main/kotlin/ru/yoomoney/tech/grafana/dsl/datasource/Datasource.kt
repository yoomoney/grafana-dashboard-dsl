package ru.yoomoney.tech.grafana.dsl.datasource

/**
 * Data source for metrics.
 *
 * @author Dmitry Komarov
 * @since 7/23/18
 */
interface Datasource {

    /**
     * Returns string representation of this data source.
     *
     * @return title
     */
    fun asDatasourceName(): String?
}
