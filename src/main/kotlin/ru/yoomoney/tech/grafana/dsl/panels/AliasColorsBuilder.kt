package ru.yoomoney.tech.grafana.dsl.panels

/**
 * Builder for [AliasColors]
 *
 * @author Dmitry Pavlov
 * @since 11.01.2019
 */
class AliasColorsBuilder {

    internal val aliasColors: AliasColors = AliasColors()

    infix fun String.to(value: Color) {
        aliasColors[this] = value
    }
}
