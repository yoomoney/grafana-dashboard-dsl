package ru.yandex.money.tools.grafana.dsl.datasource

/**
 * Zabbix.
 */
object Zabbix : Datasource {
    override fun asDatasourceName() = "Zabbix"
}

/**
 * Graphite
 */
object Graphite : Datasource {
    override fun asDatasourceName() = "Graphite"
}
