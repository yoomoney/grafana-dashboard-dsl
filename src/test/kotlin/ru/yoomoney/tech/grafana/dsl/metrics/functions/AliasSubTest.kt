package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

/**
 * Test for [ru.yoomoney.tech.grafana.dsl.metrics.functions.AliasSub]
 */
class AliasSubTest {
	@Test
    fun `should create metric that aliased by substring replace (Metric)`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasSub "$1 my-metric-name"

        // then
        metric.asString() shouldEqual "aliasSub(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '(.*.)', '$1 my-metric-name')"
    }

    @Test
    fun `should create metric that aliased by substring replace (String)`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".aliasSub("$1 my-metric-name")

        // then
        metric.asString() shouldEqual "aliasSub(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '(.*.)', '$1 my-metric-name')"
    }

    @Test
    fun `should create metric that aliased by substring replace with search regex (String)`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".aliasSub("(.*oil-gate.)", "my-metric-name $2")

        // then
        metric.asString() shouldEqual "aliasSub(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '(.*oil-gate.)', 'my-metric-name $2')"
    }
}
