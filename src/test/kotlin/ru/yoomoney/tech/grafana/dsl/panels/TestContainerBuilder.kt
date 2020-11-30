package ru.yoomoney.tech.grafana.dsl.panels

import ru.yoomoney.tech.grafana.dsl.generators.PanelLayoutGenerator
import ru.yoomoney.tech.grafana.dsl.generators.SimplePanelLayoutGenerator

class TestContainerBuilder(override val panelLayoutGenerator: PanelLayoutGenerator = SimplePanelLayoutGenerator()) : PanelContainerBuilder {
    override val panels = mutableListOf<Panel>()
}
