package ru.yoomoney.tech.grafana.dsl.metrics

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json

/**
 * Metric, that can be used in a dashboard.
 *
 * @author Dmitry Komarov
 * @since 7/23/18
 */
interface DashboardMetric : Json<JSONObject> {
    val id: String?
}
