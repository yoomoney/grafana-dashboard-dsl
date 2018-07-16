package ru.yandex.money.tools.grafana.dsl

/**
 * Версия JSON-схемы для Grafana HTTP API
 */
private const val schemaVersion = 16

/**
 * Надо придумать, как генерить UID'ы
 */
private fun titleToUid(title: String): String = "${title.hashCode()}"

fun dashboard(build: DashboardBuilder.() -> Unit) {
    val builder = DashboardBuilder()
    builder.build()

    val json = """
        |{
        |   "annotations": {
        |       "list": []
        |   },
        |   "editable": true,
        |   "gnetId": null,
        |   "graphTooltip": 0,
        |   "hideControls": false,
        |   "links": [],
        |   "schemaVersion": $schemaVersion,
        |   "style": "${builder.style.code}",
        |   "tags": [],
        |   "panels": [
        |       {
        |
        |       }
        |   ],
        |   "templating": {
        |       "list": [
        |       ]
        |   },
        |   "time": {
        |       "from": "${builder.time.from}",
        |       "to": "${builder.time.to}"
        |   },
        |   "refresh": "${builder.refresh}",
        |    "timepicker": {
        |        "collapse": true,
        |        "enable": true,
        |        "refresh_intervals": ["5s", "10s", "30s", "1m", "5m", "15m", "30m", "1h", "2h", "1d"],
        |        "time_options": ["5m", "15m", "1h", "6h", "12h", "24h", "2d", "7d", "30d"],
        |        "type": "timepicker"
        |    },
        |    "timezone": "${builder.timezone.code}",
        |    "title": "${builder.title}",
        |    "uid": "${titleToUid(builder.title)}",
        |    "version": 1
        |}
    """.trimMargin()

    println(json)
}
