package ru.yandex.money.tools.grafana.dsl.panels.graph.display.drawoptions

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.jsonFile
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

/**
 * Tests for create Hover tooltip
 *
 * @author Aleksey Antufev
 * @since 05.09.2019
 */
class HoverTooltipTest {

    @Test
    fun `should create default hover tooltip`() {
        HoverTooltip().toJson().toString() shouldEqualToJson jsonFile("DefaultHoverTooltip.json")
    }

    @Test
    fun `should create custom hover tooltip`() {
        val hoverTooltip = HoverTooltip(HoverTooltip.Mode.SINGLE, HoverTooltip.SortOrder.DECREASING, HoverTooltip.StackedValue.CUMULATIVE)
        hoverTooltip.toJson().toString() shouldEqualToJson jsonFile("CustomHoverTooltip.json")
    }
}