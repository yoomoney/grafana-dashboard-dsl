package ru.yoomoney.tech.grafana.dsl.panels.alerting

import org.amshove.kluent.shouldEqual
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class AlertingStatesTest {

    @Test(dataProvider = "alertingStates")
    fun `should serialize alerting states correctly`(alertingState: AlertingState, representation: String) {
        alertingState.asState() shouldEqual representation
    }

    @DataProvider
    fun alertingStates() = arrayOf(
            arrayOf(Alerting, "alerting"),
            arrayOf(Ok, "ok"),
            arrayOf(KeepLastState, "keep_state"),
            arrayOf(NoData, "no_data")
    )
}
