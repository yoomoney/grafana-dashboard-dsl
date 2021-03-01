package ru.yoomoney.tech.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.panels.stat.statPanel
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson
import ru.yoomoney.tech.grafana.dsl.variables.RefreshMode

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
