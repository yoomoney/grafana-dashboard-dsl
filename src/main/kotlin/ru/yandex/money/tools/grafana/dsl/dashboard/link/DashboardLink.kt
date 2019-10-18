package ru.yandex.money.tools.grafana.dsl.dashboard.link

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * Link object for dashboard
 *
 * @see <a href="https://grafana.com/docs/guides/whats-new-in-v2-1/#dashboard-links-and-navigation">Dashboard links</a>
 *
 * @author Ilya Doroshenko (ivdoroshenko@yamoney.ru)
 * @since 17.10.2019
 */
interface DashboardLink : Json<JSONObject>
