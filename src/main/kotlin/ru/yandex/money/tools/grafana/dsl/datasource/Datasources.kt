package ru.yandex.money.tools.grafana.dsl.datasource

/**
 * Null-datasource.
 */
@Deprecated("This object will be deleted in 2.0.0. Nulls are not supported for datasource name any more.")
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
