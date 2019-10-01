package ru.yandex.money.tools.grafana.dsl.generator

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.panel
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

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