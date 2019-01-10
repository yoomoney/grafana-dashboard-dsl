package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test


class AliasTest {

    @Test
    fun `should create metric that aliases metric with new name (Metric)`() {
        // given
        val metric = "*.*.count" alias  "name"

        // then
        metric.asString() shouldEqual "alias(*.*.count, 'name')"
    }

    @Test
    fun `should create metric that aliases metric with new name (String)`() {
        // given
        val metric = StringMetric("*.*.count") alias  "name"

        // then
        metric.asString() shouldEqual "alias(*.*.count, 'name')"
    }
}