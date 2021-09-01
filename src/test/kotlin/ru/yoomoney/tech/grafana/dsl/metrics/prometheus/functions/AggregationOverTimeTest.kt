package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class AggregationOverTimeTest {

    @Test
    fun `should apply avg_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().avgOverTime()

        // then
        metric.asString() shouldBeEqualTo  "avg_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply min_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().minOverTime()

        // then
        metric.asString() shouldBeEqualTo  "min_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply max_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().maxOverTime()

        // then
        metric.asString() shouldBeEqualTo  "max_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply sum_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().sumOverTime()

        // then
        metric.asString() shouldBeEqualTo  "sum_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply count_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().countOverTime()

        // then
        metric.asString() shouldBeEqualTo  "count_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply quantile_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().quantileOverTime(0.8)

        // then
        metric.asString() shouldBeEqualTo  "quantile_over_time(0.8, metric_name[5s])"
    }

    @Test(expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = "quantile must be greater than or equal to 0 and less than or equal to 1")
    fun `should fail when apply quantile_over_time function with negative quantile`() {
        "metric_name[5s]".asRangeVector().quantileOverTime(-0.01)
    }

    @Test(expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = "quantile must be greater than or equal to 0 and less than or equal to 1")
    fun `should fail when apply quantile_over_time function with quantile greater than 1`() {
        "metric_name[5s]".asRangeVector().quantileOverTime(1.01)
    }

    @Test
    fun `should apply stddev_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().stddevOverTime()

        // then
        metric.asString() shouldBeEqualTo  "stddev_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply stdvar_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().stdvarOverTime()

        // then
        metric.asString() shouldBeEqualTo  "stdvar_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply last_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().lastOverTime()

        // then
        metric.asString() shouldBeEqualTo  "last_over_time(metric_name[5s])"
    }

    @Test
    fun `should apply present_over_time function to prometheusMetric`() {
        // given
        val metric = "metric_name[5s]".asRangeVector().presentOverTime()

        // then
        metric.asString() shouldBeEqualTo  "present_over_time(metric_name[5s])"
    }
}