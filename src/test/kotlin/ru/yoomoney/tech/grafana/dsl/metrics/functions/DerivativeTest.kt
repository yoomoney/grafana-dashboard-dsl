package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

/**
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class DerivativeTest {

    @Test
    fun `should create metric that gets derivative (string)`() {
        "*.*.mean".derivative().asString() shouldEqual "derivative(*.*.mean)"
    }

    @Test
    fun `should create metric that gets derivative (metric)`() {
        StringMetric("*.*.mean").derivative().asString() shouldEqual "derivative(*.*.mean)"
    }
}