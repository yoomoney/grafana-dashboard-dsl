package ru.yoomoney.tech.grafana.dsl.annotations

/**
 * A severity of Zabbix trigger events.
 *
 * Trigger severity defines how important a trigger is.
 */
enum class ZabbixTriggerSeverity(val code: Int) {

    /**
     * Unknown severity.
     */
    NOT_CLASSIFIED(0),

    /**
     * For information purposes.
     */
    INFORMATION(1),

    /**
     * Be warned.
     */
    WARNING(2),

    /**
     * Average problem.
     */
    AVERAGE(3),

    /**
     * Something important has happened.
     */
    HIGH(4),

    /**
     * Disaster. Financial losses, etc.
     */
    DISASTER(5)
}
