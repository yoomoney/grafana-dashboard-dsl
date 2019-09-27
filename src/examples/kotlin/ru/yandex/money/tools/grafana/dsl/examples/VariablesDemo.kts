package ru.yandex.money.tools.grafana.dsl.examples

import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.variables.HidingMode
import ru.yandex.money.tools.grafana.dsl.variables.VariableValue

@Suppress("UNUSED_VARIABLE")
dashboard(title = "Grafana Variables Demo") {

    tags += arrayOf("variables", "example")

    /* Simple constant variable with name and label [constant] */
    val constant by variables.constant("42")

    /* Custom variable with name [custom] (so you can refer this variable via $custom) and label [Custom].
     * Multi values are allowed for this variable and All option is included with value 0
     */
    val custom by variables.custom("1", "2", "3") {
        displayName = "Custom"
        multiValuesAllowed = true
        includeAllValue = true
        allValue = "42"
    }

    /* Custom variable with named options
     * Index of selected value can be changed bu `selectedIndex` property
     */
    val customWithNamedOptions by variables.custom(VariableValue("1", "First"), VariableValue("2", "Second")) {
        displayName = "Custom variable with named options"
        multiValuesAllowed = true
        includeAllValue = true
        allValue = "42"
        selectedIndex = 1
    }

    /* Interval variable that will be hidden from dashboard */
    val interval by variables.interval(1.m, 10.m, 1.h) {
        hidingMode = HidingMode.VARIABLE
    }

    /* Query variable that queries applications versions from Zabbix with some filtering expression */
    val query by variables.query(datasource = Zabbix, query = "App version") {
        regex = ".*backend.*"
    }

    /* Text box with predefined value [grafana] */
    val textBox by variables.textBox(defaultValue = "grafana")
}
