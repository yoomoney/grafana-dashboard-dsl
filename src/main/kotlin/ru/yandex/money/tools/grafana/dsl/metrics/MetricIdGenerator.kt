package ru.yandex.money.tools.grafana.dsl.metrics

/**
 * Generates metric id
 *
 * Current implementation generates only 'A'..'Z' sequence.
 * It should be enough for most graphs.
 *
 * @author Alexander Esipov (asesipov@yamoney.ru)
 * @since 03.12.2019
 */
internal class MetricIdGenerator {
    private var lastId: Char? = null

    /**
     * Return next metric id
     *
     * @throws IllegalStateException if last generated id was 'Z'
     */
    fun nextMetricId(): String {
        val nextId = lastId?.plus(1) ?: 'A'
        if (nextId > 'Z') {
            throw IllegalStateException("Current implementation supports only 'A'..'Z' auto generated metric ids")
        }
        lastId = nextId
        return nextId.toString()
    }
}