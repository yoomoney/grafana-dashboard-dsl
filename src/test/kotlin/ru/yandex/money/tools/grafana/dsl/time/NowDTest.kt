package ru.yandex.money.tools.grafana.dsl.time

import org.amshove.kluent.shouldEqual
import org.testng.annotations.Test

/**
 * [nowD] Test
 */
class NowDTest {

    @Test
    fun `should stringify correctly`() {
        // expect
        nowD.toJson() shouldEqual "now/d"
        nowD.toString() shouldEqual "now/d"
    }
}
