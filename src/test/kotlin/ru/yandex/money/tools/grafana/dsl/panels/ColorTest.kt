package ru.yandex.money.tools.grafana.dsl.panels

import org.amshove.kluent.shouldEqual
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

/**
 * Тестирует [Color]
 *
 * @author dupavlov
 */
class ColorTest {

    @Test
    fun `should create Color instance via constructor`() {
        val color = Color(126, 178, 109)
        color.red shouldEqual 126
        color.green shouldEqual 178
        color.blue shouldEqual 109
        color.asString() shouldEqual "#7eb26d"
    }

    @Test
    fun `should create Color instance via factory`() {
        val color = Color.of(0x7EB26D)
        color.red shouldEqual 126
        color.green shouldEqual 178
        color.blue shouldEqual 109
        color.asString() shouldEqual "#7eb26d"
    }

    @DataProvider
    fun dataForExceptionHandlingTest() = arrayOf(
            arrayOf(280, 0, 0),
            arrayOf(128, -172, 0),
            arrayOf(128, 255, 400)
    )

    @Test(dataProvider = "dataForExceptionHandlingTest",
            expectedExceptions = [IllegalArgumentException::class],
            expectedExceptionsMessageRegExp = ".*must be between 0 and 255.*")
    fun `should throw exception if one pf chanel values id out of bounds`(red: Int, green: Int, blue: Int) {
        Color(red, green, blue)
    }

    @DataProvider
    fun dataForTestingConstants() = arrayOf(
            arrayOf(Color.GREEN, "#7eb26d"),
            arrayOf(Color.YELLOW, "#eab839"),
            arrayOf(Color.RED, "#bf1b00"),
            arrayOf(Color.PURPLE, "#3f2b5b"),
            arrayOf(Color.DARK_GREEN, "#3f6833"),
            arrayOf(Color.ORANGE, "#ef843c"),
            arrayOf(Color.DARK_RED, "#890f02"),
            arrayOf(Color.BLUE, "#64b0c8"),
            arrayOf(Color.CORAL, "#e24d42"),
            arrayOf(Color.WHITE, "#fce2de")
    )

    @Test(dataProvider = "dataForTestingConstants")
    fun `should return appropriate value for each constant`(color: Color, value: String) {
        color.asString() shouldEqual value
    }

}
