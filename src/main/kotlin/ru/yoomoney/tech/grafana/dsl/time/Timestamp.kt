package ru.yoomoney.tech.grafana.dsl.time

import ru.yoomoney.tech.grafana.dsl.json.Json

/**
 * Timestamp.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
interface Timestamp : Json<String> {

    /**
     * Returns time range from this to [to].
     *
     * @param to End of time range
     * @return time range
     */
    operator fun rangeTo(to: Timestamp) = TimeRange(this, to)
}
