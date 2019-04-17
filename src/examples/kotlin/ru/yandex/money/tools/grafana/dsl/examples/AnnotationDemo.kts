package ru.yandex.money.tools.grafana.dsl.examples

import ru.yandex.money.tools.grafana.dsl.annotations.zabbix
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.panels.Color

dashboard(title = "Grafana Annotations Demo") {

    tags += arrayOf("annotations", "demo")

    annotations {
        // Simple Zabbix annotation
        annotation(name = "Test annotation") {
            zabbix {
                group = "Zabbix"
                host = "/ci/"
                application = "/backend/"
                trigger = "/release-start/"
            }
        }

        // Disabled annotation
        annotation(name = "Disabled annotation") {
            enabled = false

            zabbix {
                group = "Zabbix"
                host = "/ci/"
                application = "/backend/"
                trigger = "/release-success/"
            }
        }

        // Hidden and colored annotation
        annotation(name = "Hidden and colored annotation") {
            hidden = true // Marks will be displayed anyway but it be unable to disable this annotation
            color = Color.RED

            zabbix {
                group = "Zabbix"
                host = "/ci/"
                application = "/backend/"
                trigger = "/release-fail/"

                showHostName = true // Show host name in annotation mark's tooltip
            }
        }
    }
}
