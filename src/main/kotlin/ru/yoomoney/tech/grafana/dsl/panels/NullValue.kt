package ru.yoomoney.tech.grafana.dsl.panels

/**
 * Display mode for null values
 * @author Aleksey Antufev
 * @since 11.09.2019
 * */
enum class NullValue(val value: String) {

    CONNECTED("connected"),
    NULL("null"),
    NULL_AS_ZERO("null as zero")
}
