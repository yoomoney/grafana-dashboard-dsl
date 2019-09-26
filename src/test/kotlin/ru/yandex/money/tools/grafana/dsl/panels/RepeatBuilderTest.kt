package ru.yandex.money.tools.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.repeat.Horizontal
import ru.yandex.money.tools.grafana.dsl.panels.repeat.Vertical
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class RepeatBuilderTest {

    @Test
    fun `should create repeat horizontal`() {

        val expectedDashboard = dashboard("repeat horizontal") {

            val hosts by variables.custom("host1", "host2")

            panels {
                singleStat("repeat horizontal") {
                    repeat(hosts) {
                        direction = Horizontal(2)
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("RepeatBuilderHorizontal.json")
    }

    @Test
    fun `should create repeat vertical`() {

        val expectedDashboard = dashboard("repeat vertical") {

            val hosts by variables.custom("host1", "host2")

            panels {
                singleStat("repeat vertical") {
                    repeat(hosts) {
                        direction = Vertical()
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("RepeatBuilderVertical.json")
    }
}
