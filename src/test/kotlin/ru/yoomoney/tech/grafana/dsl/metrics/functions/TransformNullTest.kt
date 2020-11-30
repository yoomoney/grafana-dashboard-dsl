package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class TransformNullTest {
    @Test
    fun `should create metric that transform metric and replace nulls to constant`() {
        // given
        val metric = "*.*.count" transformNull 0.0

        // then
        metric.asString() shouldEqual "transformNull(*.*.count, 0.0)"
    }
}