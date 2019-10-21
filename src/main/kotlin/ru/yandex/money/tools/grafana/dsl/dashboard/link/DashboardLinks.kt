package ru.yandex.money.tools.grafana.dsl.dashboard.link

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

/**
 * Links of a dashboard
 */
class DashboardLinks(links: List<DashboardLink>) : Json<JSONArray> by JsonArray(links)
