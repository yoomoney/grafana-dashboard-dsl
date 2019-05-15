package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

class GroupByNodesTest {

    @Test(expectedExceptions = [IllegalArgumentException::class], expectedExceptionsMessageRegExp = "nodes must be not empty")
    fun `should throw exception when nodes were not specified`() {
        // expect exception
        "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNodes("avg")
    }

    @Test
    fun `should create metric`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNodes("avg", 0, 1)

        // then
        metric.asString() shouldEqual "groupByNodes(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 'avg', 0, 1)"
    }

    @Test
    fun `should create metric with default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".groupByNodes(0)

        // then
        metric.asString() shouldEqual "groupByNodes(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, 'sum', 0)"
    }
}