package ru.yandex.money.tools.grafana.dsl.examples.dashboard

import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.panels.panel

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