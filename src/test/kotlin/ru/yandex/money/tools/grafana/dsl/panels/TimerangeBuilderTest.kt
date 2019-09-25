package ru.yandex.money.tools.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m

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