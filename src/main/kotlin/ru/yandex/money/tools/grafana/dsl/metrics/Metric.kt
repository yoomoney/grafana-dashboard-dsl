package ru.yandex.money.tools.grafana.dsl.metrics

/**
 * Метрика, отображаемая на панелях дэшборда.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
interface Metric {

    /**
     * Возвращает строковое представление метрики.
     *
     * @return строковое представление метрики
     */
    fun asString(): String
}
