package ru.yandex.money.tools.grafana.dsl.metrics

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Metric, that can be used in a dashboard.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
interface DashboardMetric : Json<JSONObject>
