package ru.yoomoney.tech.grafana.dsl.metrics

import org.json.JSONArray
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.JsonArray

/**
 * Metrics collection.
 *
 * @author Dmitry Komarov
 * @since 7/23/18
 */
class Metrics(dashboardMetrics: Collection<DashboardMetric>) : Json<JSONArray> by JsonArray(dashboardMetrics)
