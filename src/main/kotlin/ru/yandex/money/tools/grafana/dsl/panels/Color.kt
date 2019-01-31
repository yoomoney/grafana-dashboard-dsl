package ru.yandex.money.tools.grafana.dsl.panels

/**
 * Represents color for [AliasColors]
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
data class Color(val red: Int, val green: Int, val blue: Int) {

    init {
        if (red < 0 || red > 255) {
            throw IllegalArgumentException("red must be between 0 and 255")
        }
        if (green < 0 || green > 255) {
            throw IllegalArgumentException("green must be between 0 and 255")
        }
        if (blue < 0 || blue > 255) {
            throw IllegalArgumentException("blue must be between 0 and 255")
        }
    }

    companion object {

        /**
         * Common colors
         */
        val GREEN = Color.of(0x7EB26D)
        val YELLOW = Color.of(0xEAB839)
        val RED = Color.of(0xBF1B00)
        val PURPLE = Color.of(0x3F2B5B)
        val DARK_GREEN = Color.of(0x3F6833)
        val ORANGE = Color.of(0xEF843C)
        val DARK_RED = Color.of(0x890F02)
        val BLUE = Color.of(0x64B0C8)
        val CORAL = Color.of(0xE24D42)
        val WHITE = Color.of(0xFCE2DE)

        /**
         * Constructs color for hex
         */
        fun of(rgb: Int): Color {
            val red = rgb shr 16 and 0xFF
            val green = rgb shr 8 and 0xFF
            val blue = rgb and 0xFF
            return Color(red, green, blue)
        }
    }

    fun asString(): String {
        return String.format("#%02x%02x%02x", red, green, blue)
    }
}
