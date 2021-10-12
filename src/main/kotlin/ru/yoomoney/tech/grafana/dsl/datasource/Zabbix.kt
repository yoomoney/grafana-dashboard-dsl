package ru.yoomoney.tech.grafana.dsl.datasource

/**
 * Zabbix datasource type
 */
interface ZabbixDatasource : Datasource

/**
 * Predefined zabbix datasource
 */
object Zabbix : ZabbixDatasource {
    override fun asDatasourceName() = "Zabbix"
}

/**
 * Create zabbix datasource
 */
fun zabbixDatasource(name: String): ZabbixDatasource = SimpleZabbixDatasource(name)

internal class SimpleZabbixDatasource(private val name: String) : ZabbixDatasource {
    override fun asDatasourceName(): String = name
}
