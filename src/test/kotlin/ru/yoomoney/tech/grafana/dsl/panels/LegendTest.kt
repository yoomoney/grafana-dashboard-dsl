package ru.yoomoney.tech.grafana.dsl.panels

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

/**
 * [Legend] Test
 */
class LegendTest {

    @Test
    fun `should create default legend block`() {
        Legend.DEFAULT.toJson().toString() shouldEqualToJson jsonFile("DefaultLegendBlock.json")
    }

    @Test
    fun `should create empty legend block`() {
        Legend.EMPTY.toJson().toString() shouldEqualToJson jsonFile("EmptyLegendBlock.json")
    }

    @Test
    fun `should create custom legend block`() {

        val legend = Legend(alignAsTable = true,
                avg = false,
                current = true,
                hideEmpty = true,
                hideZero = true,
                max = true,
                min = false,
                rightSide = false,
                show = true,
                sort = null,
                sortDesc = false,
                total = false,
                values = true
        )
        legend.toJson().toString() shouldEqualToJson jsonFile("CustomLegendBlock.json")
    }
}