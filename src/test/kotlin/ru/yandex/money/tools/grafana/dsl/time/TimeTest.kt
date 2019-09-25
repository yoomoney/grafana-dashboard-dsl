package ru.yandex.money.tools.grafana.dsl.time

import org.testng.annotations.Test
import ru.yandex.money.tools.grafana.dsl.shouldEqualToJson

class TimeTest {

    @Test
    fun `should create range correctly`() {
        // when
        val range = now - 1.w..now

        // then
        range.toJson().toString() shouldEqualToJson """{"from": "now-1w", "to": "now"}"""
    }
}