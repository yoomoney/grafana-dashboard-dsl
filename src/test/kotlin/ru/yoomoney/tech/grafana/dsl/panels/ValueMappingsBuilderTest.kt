package ru.yoomoney.tech.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class ValueMappingsBuilderTest {

    @Test
    fun `should create value to text mapping`() {

        val expectedDashboard = dashboard("value mapping test") {
            panels {
                singleStat("value mapping test") {
                    valueMappings<ValueToTextType> {
                        valueToText {
                            "null" to "N/A"
                            "0" to "N/A"
                        }
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("ValueMappingsBuilderValueToText.json")
    }

    @Test
    fun `should create range to text mapping`() {

        val expectedDashboard = dashboard("range mapping test") {
            panels {
                singleStat("range mapping test") {
                    valueMappings<RangeToTextType> {
                        rangeToText {
                            range("0", "100", "max")
                            range("-10", "0", "min")
                        }
                    }
                }
            }
        }

        expectedDashboard shouldEqualToJson jsonFile("ValueMappingsBuilderRangeToText.json")
    }
}