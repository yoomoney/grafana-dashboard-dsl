package ru.yoomoney.tech.grafana.dsl.examples.dashboard

import ru.yoomoney.tech.grafana.dsl.dashboard
import ru.yoomoney.tech.grafana.dsl.json.set
import ru.yoomoney.tech.grafana.dsl.panels.panel

/**
 * Example of using an editable dashboard
 * @author Aleksandr Korkin
 * @since 09.10.2019
 */
dashboard(title = "Editable dashboard") {
    editable = true
    panels {
        panel(title = "Test Panel") {
            properties {
                it["type"] = "graph"
            }
        }
    }
}