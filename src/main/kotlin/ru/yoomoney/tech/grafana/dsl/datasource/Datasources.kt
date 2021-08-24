package ru.yoomoney.tech.grafana.dsl.datasource

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

/**
 * PromQL
 */
object PromQl : Datasource {
    override fun asDatasourceName() = "PromQL"
}
