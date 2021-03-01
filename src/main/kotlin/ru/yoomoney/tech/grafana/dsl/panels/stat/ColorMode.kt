package ru.yoomoney.tech.grafana.dsl.panels.stat

/**
 * Target of staining
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
enum class ColorMode(val value: String) {
    /**
     * Colors only the value and graph area
     */
    VALUE("value"),

    /**
     * Colors the background as well
     */
    BACKGROUND("background");
}
