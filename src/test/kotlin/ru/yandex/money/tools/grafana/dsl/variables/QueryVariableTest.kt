package ru.yandex.money.tools.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class QueryVariableTest {

    @Test
    fun `should create variable with all properties set`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.query(datasource = Zabbix, query = "App version") {
            displayName = "Test"
            hidingMode = HidingMode.NONE
            multiValuesAllowed = true
            includeAllValue = true
            regex = ".*test.*"
            refreshMode = RefreshMode.NEVER
            sortOrder = SortOrder.DISABLED
            allValue = "test"
        }

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVariableWithAllProps.json")
    }

    @Test
    fun `should create variable with minimal properties set`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.query(datasource = Zabbix, query = "App version")

        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVariableWithMinProps.json")
    }
}
