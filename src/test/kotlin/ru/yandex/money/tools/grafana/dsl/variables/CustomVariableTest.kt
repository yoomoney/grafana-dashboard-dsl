package ru.yandex.money.tools.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class CustomVariableTest {

    @Test
    fun `should create variable with all properties set`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom("1", "2", "3") {
            displayName = "Test"
            hidingMode = HidingMode.LABEL
            multiValuesAllowed = true
            includeAllValue = true
            allValue = "test"
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties set`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom("1", "2", "3")

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithMinProps.json")
    }

    @Test
    fun `should create variable with named options`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom(VariableValue("1", "first")) {
            displayName = "Test"
            hidingMode = HidingMode.LABEL
            multiValuesAllowed = true
            includeAllValue = true
            allValue = "test"
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithNamedOptions.json")
    }

    @Test
    fun `should create variable with minimal properties set and named options`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom(VariableValue("1", "first"))

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithNamedOptionsAndMinProps.json")
    }
}
