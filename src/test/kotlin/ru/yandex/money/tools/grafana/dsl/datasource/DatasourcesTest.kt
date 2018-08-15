package ru.yandex.money.tools.grafana.dsl.datasource

import org.amshove.kluent.shouldEqual
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import ru.yandex.money.tools.testing.kotlin.and
import ru.yandex.money.tools.testing.kotlin.provide

class DatasourcesTest {

    @Test(dataProvider = "representations")
    fun `should serialize data sources correctly`(dataSource: Datasource, representation: String?) {
        dataSource.asDatasourceName() shouldEqual representation
    }

    @DataProvider
    fun representations() = provide(
            NullDatasource and null,
            Zabbix and "Zabbix",
            Graphite and "Graphite"
    )
}
