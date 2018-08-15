package ru.yandex.money.tools.grafana.dsl.metrics

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Метрика, которая используется в дэшборде.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/23/18
 */
interface DashboardMetric : Json<JSONObject>
