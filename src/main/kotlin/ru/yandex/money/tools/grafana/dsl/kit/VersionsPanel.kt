package ru.yandex.money.tools.grafana.dsl.kit

import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.json.emptyJsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonArray
import ru.yandex.money.tools.grafana.dsl.json.jsonObject
import ru.yandex.money.tools.grafana.dsl.metrics.DashboardMetric
import ru.yandex.money.tools.grafana.dsl.metrics.Metrics
import ru.yandex.money.tools.grafana.dsl.panels.BasePanel
import ru.yandex.money.tools.grafana.dsl.panels.MetricPanel
import ru.yandex.money.tools.grafana.dsl.panels.Panel
import ru.yandex.money.tools.grafana.dsl.panels.PanelsBuilder
import ru.yandex.money.tools.grafana.dsl.panels.Position
import ru.yandex.money.tools.grafana.dsl.panels.idGenerator
import ru.yandex.money.tools.grafana.dsl.panels.nextPosition
import ru.yandex.money.tools.grafana.dsl.variables.Variable

/**
 * Группа панелей (см. свойство [repeat]), которые отображают развернутую версию компонента на некотором хосте.
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 7/21/18
 */
class VersionsPanel(private val hosts: Variable, position: Position, componentName: String) : Panel {

    override fun toJson() = jsonObject(basePanel.toJson()) {
        "type" to "singlestat"
        "hideTimeOverride" to true
        "mappingType" to 1
        "mappingTypes" to jsonArray[
            jsonObject {
                "name" to "value to text"
                "value" to 1
            }
        ]
        "valueMaps" to jsonArray[
            jsonObject {
                "op" to "="
                "text" to "N/A"
                "value" to "null"
            }
        ]
        "repeat" to hosts.name
        "minSpan" to 2
    }

    private val basePanel = MetricPanel(
            BasePanel(
                    id = idGenerator++,
                    title = hosts.asVariable(),
                    position = position
            ),
            datasource = Zabbix,
            nullPointMode = "connected",
            metrics = Metrics(
                    listOf(
                            object : DashboardMetric {
                                override fun toJson() = jsonObject {
                                    "application" to jsonObject {
                                        "filter" to "App $componentName Ping General"
                                    }
                                    "functions" to emptyJsonArray()
                                    "group" to jsonObject {
                                        "filter" to "/.*/"
                                    }
                                    "host" to jsonObject {
                                        "filter" to hosts.asVariable()
                                    }
                                    "item" to jsonObject {
                                        "filter" to "/service version/"
                                    }
                                    "mode" to 2
                                    "options" to jsonObject {
                                        "showDisabledItems" to false
                                    }
                                    "triggers" to jsonObject {
                                        "acknowledged" to 2
                                        "count" to true
                                        "minSeverity" to 3
                                    }
                                    "useCaptureGroups" to false
                                }
                            }
                    )
            )
    )
}

fun PanelsBuilder.versions(hosts: Variable, componentName: String) {
    this.row("Versions") {
        panels += VersionsPanel(hosts, nextPosition(3, 3), componentName)
    }
}
