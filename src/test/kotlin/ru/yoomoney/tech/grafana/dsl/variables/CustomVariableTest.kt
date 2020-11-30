package ru.yoomoney.tech.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

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
        val jsonObject = variable.toJson().toString()
        jsonObject shouldEqualToJson jsonFile("CustomVariableWithNamedOptions.json")
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

    @Test
    fun `should create variable with specified selected index `() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom(VariableValue("1", "first"), VariableValue("2", "second")) {
            selectedIndex = 1
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithSpecifiedSelectedIndex.json")
    }

    @Test
    fun `should create variable with all properties set named options and specified selected index`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom(VariableValue("1", "first"), VariableValue("2", "second")) {
            displayName = "Test"
            multiValuesAllowed = true
            includeAllValue = true
            allValue = "test"
            selectedIndex = 2
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithAllPropsAndSpecifiedSelectedIndex.json")
    }

    @Test
    fun `should create variable with minimal properties set specified selected index`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.custom("1", "2", "3") {
            selectedIndex = 2
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("CustomVariableWithMinPropsSelectedIndex.json")
    }
}
