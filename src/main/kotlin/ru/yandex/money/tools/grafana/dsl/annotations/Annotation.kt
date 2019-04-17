package ru.yandex.money.tools.grafana.dsl.annotations

import org.json.JSONObject
import ru.yandex.money.tools.grafana.dsl.json.Json

/**
 * An annotation that displayed on graphs.
 *
 * Annotations can be used to mark some important events, e.g. new version release or start of marketing company.
 */
interface Annotation : Json<JSONObject>
