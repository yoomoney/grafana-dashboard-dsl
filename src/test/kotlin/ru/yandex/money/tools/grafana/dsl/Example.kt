package ru.yandex.money.tools.grafana.dsl

import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.kit.Component
import ru.yandex.money.tools.grafana.dsl.kit.INCOMING_REQUESTS
import ru.yandex.money.tools.grafana.dsl.kit.OUTGOING_REQUESTS
import ru.yandex.money.tools.grafana.dsl.kit.PORTAL_BACK_OTHER
import ru.yandex.money.tools.grafana.dsl.kit.QUEUE
import ru.yandex.money.tools.grafana.dsl.kit.commonInfo
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.now

fun main(args: Array<String>) {
    val dashboard = dashboard(title = "Catalog common info (autogen)") {
        timeRange = now-2.h .. now

        val hosts by variable(datasource = Zabbix) {
            query(PORTAL_BACK_OTHER) {
                regex = ".*catalog.*"
            }
        }

        val autoInterval by variable {
            interval(1.m, 10.m, 30.m, 1.h)
        }

        tags += "catalog"

        commonInfo(hosts, autoInterval, Component("catalog", "Catalog")) {
            notifications {
                id { 181 }
            }

            thresholds {
                INCOMING_REQUESTS { 20 }
                OUTGOING_REQUESTS { 20 }
                QUEUE { 15 }
            }
        }
    }

    println(dashboard)
}


