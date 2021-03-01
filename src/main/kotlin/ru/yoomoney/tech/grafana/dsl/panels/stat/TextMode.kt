package ru.yoomoney.tech.grafana.dsl.panels.stat

/**
 * Used to control what text the panel renders
 * @author Aleksey Matveev
 * @since 03.11.2020
 */
enum class TextMode(val value: String) {
    /**
     * If the data contains multiple series or fields, show both name and value
     */
    AUTO("auto"),

    /**
     * Show only value, never name. Name is displayed in the hover tooltip instead
     */
    VALUE("value"),

    /**
     * Always show value and name
     */
    VALUE_AND_NAME("value and name"),

    /**
     * Show name instead of value. Value is displayed in the hover tooltip
     */
    NAME("name"),

    /**
     * Show nothing (empty). Name and value are displayed in the hover tooltip
     */
    NONE("none");
}
