package ru.yandex.money.tools.grafana.dsl.kit

/**
 * Хранит название компонента.
 *
 * @property name стандартное название компонента, используемое для получения метрик
 *               (обычно начинается с маленькой буквы)
 * @property pingName название компонента, используемое для получения Zabbix-метрик
 *                    (обычно начинается с большой буквы)
 *
 * @author Dmitry Komarov
 * @since 01.08.2018
 */
data class Component(val name: String, val pingName: String) {
    constructor(name: String) : this(name, name)
}
