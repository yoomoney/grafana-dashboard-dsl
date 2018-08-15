package ru.yandex.money.tools.grafana.dsl.datasource

/**
 * Null-datasource.
 */
object NullDatasource : Datasource {
    override fun asDatasourceName(): String? = null
}

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
