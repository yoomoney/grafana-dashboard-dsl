package ru.yoomoney.tech.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson
import ru.yoomoney.tech.grafana.dsl.time.h
import ru.yoomoney.tech.grafana.dsl.time.m

class TimerangeBuilderTest {

    @Test
    fun `should create time range`() {

        val expectedDashboard = dashboard("time range test") {
            panels {
                singleStat("time range test") {
                    timerange {
                        lastTime = 2.h
                        timeShift = 5.m
                        hideTimeOverrideInfo = true
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("TimeRangeBuilder.json")
    }
}