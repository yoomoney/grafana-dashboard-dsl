package ru.yandex.money.tools.grafana.dsl.time

/**
 * Продолжительность периода обновления метрик на дэшборде.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
interface Refresh {

    /**
     * Возвращает представление периода обновления метрик.
     *
     * @return период метрики
     */
    fun asRefreshPeriod(): Any
}
