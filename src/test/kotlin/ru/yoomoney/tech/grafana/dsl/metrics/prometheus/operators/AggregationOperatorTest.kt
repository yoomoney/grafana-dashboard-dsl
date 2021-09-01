package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.operators

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class AggregationOperatorTest {
    @Test
    fun `should apply sum by operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().sum(by = listOf("a", "b"))

        // then
        metric.asString() shouldBeEqualTo  "sum by (a, b) (instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply sum without operator without aliases to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().sum(without = listOf("a", "c"))

        // then
        metric.asString() shouldBeEqualTo  "sum without (a, c) (instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply sum operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().sum()

        // then
        metric.asString() shouldBeEqualTo  "sum(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply min operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().min()

        // then
        metric.asString() shouldBeEqualTo  "min(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply max operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().max()

        // then
        metric.asString() shouldBeEqualTo  "max(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply avg operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().avg()

        // then
        metric.asString() shouldBeEqualTo  "avg(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply group operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().group()

        // then
        metric.asString() shouldBeEqualTo  "group(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply stddev operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().stddev()

        // then
        metric.asString() shouldBeEqualTo  "stddev(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply stdvar operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().stdvar()

        // then
        metric.asString() shouldBeEqualTo  "stdvar(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply countValues operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().countValues()

        // then
        metric.asString() shouldBeEqualTo  "count_values(instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply bottomk operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().bottomk(10)

        // then
        metric.asString() shouldBeEqualTo  "bottomk(10, instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply topk operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().topk(15)

        // then
        metric.asString() shouldBeEqualTo  "topk(15, instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply topk by operator to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().topk(k = 15, by = listOf("a", "b"))

        // then
        metric.asString() shouldBeEqualTo  "topk by (a, b) (15, instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply topk without operator without aliases to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().topk(k = 15, without = listOf("a", "c"))

        // then
        metric.asString() shouldBeEqualTo  "topk without (a, c) (15, instance_memory_limit_bytes)"
    }

    @Test
    fun `should apply topk quantile to prometheusMetric`() {
        // given
        val metric = "instance_memory_limit_bytes".asInstantVector().quantile(0.5)

        // then
        metric.asString() shouldBeEqualTo  "quantile(0.5, instance_memory_limit_bytes)"
    }
}
