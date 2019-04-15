package ru.yandex.money.tools.grafana.dsl.annotations

import org.json.JSONArray
import ru.yandex.money.tools.grafana.dsl.json.Json
import ru.yandex.money.tools.grafana.dsl.json.JsonArray

/**
 * A collection of annotations.
 */
class Annotations(annotations: List<Annotation>) : Json<JSONArray> by JsonArray(annotations)
