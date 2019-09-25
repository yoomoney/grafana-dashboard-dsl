package ru.yandex.money.tools.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class MetricsBuilderTest {

    @Test
    fun `should create zabbix text query`() {

        val expectedDashboard = dashboard("zabbix text query") {
            panels {
                singleStat("zabbix text query") {
                    metrics<Zabbix> {
                        textQuery {
                            host = "host.com"
                            application = "/App acquirer Ping General/"
                            item = "/service version/"
                            group = "/.*/"
                        }
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("MetricsBuilderTextQuery.json")
    }

    @Test
    fun `should create zabbix metrics query`() {

        val expectedDashboard = dashboard("zabbix metrics query") {
            panels {
                singleStat("zabbix metrics query") {
                    metrics<Zabbix> {
                        metricsQuery {
                            host = "host.com"
                            application = "/App acquirer Ping General/"
                            item = "/service version/"
                            group = "/.*/"
                        }
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("MetricsBuilderMetricsQuery.json")
    }
}