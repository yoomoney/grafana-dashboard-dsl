package ru.yoomoney.tech.grafana.dsl.panels

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json

/**
 * Panel on dashboard.
 *
 * @author Dmitry Komarov
 * @since 7/21/18
 */
interface Panel : Json<JSONObject>
