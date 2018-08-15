package ru.yandex.money.tools.grafana.dsl.json

/**
 * Представляет объект, который может быть сериализован в JSON.
 *
 * @param J тип JSON-сущности, в которую может быть сериализован объект (например, строка, массив, объект)
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 24.07.2018
 */
interface Json<out J> {

    /**
     * Возвращает JSON-представление объекта.
     *
     * @return JSON-представление
     */
    fun toJson(): J
}
