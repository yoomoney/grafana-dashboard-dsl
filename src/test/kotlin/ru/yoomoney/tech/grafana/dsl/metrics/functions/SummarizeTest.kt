package ru.yoomoney.tech.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.json.JSONObject
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.time.m
import ru.yoomoney.tech.grafana.dsl.variables.Variable

class SummarizeTest {

    @Test
    fun `should create metric from duration and function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".summarize(1.m, "avg")

        // then
        metric.asString() shouldEqual "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '1m', 'avg')"
    }

    @Test
    fun `should create metric from duration and default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" summarize 1.m

        // then
        metric.asString() shouldEqual "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '1m', 'sum')"
    }

    @Test
    fun `should create metric from variable and function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".summarize(FakeVariable, "avg")

        // then
        metric.asString() shouldEqual "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '$${FakeVariable.name}', 'avg')"
    }

    @Test
    fun `should create metric from variable and default function`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count" summarize FakeVariable

        // then
        metric.asString() shouldEqual "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '$${FakeVariable.name}', 'sum')"
    }

    @Test
    fun `should create metric from variable and function with alignTo`() {
        // given
        val metric = "*.*.oil-gate.requests.incoming.*.*.process_time.*.count".summarize(FakeVariable, "avg", true)

        // then
        metric.asString() shouldEqual "summarize(*.*.oil-gate.requests.incoming.*.*.process_time.*.count, '$${FakeVariable.name}', 'avg', true)"
    }

    private object FakeVariable : Variable {

        override fun toJson(): JSONObject {
            throw UnsupportedOperationException()
        }

        override val name = "fake"
    }
}
