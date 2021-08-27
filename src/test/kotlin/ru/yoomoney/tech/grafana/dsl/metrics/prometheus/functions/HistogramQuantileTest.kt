package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asInstantVector

class HistogramQuantileTest {
    @DataProvider
    fun quantiles() = arrayOf(
        arrayOf(0.0, "0.0"),
        arrayOf(0.5, "0.5"),
        arrayOf(1.0, "1.0")
    )

    @Test(dataProvider = "quantiles")
    fun `should apply histogramQuantile function to prometheusMetric`(quantile: Double, quantilesStr: String) {
        // given
        val metric = "metric_name".asInstantVector().histogramQuantile(quantile)

        // then
        metric.asString() shouldBeEqualTo  "histogram_quantile($quantilesStr, metric_name)"
    }

    @Test(expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = "quantile must be greater than or equal to 0 and less than or equal to 1")
    fun `should fail when apply histogramQuantile function with negative quantile`() {
        "metric_name".asInstantVector().histogramQuantile(-0.01)
    }

    @Test(expectedExceptions = [IllegalArgumentException::class],
        expectedExceptionsMessageRegExp = "quantile must be greater than or equal to 0 and less than or equal to 1")
    fun `should fail when apply histogramQuantile function with quantile greater than 1`() {
        "metric_name".asInstantVector().histogramQuantile(1.01)
    }
}