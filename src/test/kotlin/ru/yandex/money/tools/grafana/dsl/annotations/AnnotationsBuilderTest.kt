package ru.yandex.money.tools.grafana.dsl.annotations

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.Color
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class AnnotationsBuilderTest {

    @Test
    fun `should create 3 annotations`() {
        val builder = AnnotationsBuilder()

        builder.annotation(name = "Test annotation") {
            zabbix {
                group = "Zabbix"
                host = "/ci/"
                application = "/backend/"
                trigger = "/release-start/"
            }
        }
        builder.annotation(name = "Disabled annotation") {
            enabled = false

            zabbix {
                group = "Zabbix"
                host = "/ci/"
                application = "/backend/"
                trigger = "/release-success/"
            }
        }
        builder.annotation(name = "Hidden and colored annotation") {
            hidden = true
            color = Color.RED

            zabbix {
                group = "Zabbix"
                host = "/ci/"
                application = "/backend/"
                trigger = "/release-fail/"

                showHostName = true
            }
        }

        val annotations = Annotations(builder.annotations)

        annotations.toJson().toString() shouldEqualToJson jsonFile("Annotations.json")
    }
}
