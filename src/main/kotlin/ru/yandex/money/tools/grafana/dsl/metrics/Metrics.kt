package ru.yandex.money.tools.grafana.dsl.metrics

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

/**
 * Metrics collection.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
class Metrics(dashboardMetrics: Collection<DashboardMetric>) : Json<JSONArray> by JsonArray(dashboardMetrics)
