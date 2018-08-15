package ru.yandex.money.tools.grafana.dsl.datasource

/**
 * Источник данных для метрик.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
interface Datasource {

    /**
     * Возвращает строковое название источника данных.
     *
     * @return название
     */
    fun asDatasourceName(): String?
}
