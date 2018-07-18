package ru.yandex.money.tools.grafana.dsl

fun main(args: Array<String>) {
    dashboard {
        title = "SomeComponent common info"
        time = now - 6.h..now
        refresh = PeriodicRefresh(5.s)

        panels {
            jsonPanel {
                """
                    {
                      "aliasColors": {},
                      "bars": false,
                      "dashLength": 10,
                      "dashes": false,
                      "datasource": null,
                      "decimals": 0,
                      "editable": true,
                      "error": false,
                      "fill": 1,
                      "grid": {},
                      "gridPos": {
                        "h": 8,
                        "w": 6,
                        "x": 0,
                        "y": 0
                      },
                      "height": "300px",
                      "id": 1,
                      "legend": {
                        "alignAsTable": true,
                        "avg": true,
                        "current": true,
                        "hideEmpty": true,
                        "hideZero": true,
                        "max": true,
                        "min": true,
                        "rightSide": false,
                        "show": true,
                        "sideWidth": 540,
                        "sort": "total",
                        "sortDesc": true,
                        "total": true,
                        "values": true
                      },
                      "lines": true,
                      "linewidth": 2,
                      "links": [],
                      "nullPointMode": "null as zero",
                      "percentage": false,
                      "pointradius": 5,
                      "points": false,
                      "renderer": "flot",
                      "seriesOverrides": [],
                      "spaceLength": 10,
                      "stack": true,
                      "steppedLine": false,
                      "targets": [
                        {
                          "hide": false,
                          "refId": "A",
                          "target": "groupByNode(*.*.*.processes.queue.aviso_delivery.commonHttp30.*.process_time.succeeded.upper, 1, 'avg')",
                          "textEditor": false
                        }
                      ],
                      "thresholds": [],
                      "timeFrom": null,
                      "timeShift": null,
                      "title": "Failed incoming requests",
                      "tooltip": {
                        "msResolution": false,
                        "shared": true,
                        "sort": 0,
                        "value_type": "cumulative"
                      },
                      "type": "graph",
                      "xaxis": {
                        "buckets": null,
                        "mode": "time",
                        "name": null,
                        "show": true,
                        "values": []
                      },
                      "yaxes": [
                        {
                          "format": "ms",
                          "label": null,
                          "logBase": 2,
                          "max": null,
                          "min": null,
                          "show": true
                        },
                        {
                          "format": "short",
                          "label": null,
                          "logBase": 1,
                          "max": null,
                          "min": null,
                          "show": true
                        }
                      ]
                    }
                """
            }

            jsonPanel {
                """
                    {
                      "aliasColors": {},
                      "bars": true,
                      "dashLength": 10,
                      "dashes": false,
                      "datasource": null,
                      "editable": true,
                      "error": false,
                      "fill": 1,
                      "grid": {},
                      "gridPos": {
                        "h": 8,
                        "w": 6,
                        "x": 6,
                        "y": 0
                      },
                      "height": "300px",
                      "id": 2,
                      "legend": {
                        "alignAsTable": true,
                        "avg": true,
                        "current": true,
                        "hideEmpty": true,
                        "hideZero": true,
                        "max": true,
                        "min": true,
                        "rightSide": false,
                        "show": true,
                        "sideWidth": 540,
                        "sort": "total",
                        "sortDesc": true,
                        "total": true,
                        "values": true
                      },
                      "lines": false,
                      "linewidth": 2,
                      "links": [],
                      "nullPointMode": "null as zero",
                      "percentage": false,
                      "pointradius": 5,
                      "points": false,
                      "renderer": "flot",
                      "seriesOverrides": [],
                      "spaceLength": 10,
                      "stack": true,
                      "steppedLine": false,
                      "targets": [
                        {
                          "hide": true,
                          "refId": "A",
                          "target": "groupByNode(*.*.makeupd.requests.outgoing.*._1_validation_login_validateLogin.process_time.succeeded.count, 6, 'sum')",
                          "textEditor": false
                        },
                        {
                          "hide": true,
                          "refId": "B",
                          "target": "groupByNode(*.*.makeupd.requests.outgoing.*._1_validation_login_validateLogin.process_time.*.count, 6, 'sum')",
                          "textEditor": false
                        },
                        {
                          "refId": "C",
                          "target": "groupByNode(group(scale(#A, 100), #B), 0,'divideSeries')",
                          "targetFull": "groupByNode(group(scale(groupByNode(*.*.makeupd.requests.outgoing.*._1_validation_login_validateLogin.process_time.succeeded.count, 6, 'sum'), 100), groupByNode(*.*.makeupd.requests.outgoing.*._1_validation_login_validateLogin.process_time.*.count, 6, 'sum')), 0,'divideSeries')",
                          "textEditor": true
                        }
                      ],
                      "thresholds": [],
                      "timeFrom": null,
                      "timeShift": null,
                      "title": "Validate login",
                      "tooltip": {
                        "msResolution": false,
                        "shared": false,
                        "sort": 2,
                        "value_type": "cumulative"
                      },
                      "transparent": false,
                      "type": "graph",
                      "xaxis": {
                        "buckets": null,
                        "mode": "time",
                        "name": null,
                        "show": true,
                        "values": []
                      },
                      "yaxes": [
                        {
                          "format": "short",
                          "label": null,
                          "logBase": 1,
                          "max": null,
                          "min": null,
                          "show": true
                        },
                        {
                          "format": "short",
                          "label": null,
                          "logBase": 1,
                          "max": null,
                          "min": null,
                          "show": true
                        }
                      ]
                    }
                """
            }
        }
    }
}
