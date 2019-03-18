package ru.yandex.money.tools.grafana.dsl.variables

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.dashboard.DashboardBuilder
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.s

class VariablesTest {

    @Test
    fun `should create empty query variable correctly`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        // when
        val variable by dashboardBuilder.variable(datasource = Zabbix) {
            query("test_query") {}
        }

        // then
        variable.name shouldEqual "variable" // same as Kotlin's variable name
        variable.asVariable() shouldEqual "\$variable"
        variable.toJson().toString() shouldEqualToJson jsonFile("EmptyQueryVar.json")
    }

    @Test
    fun `should create query variable with custom regex`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        // when
        val variable by dashboardBuilder.variable(datasource = Zabbix) {
            query("test_query") {
                regex = "\\w+"
            }
        }

        // then
        variable.name shouldEqual "variable" // same as Kotlin's variable name
        variable.asVariable() shouldEqual "\$variable"
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVarWithCustomRegex.json")
    }

    @Test
    fun `should create interval variable`() {
        // given
        val dashboardBuilder = DashboardBuilder("Test Dashboard")

        // when
        val variable by dashboardBuilder.variable {
            interval(1.s, 1.m, 1.h)
        }

        // then
        variable.name shouldEqual "variable" // same as Kotlin's variable name
        variable.asVariable() shouldEqual "\$variable"
        variable.toJson().toString() shouldEqualToJson jsonFile("IntervalVar.json")
    }

}
