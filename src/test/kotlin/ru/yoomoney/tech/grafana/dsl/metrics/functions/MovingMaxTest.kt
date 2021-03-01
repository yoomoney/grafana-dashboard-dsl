package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.time.m

/**
 * @author Aleksey Matveev
 * @since 23.11.2020
 */
class MovingMaxTest {
    @Test
    fun `should create metric that gets max of metric`() {
        // given
        val metric = "*.*.count" movingMax 30.m

        // then
        metric.asString() shouldEqual "movingMax(*.*.count, '30m')"
    }

    @Test
    fun `should create metric that gets max of metric by metric object`() {
        // given
        val metric = StringMetric("*.*.count") movingMax 30.m

        // then
        metric.asString() shouldEqual "movingMax(*.*.count, '30m')"
    }
}