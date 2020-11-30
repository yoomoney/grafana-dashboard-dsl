package ru.yoomoney.tech.grafana.dsl.annotations

import org.json.JSONObject
import ru.yoomoney.tech.grafana.dsl.json.Json

/**
 * An annotation that displayed on graphs.
 *
 * Annotations can be used to mark some important events, e.g. new version release or start of marketing company.
 */
interface Annotation : Json<JSONObject>
