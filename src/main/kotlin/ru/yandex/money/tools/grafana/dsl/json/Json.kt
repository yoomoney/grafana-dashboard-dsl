package ru.yandex.money.tools.grafana.dsl.json

/**
 * Represents an object that can be serialized to JSON.
 *
 * @param J Type of JSON object to be serialized to (string, array, object, etc)
 *
 * @author Dmitry Komarov (komarovdmitry@yamoney.ru)
 * @since 24.07.2018
 */
interface Json<out J> {

    /**
     * returns object as JSON.
     *
     * @return JSON
     */
    fun toJson(): J
}
