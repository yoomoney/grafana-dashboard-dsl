package ru.yoomoney.tech.grafana.dsl.generator

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.panels.panel
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class SimplePanelLayoutGeneratorTest {

    @Test
    fun `test generator`() {
        val dashboard = dashboard("test generator") {
            panels {

                panel("1") {}

                panel("2") {}

                panel("3") {}

                row("4") {

                    panel("5") {}

                    panel("6") {}
                }
            }
        }

        dashboard shouldEqualToJson jsonFile("SimpleGenerator.json")
    }
}