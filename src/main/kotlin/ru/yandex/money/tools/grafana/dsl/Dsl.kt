package ru.yandex.money.tools.grafana.dsl

/**
 * Версия JSON-схемы для Grafana HTTP API
 */
@Suppress("TopLevelPropertyNaming")
private const val schemaVersion = 16

fun dashboard(build: DashboardBuilder.() -> Unit): String {
    val builder = DashboardBuilder()
    builder.build()

    return """
        |{
        |   "annotations": {
        |       "list": []
        |   },
        |   "editable": false,
        |   "gnetId": null,
        |   "graphTooltip": 0,
        |   "links": [],
        |   "schemaVersion": $schemaVersion,
        |   "style": "${builder.style.code}",
        |   "tags": [],
        |   "panels": ${builder.panels},
        |   "templating": {
        |       "list": []
        |   },
        |   "time": {
        |       "from": "${builder.time.from}",
        |       "to": "${builder.time.to}"
        |   },
        |   "refresh": "${builder.refresh}",
        |    "timepicker": {
        |        "collapse": true,
        |        "enable": true,
        |        "now": true,
        |        "refresh_intervals": ["5s", "10s", "30s", "1m", "5m", "15m", "30m", "1h", "2h", "1d"],
        |        "time_options": ["5m", "15m", "1h", "6h", "12h", "24h", "2d", "7d", "30d"],
        |        "type": "timepicker"
        |    },
        |    "timezone": "${builder.timezone.code}",
        |    "title": "${builder.title}",
        |    "version": 1
        |}
    """.trimMargin()
}
