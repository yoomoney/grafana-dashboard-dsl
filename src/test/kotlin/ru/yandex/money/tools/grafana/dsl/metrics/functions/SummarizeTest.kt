package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.json.JSONObject
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.variables.Variable

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

    private object FakeVariable : Variable {

        override fun toJson(): JSONObject {
            throw UnsupportedOperationException()
        }

        override val name = "fake"
    }
}
