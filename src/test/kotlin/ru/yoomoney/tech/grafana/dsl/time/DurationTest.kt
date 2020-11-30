package ru.yoomoney.tech.grafana.dsl.time

import org.amshove.kluent.shouldEqual
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class DurationTest {

    @Test(dataProvider = "durations")
    fun `should stringify correctly`(duration: Duration, expected: String) {
        // expect
        duration.asRefreshPeriod() shouldEqual expected
        duration.toString() shouldEqual expected
    }

    @DataProvider
    fun durations() = arrayOf(
            arrayOf(10.s, "10s"),
            arrayOf(10.m, "10m"),
            arrayOf(10.h, "10h"),
            arrayOf(10.d, "10d"),
            arrayOf(10.w, "10w")
    )
}
