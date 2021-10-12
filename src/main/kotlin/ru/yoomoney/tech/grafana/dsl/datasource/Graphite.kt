package ru.yoomoney.tech.grafana.dsl.datasource

/**
 * Graphite datasource type
 */
interface GraphiteDatasource : Datasource

/**
 * Predefined graphite datasource
 */
object Graphite : GraphiteDatasource {
    override fun asDatasourceName() = "Graphite"
}

/**
 * Create graphite datasource
 */
fun graphiteDatasource(name: String): GraphiteDatasource = SimpleGraphiteDatasource(name)

internal class SimpleGraphiteDatasource(private val name: String) : GraphiteDatasource {
    override fun asDatasourceName(): String = name
}