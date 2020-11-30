package ru.yoomoney.tech.grafana.dsl.variables

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

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
            current = CurrentVariableValue("test")
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

    @Test
    fun `should create variable with tags`() {
        // given
        val builder = VariablesBuilder()

        // when
        val variable by builder.query(datasource = Zabbix, query = "App version") {
            tags = VariableTags("*.*", "*.\$tag")
        }

        println(variable.toJson().toString())
        // then
        variable.toJson().toString() shouldEqualToJson jsonFile("QueryVariableWithTags.json")
    }
}
