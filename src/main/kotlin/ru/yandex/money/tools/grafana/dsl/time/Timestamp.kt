package ru.yandex.money.tools.grafana.dsl.time

import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Метка времени.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
interface Timestamp : Json<String> {

    /**
     * Возвращает отрезок с данного по некоторый момент.
     *
     * @param to конец отрезка
     * @return отрезок времени
     */
    operator fun rangeTo(to: Timestamp) = TimeRange(this, to)
}
