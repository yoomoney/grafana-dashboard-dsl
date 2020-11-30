package ru.yoomoney.tech.grafana.dsl.panels

/**
 * Represents color for [AliasColors].
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
data class Color(val red: Int, val green: Int, val blue: Int) {

    /**
     * Constructs [Color] from integer representation where:
     *
     * + bits from 24 to 31 are ignored
     * + bits from 16 to 23 are [red] color component
     * + bits from 8 to 15 are [blue] color component
     * + bits from 0 to 7 are [green] color component
     */
    constructor(rgb: Int) : this(
        red = rgb shr 16 and 0xFF,
        green = rgb shr 8 and 0xFF,
        blue = rgb and 0xFF
    )

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

    /**
     * Alias for [Color.asHexString].
     */
    fun asString(): String = asHexString()

    /**
     * Returns color string representation in hexadecimal form: 6 hexadecimal chars prepended by #.
     *
     * first 2 hex chars are [red] color component
     * second 2 hex chars are [blue] color component
     * third 2 hex chars are [green] color component
     */
    fun asHexString(): String = String.format("#%02x%02x%02x", red, green, blue)

    /**
     * Returns color string representation in RGBa format:
     *
     * rgba(int([red]), int([green]), int([blue]), 1)
     *
     * Note that 4th component of this format is always `1` and can't be changes now.
     */
    fun asRgbaString(): String = "rgba($red,$green,$blue,1)"

    companion object {

        /**
         * Common colors, that can be chosen from color picker in Grafana
         */
        val GREEN = Color(0x7EB26D)
        val YELLOW = Color(0xEAB839)
        val RED = Color(0xBF1B00)
        val PURPLE = Color(0x3F2B5B)
        val DARK_GREEN = Color(0x3F6833)
        val ORANGE = Color(0xEF843C)
        val DARK_RED = Color(0x890F02)
        val BLUE = Color(0x64B0C8)
        val CORAL = Color(0xE24D42)

        val WHITE = Color(0xFCE2DE)

        /**
         * Alias for secondary [Color] constructor.
         */
        fun of(rgb: Int): Color = Color(rgb)
    }
}
