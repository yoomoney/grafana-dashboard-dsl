package ru.yandex.money.tools.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m

class IntervalVariableTest {

    @Test
    fun `should create variable with all properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.interval(1.m, 10.m, 1.h) {
            displayName = "Test"
            hidingMode = HidingMode.NONE
            auto = true
            stepCount = 10
            minInterval = 10.m
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("IntervalVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.interval(1.m, 10.m, 1.h) {
            auto = false
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("IntervalVariableWithMinProps.json")
    }
}
