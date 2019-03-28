package ru.yandex.money.tools.grafana.dsl.metrics.functions

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.variables.VariablesBuilder

class HighestTest {

    @Test
    fun `should create highestCurrent metric with int n`() {
        // given
        val metric = "*.*.count" highestCurrent 5

        // then
        metric.asString() shouldEqual "highestCurrent(*.*.count, 5)"
    }

    @Test
    fun `should create highestCurrent metric with int n and metric`() {
        // given
        val metric = StringMetric("*.*.count") highestCurrent 5

        // then
        metric.asString() shouldEqual "highestCurrent(*.*.count, 5)"
    }

    @Test
    fun `should create highestCurrent metric with variable n`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = "*.*.count" highestCurrent n

        // then
        metric.asString() shouldEqual "highestCurrent(*.*.count, \$n)"
    }

    @Test
    fun `should create highestCurrent metric with variable n and metric`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = StringMetric("*.*.count") highestCurrent n

        // then
        metric.asString() shouldEqual "highestCurrent(*.*.count, \$n)"
    }

    @Test
    fun `should create highestMax metric with int n`() {
        // given
        val metric = "*.*.count" highestMax 5

        // then
        metric.asString() shouldEqual "highestMax(*.*.count, 5)"
    }

    @Test
    fun `should create highestMax metric with int n and metric`() {
        // given
        val metric = StringMetric("*.*.count") highestMax 5

        // then
        metric.asString() shouldEqual "highestMax(*.*.count, 5)"
    }

    @Test
    fun `should create highestMax metric with variable n`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = "*.*.count" highestMax n

        // then
        metric.asString() shouldEqual "highestMax(*.*.count, \$n)"
    }

    @Test
    fun `should create highestMax metric with variable n and metric`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = StringMetric("*.*.count") highestMax n

        // then
        metric.asString() shouldEqual "highestMax(*.*.count, \$n)"
    }

    @Test
    fun `should create highestAverage metric with int n`() {
        // given
        val metric = "*.*.count" highestAverage 5

        // then
        metric.asString() shouldEqual "highestAverage(*.*.count, 5)"
    }

    @Test
    fun `should create highestAverage metric with int n and metric`() {
        // given
        val metric = StringMetric("*.*.count") highestAverage 5

        // then
        metric.asString() shouldEqual "highestAverage(*.*.count, 5)"
    }

    @Test
    fun `should create highestAverage metric with variable n`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = "*.*.count" highestAverage n

        // then
        metric.asString() shouldEqual "highestAverage(*.*.count, \$n)"
    }

    @Test
    fun `should create highestAverage metric with variable n and metric`() {
        // given
        val builder = VariablesBuilder()
        val n by builder.constant("5")

        val metric = StringMetric("*.*.count") highestAverage n

        // then
        metric.asString() shouldEqual "highestAverage(*.*.count, \$n)"
    }
}
