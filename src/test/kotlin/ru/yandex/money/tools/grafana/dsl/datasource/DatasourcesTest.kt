package ru.yandex.money.tools.grafana.dsl.datasource

import org.amshove.kluent.shouldEqual
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DatasourcesTest {

    @Test(dataProvider = "representations")
    fun `should serialize data sources correctly`(dataSource: Datasource, representation: String?) {
        dataSource.asDatasourceName() shouldEqual representation
    }

    @DataProvider
    fun representations() = arrayOf(
            arrayOf(Zabbix, "Zabbix"),
            arrayOf(Graphite, "Graphite")
    )
}
