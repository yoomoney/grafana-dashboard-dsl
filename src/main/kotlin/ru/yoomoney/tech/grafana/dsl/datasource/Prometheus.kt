package ru.yoomoney.tech.grafana.dsl.datasource

/**
 * Prometheus datasource
 */
interface PrometheusDatasource : Datasource

/**
 * Predefined "PromQL" prometheus datasource
 */
object PromQl : PrometheusDatasource {
    override fun asDatasourceName() = "PromQL"
}

/**
 * Predefined "Prometheus" prometheus datasource
 */
object Prometheus : PrometheusDatasource {
    override fun asDatasourceName() = "Prometheus"
}

/**
 * Create graphite datasource
 */
fun prometheusDatasource(name: String): PrometheusDatasource = SimplePrometheusDatasource(name)

internal class SimplePrometheusDatasource(private val name: String) : PrometheusDatasource {
    override fun asDatasourceName(): String = name
}