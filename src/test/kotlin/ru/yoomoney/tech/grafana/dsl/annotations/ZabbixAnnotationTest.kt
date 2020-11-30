package ru.yoomoney.tech.grafana.dsl.annotations

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.panels.Color
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class ZabbixAnnotationTest {

    @Test
    fun `should create Zabbix annotation`() {
        val builder = AnnotationBuilder(name = "test")

        builder.enabled = false
        builder.hidden = true
        builder.color = Color.BLUE

        val annotation = builder.zabbix {
            group = "Zabbix"
            host = "/test/"
            application = "/app/"
            trigger = "/trigger/"

            minSeverity = ZabbixTriggerSeverity.DISASTER

            showOkEvents = true
            hideAcknowledgedEvents = true
            showHostName = true
        }

        annotation.toJson().toString() shouldEqualToJson jsonFile("ZabbixAnnotation.json")
    }

    @Test
    fun `should create minimal Zabbix annotation`() {
        val builder = AnnotationBuilder(name = "test")

        val annotation = builder.zabbix {}

        annotation.toJson().toString() shouldEqualToJson jsonFile("ZabbixAnnotationMinimal.json")
    }
}
