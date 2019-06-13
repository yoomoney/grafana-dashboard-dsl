package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class AliasByNodeTest {

    @Test
    fun `should create metric that aliased by node`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" aliasByNode 1

        // then
        metric.asString() shouldEqual "aliasByNode(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 1)"
    }

    @Test
    fun `should create metric that aliased by nodes`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".aliasByNode(1, 2)

        // then
        metric.asString() shouldEqual "aliasByNode(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 1, 2)"
    }
}
