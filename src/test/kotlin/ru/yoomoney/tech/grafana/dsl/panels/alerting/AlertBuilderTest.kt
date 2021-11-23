package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.json.JSONObject
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.ReferencedDashboardMetric
import ru.yoomoney.tech.grafana.dsl.metrics.functions.StringMetric
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson
import ru.yoomoney.tech.grafana.dsl.time.m

class AlertBuilderTest {

    @Test
    fun `should create alerting panel`() {
        val builder = AlertBuilder("Alert name").apply {
            frequency = 5.m
            pendingFor = 10.m
            onNoData = Alerting
            message = "Alert message"
            notificationIds += 1
            notificationIds += 2
            notificationUids += "ABC"

            conditions {
                query(
                    ReferencedDashboardMetric(
                        StringMetric("*"),
                        "A",
                        false
                    ),
                    15.m
                ).isAbove(3000)
            }

            thresholds {
                threshold { ThresholdDsl.gt(3000) }
            }
        }

        val alertingPanelJson = JSONObject()
            .apply {  builder.createAlertingPanel().invoke(this) }
            .toString()

        alertingPanelJson shouldEqualToJson jsonFile("AlertingPanel.json")
    }
}