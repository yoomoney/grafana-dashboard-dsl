package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

/**
 * @author Alexander Esipov (asesipov@yamoney.ru)
 * @since 02.12.2019
 */
class AliasByMetricTest {

    @Test
    fun `should create metric that gets derivative (string)`() {
        "*.*.mean".aliasByMetric().asString() shouldEqual "aliasByMetric(*.*.mean)"
    }

    @Test
    fun `should create metric that gets derivative (metric)`() {
        StringMetric("*.*.mean").aliasByMetric().asString() shouldEqual "aliasByMetric(*.*.mean)"
    }
}