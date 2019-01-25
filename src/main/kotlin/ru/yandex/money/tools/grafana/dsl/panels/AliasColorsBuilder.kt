package ru.yandex.money.tools.grafana.dsl.panels

/**
 * Билдер для [AliasColors]
 *
 * @author Dmitry Pavlov (dupavlov@yamoney.ru)
 * @since 11.01.2019
 */
class AliasColorsBuilder {

    internal val aliasColors: AliasColors = AliasColors()

    infix fun String.to(value: Color) {
        aliasColors[this] = value
    }
}
