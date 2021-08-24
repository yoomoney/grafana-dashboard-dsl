package ru.yoomoney.tech.grafana.dsl.panels

import org.amshove.kluent.shouldBe
import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.datasource.PromQl
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.functions.StringMetric
import ru.yoomoney.tech.grafana.dsl.panels.repeat.Horizontal
import ru.yoomoney.tech.grafana.dsl.panels.stat.ColorMode
import ru.yoomoney.tech.grafana.dsl.panels.stat.GraphMode
import ru.yoomoney.tech.grafana.dsl.panels.stat.JustifyMode
import ru.yoomoney.tech.grafana.dsl.panels.stat.StatPanelDisplayOptions
import ru.yoomoney.tech.grafana.dsl.panels.stat.TextMode
import ru.yoomoney.tech.grafana.dsl.panels.stat.statPanel
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson
import ru.yoomoney.tech.grafana.dsl.variables.RefreshMode

/**
 * @author Aleksey Matveev
 * @since 03.11.2020
 */

class StatPanelBuilderTest {
    @Test
    fun `create stat with repeat`() {
        val minimalDashboard = dashboard("TestAutogenerated") {
            // given
            val hosts by variables.query(datasource = Zabbix, query = "Skrat Back.*") {
                regex = ".*acquirer.*"
                refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE
                includeAllValue = true
            }

            panels {
                statPanel(hosts.asVariable()) {
                    bounds = 3 to 3

                    repeat(hosts) {
                        direction = Horizontal(2)
                    }

                    metrics<Zabbix> {
                        textQuery {
                            host = hosts.asVariable()
                            application = "Application data"
                            item = "Application dynamic config version"
                            group = "/.*/"
                            textFilter = ".{11}"
                        }
                    }

                    timerange {
                        hideTimeOverrideInfo = true
                    }

                    options {
                        colorMode = ColorMode.BACKGROUND
                        reduceOptions {
                            fields = "Application data"
                        }
                    }

                    fieldConfig {
                        thresholds(ThresholdMode.ABSOLUTE) {
                            steps {
                                "0" to Color.RED
                            }
                        }
                        mappings {
                            valueToText {
                                "null" to "N/A"
                            }
                        }
                    }
                }
            }
        }

        // then
        minimalDashboard shouldEqualToJson jsonFile("StatPanelWithRepeat.json")
    }

    @Test
    fun `create stat without repeat`() {
        val minimalDashboard = dashboard("TestAutogenerated") {
            // given
            val hosts by variables.query(datasource = Zabbix, query = "Skrat Back.*") {
                regex = ".*acquirer.*"
                refreshMode = RefreshMode.ON_TIME_RANGE_CHANGE
                includeAllValue = true
            }

            panels {
                statPanel(hosts.asVariable()) {
                    bounds = 3 to 3

                    metrics<Zabbix> {
                        textQuery {
                            host = hosts.asVariable()
                            application = "Application data"
                            item = "Application dynamic config version"
                            group = "/.*/"
                            textFilter = ".{11}"
                        }
                    }

                    timerange {
                        hideTimeOverrideInfo = true
                    }

                    options {
                        colorMode = ColorMode.BACKGROUND
                    }

                    fieldConfig {
                        thresholds(ThresholdMode.ABSOLUTE) {
                            steps {
                                "0" to Color.RED
                            }
                        }
                        mappings {
                            valueToText {
                                "null" to "N/A"
                            }
                        }
                    }
                }
            }
        }

        // then
        minimalDashboard shouldEqualToJson jsonFile("StatPanelWithoutRepeat.json")
    }

    @Test
    fun `should create panel with promQl metric`() {
        // given
        val testContainer = TestContainerBuilder()

        // when
        testContainer.statPanel(title = "PromQl Panel") {
            datasource = PromQl
            metrics<PromQl> {
                promQlMetric(format = "{{version}}", instant = true) {
                    StringMetric("app_info{app_name=\"my_app\",instance=\"host\"}")
                }
            }
            options = StatPanelDisplayOptions(
                justifyMode = JustifyMode.CENTER,
                orientation = Orientation.AUTO,
                textMode = TextMode.NAME,
                graphMode = GraphMode.NONE
            )
        }

        // then
        val panels = testContainer.panels
        panels.size shouldBe 1
        panels[0].toJson().toString() shouldEqualToJson jsonFile("StatPanelWithPromQlMetric.json")
    }

}