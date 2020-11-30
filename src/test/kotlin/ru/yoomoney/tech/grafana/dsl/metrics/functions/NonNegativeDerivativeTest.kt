package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

/**
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class NonNegativeDerivativeTest {

    @Test
    fun `should create metric that gets nonNegativeDerivative (string)`() {
        "*.*.mean".nonNegativeDerivative().asString() shouldEqual "nonNegativeDerivative(*.*.mean)"
    }

    @Test
    fun `should create metric that gets nonNegativeDerivative (metric)`() {
        StringMetric("*.*.mean").nonNegativeDerivative().asString() shouldEqual "nonNegativeDerivative(*.*.mean)"
    }
}