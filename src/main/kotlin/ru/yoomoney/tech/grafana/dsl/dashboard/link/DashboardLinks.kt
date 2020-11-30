package ru.yoomoney.tech.grafana.dsl.dashboard.link

import org.json.JSONArray
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.JsonArray

/**
 * Links of a dashboard
 */
class DashboardLinks(links: List<DashboardLink>) : Json<JSONArray> by JsonArray(links)
