package ru.yandex.money.tools.grafana.dsl.time

import org.amshove.kluent.shouldEqual
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import ru.yandex.money.tools.testing.kotlin.and
import ru.yandex.money.tools.testing.kotlin.provide

class DurationTest {

    @Test(dataProvider = "durations")
    fun `should stringify correctly`(duration: Duration, expected: String) {
        // expect
        duration.asRefreshPeriod() shouldEqual expected
        duration.toString() shouldEqual expected
    }

    @DataProvider
    fun durations() = provide(
            10.s and "10s",
            10.m and "10m",
            10.h and "10h",
            10.d and "10d",
            10.w and "10w"
    )
}
