package ru.yoomoney.tech.grafana.dsl.annotations

import org.json.JSONArray
import ru.yoomoney.tech.grafana.dsl.json.Json
import ru.yoomoney.tech.grafana.dsl.json.JsonArray

/**
 * A collection of annotations.
 */
class Annotations(annotations: List<Annotation>) : Json<JSONArray> by JsonArray(annotations)
