package ru.yandex.money.tools.grafana.dsl.panels

import ru.yandex.money.tools.grafana.dsl.generators.PanelLayoutGenerator
import ru.yandex.money.tools.grafana.dsl.generators.SimplePanelLayoutGenerator

class TestContainerBuilder(override val panelLayoutGenerator: PanelLayoutGenerator = SimplePanelLayoutGenerator()) : PanelContainerBuilder {
    override val panels = mutableListOf<Panel>()
}
