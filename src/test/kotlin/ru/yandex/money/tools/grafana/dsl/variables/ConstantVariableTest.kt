package ru.yandex.money.tools.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class ConstantVariableTest {

    @Test
    fun `should create variable with all properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.constant("test") {
            displayName = "Test"
            hidingMode = HidingMode.LABEL
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("ConstantVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.constant("test")

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("ConstantVariableWithMinProps.json")
    }
}
