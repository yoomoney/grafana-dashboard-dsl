package ru.yoomoney.tech.grafana.dsl.annotations

import ru.yoomoney.tech.grafana.dsl.DashboardElement
import ru.yoomoney.tech.grafana.dsl.datasource.Zabbix
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.panels.Color

/**
 * An annotation with [Zabbix] datasource.
 */
class ZabbixAnnotation private constructor(
    private val annotation: Annotation,
    private val group: String,
    private val host: String,
    private val application: String,
    private val trigger: String,
    private val minSeverity: ZabbixTriggerSeverity,
    private val showOkEvents: Boolean,
    private val hideAcknowledgedEvents: Boolean,
    private val showHostName: Boolean
) : Annotation {

    /**
     * Public constructor.
     *
     * @param name see [BasicAnnotation.name]
     * @param enabled see [BasicAnnotation.enabled]
     * @param hidden see [BasicAnnotation.hidden]
     * @param color see [BasicAnnotation.color]
     * @param group the group of Zabbix hosts that will be used to querying information. It can be concrete group name
     * or regular expression
     * @param host the host from Zabbix [group] that will be used to querying information. It can be concrete host name
     * or regular expression
     * @param application the application on selected Zabbix [host] that will be used to querying information. It can be
     * concrete application name or regular expression
     * @param trigger the Zabbix trigger that will be used to querying information. It can be concrete trigger name or
     * regular expression
     * @param minSeverity the minimal severity of trigger events
     * @param showOkEvents show OK events or not
     * @param hideAcknowledgedEvents hide acknowledged events or not
     * @param showHostName show host name in annotation mark's tooltip or not
     */
    constructor(
        name: String,
        enabled: Boolean,
        hidden: Boolean,
        color: Color,
        group: String,
        host: String,
        application: String,
        trigger: String,
        minSeverity: ZabbixTriggerSeverity,
        showOkEvents: Boolean,
        hideAcknowledgedEvents: Boolean,
        showHostName: Boolean
    ) : this(
        annotation = BasicAnnotation(
            name = name,
            enabled = enabled,
            hidden = hidden,
            color = color,
            datasource = Zabbix
        ),
        group = group,
        host = host,
        application = application,
        trigger = trigger,
        minSeverity = minSeverity,
        showOkEvents = showOkEvents,
        hideAcknowledgedEvents = hideAcknowledgedEvents,
        showHostName = showHostName
    )

    override fun toJson() = jsonObject(annotation.toJson()) {
        "group" to group
        "host" to host
        "application" to application
        "trigger" to trigger
        "minseverity" to minSeverity.code
        "showOkEvents" to showOkEvents
        "hideAcknowledged" to hideAcknowledgedEvents
        "showHostname" to showHostName
    }

    /**
     * Mutable builder that used to build [ZabbixAnnotation].
     */
    @DashboardElement
    class Builder(
        private val name: String,
        private val enabled: Boolean,
        private val hidden: Boolean,
        private val color: Color
    ) {

        var group: String = "/.*/"

        var host: String = "/.*/"

        var application: String = "/.*/"

        var trigger: String = "/.*/"

        var minSeverity: ZabbixTriggerSeverity = ZabbixTriggerSeverity.NOT_CLASSIFIED

        var showOkEvents: Boolean = false

        var hideAcknowledgedEvents: Boolean = false

        var showHostName: Boolean = false

        fun asAnnotation(): Annotation = ZabbixAnnotation(
            name = name,
            enabled = enabled,
            hidden = hidden,
            color = color,
            group = group,
            host = host,
            application = application,
            trigger = trigger,
            minSeverity = minSeverity,
            showOkEvents = showOkEvents,
            hideAcknowledgedEvents = hideAcknowledgedEvents,
            showHostName = showHostName
        )
    }
}

/**
 * Creates a [ZabbixAnnotation] with given [AnnotationBuilder] context.
 *
 * Note that all basic information will be taken from [AnnotationBuilder] so you need fill [AnnotationBuilder] with
 * relevant information before call this function.
 */
fun AnnotationBuilder.zabbix(build: ZabbixAnnotation.Builder.() -> Unit): Annotation {
    val builder = ZabbixAnnotation.Builder(
        name = name,
        enabled = enabled,
        hidden = hidden,
        color = color
    )
    builder.build()
    return builder.asAnnotation()
}
