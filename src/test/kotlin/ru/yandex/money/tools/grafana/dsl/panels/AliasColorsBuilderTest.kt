package ru.yandex.money.tools.grafana.dsl.panels

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.panels.Color.Companion.GREEN
import ru.yandex.money.tools.grafana.dsl.panels.Color.Companion.RED

/**
 * [AliasColors] and [AliasColorsBuilder] Test
 */
class AliasColorsBuilderTest {

    @Test
    fun `should create not empty alias colors block`() {

        val builder = AliasColorsBuilder().apply {
            "success" to GREEN
            "fail" to RED
        }

        builder.aliasColors.toJson().toString() shouldEqual """{"fail":"#bf1b00","success":"#7eb26d"}"""
    }

    @Test
    fun `should create empty alias colors block`() {

        AliasColorsBuilder().aliasColors.toJson().toString() shouldEqual """{}"""
    }
}