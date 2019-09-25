package ru.yandex.money.tools.grafana.dsl.time

import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Timestamp.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
interface Timestamp : Json<String> {

    /**
     * Returns time range from this to [to].
     *
     * @param to End of time range
     * @return time range
     */
    operator fun rangeTo(to: Timestamp) = Time(this, to)
}
