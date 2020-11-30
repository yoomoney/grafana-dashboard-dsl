package ru.yoomoney.tech.grafana.dsl.metrics

import org.amshove.kluent.shouldContainAll
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.datasource.Graphite
import ru.yoomoney.tech.grafana.dsl.metrics.functions.StringMetric

/**
 * @author Alexander Esipov
 * @since 02.12.2019
 */
class MetricsBuilderTest {

    @Test
    fun `metric with default referenceId`() {
        // given
        val metricsBuilder = MetricsBuilder<Graphite>()

        // when
        metricsBuilder.metric(referenceId = "A") {
            StringMetric("*")
        }
        metricsBuilder.metric(referenceId = "Z") {
            StringMetric("*")
        }
        (1..24).forEach {
            metricsBuilder.metric {
                StringMetric("*")
            }
        }

        // then
        metricsBuilder.metrics.map { (it as ReferencedDashboardMetric).id } shouldContainAll ('A'..'Z').map { it.toString() }
    }

    @Test(expectedExceptions = [IllegalStateException::class], expectedExceptionsMessageRegExp = "Current implementation supports only.*")
    fun `metric with default referenceId limits`() {
        val metricsBuilder = MetricsBuilder<Graphite>()

        (1..27).forEach {
            metricsBuilder.metric {
                StringMetric("*")
            }
        }
    }
}
