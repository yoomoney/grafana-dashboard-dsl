package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.functions

import org.amshove.kluent.shouldBeEqualTo
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.asRangeVector

class HoltWintersTest {

    @DataProvider
    fun functionValidArguments() = arrayOf(
        arrayOf(0.0, 1.0, "0.0", "1.0"),
        arrayOf(1.0, 0.0, "1.0", "0.0"),
        arrayOf(0.4, 0.5, "0.4", "0.5")
    )

    @Test(dataProvider = "functionValidArguments")
    fun `should apply holtWinters function to prometheusMetric`(sf: Double, tf: Double, sfStr: String, tfStr: String) {
        // given
        val metric = "metric_name[5m]".asRangeVector().holtWinters(sf, tf)

        // then
        metric.asString() shouldBeEqualTo  "holt_winters(metric_name[5m], $sfStr, $tfStr)"
    }

    @DataProvider
    fun functionInvalidArguments() = arrayOf(
        arrayOf(0.0, 1.1),
        arrayOf(1.1, 0.0),
        arrayOf(-0.1, 0.0),
        arrayOf(0.0, -0.1)
    )

    @Test(dataProvider = "functionInvalidArguments", expectedExceptions = [IllegalArgumentException::class])
    fun `should fail when apply holtWinters function with invalid arguments`(sf: Double, tf: Double) {
        "metric_name[5m]".asRangeVector().holtWinters(sf, tf)
    }
}