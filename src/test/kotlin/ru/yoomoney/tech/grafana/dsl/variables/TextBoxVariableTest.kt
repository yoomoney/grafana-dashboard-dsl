package ru.yoomoney.tech.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

class TextBoxVariableTest {

    @Test
    fun `should create variable with all properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.textBox("test") {
            displayName = "Test"
            hidingMode = HidingMode.LABEL
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("TextBoxVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.textBox()

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("TextBoxVariableWithMinProps.json")
    }
}
