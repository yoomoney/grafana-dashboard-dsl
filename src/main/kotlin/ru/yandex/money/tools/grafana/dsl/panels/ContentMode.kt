package ru.yandex.money.tools.grafana.dsl.panels

/**
 * Text panel content mode
 * @author Aleksandr Korkin
 * @since 27.09.2019
 * */
enum class ContentMode(val value: String) {
    MARKDOWN("markdown"),
    HTML("html")
}
