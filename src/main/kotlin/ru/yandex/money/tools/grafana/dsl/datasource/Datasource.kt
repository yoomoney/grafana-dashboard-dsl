package ru.yandex.money.tools.grafana.dsl.datasource

/**
 * Data source for metrics.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
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
