package ru.yandex.money.tools.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.panels.stat.statPanel
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.variables.RefreshMode

/**
 * @author Aleksey Matveev
 * @since 05.11.2020
 */
class StatPanelFieldConfigBuilderTest {
    @Test
    fun `should create value mapping`() {
        val expectedDashboard = dashboard("value mapping") {
            val hosts by variables.query(datasource = Zabbix, query = "Skrat Back.*") {
                regex = ".*acquirer.*"
                refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE
                includeAllValue = true
            }
            panels {
                statPanel(hosts.asVariable()) {
                    fieldConfig {
                        mappings {
                            valueToText {
                                "null" to "N/A"
                            }
                        }
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("MappingsBuilderValueToText.json")
    }

    @Test
    fun `should create range mapping`() {
        val expectedDashboard = dashboard("range mapping") {
            val hosts by variables.query(datasource = Zabbix, query = "Skrat Back.*") {
                regex = ".*acquirer.*"
                refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE
                includeAllValue = true
            }
            panels {
                statPanel(hosts.asVariable()) {
                    fieldConfig {
                        mappings {
                            rangeToText {
                                range("0", "100", "0-100")
                            }
                        }
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("MappingsBuilderRangeToText.json")
    }
}
