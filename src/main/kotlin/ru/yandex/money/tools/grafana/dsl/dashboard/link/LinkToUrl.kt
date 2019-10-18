package ru.yandex.money.tools.grafana.dsl.dashboard.link

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.dashboard.link.DashboardLinkIconShape.EXTERNAL_LINK
import ru.yandex.money.tools.grafana.dsl.dashboard.link.DashboardLinkType.URI_LINK
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Dashboard link representation to a resource available by [targetUrl]
 *
 * @property title display title
 * @property targetUrl url that link points to
 * @property icon type of an icon that will represent a link
 * @property tooltip link's tooltip
 * @property includeVariableValues if true dashboard variables will be propagated to linked dashboard
 * @property keepTimeRange if true linked dashboard will be opened with the same time range
 * @property openInNewTab if true links will be opened in new tab
 */
class LinkToUrl private constructor(
    private val title: String,
    private val targetUrl: String,
    private val icon: DashboardLinkIconShape,
    private val tooltip: String,
    private val includeVariableValues: Boolean,
    private val keepTimeRange: Boolean,
    private val openInNewTab: Boolean
) : DashboardLink {

    override fun toJson() = jsonObject {
        "type" to URI_LINK.value
        "title" to title
        "icon" to icon.value
        "tooltip" to tooltip
        "url" to targetUrl
        "includeVars" to includeVariableValues
        "keepTime" to keepTimeRange
        "targetBlank" to openInNewTab
    }

    /**
     * Mutable builder that used to build [LinkToUrl].
     */
    @DashboardElement
    class Builder(
        private val title: String,
        private val targetUrl: String
    ) {
        var icon: DashboardLinkIconShape = EXTERNAL_LINK
        var tooltip: String = ""
        var includeVariableValues: Boolean = false
        var keepTimeRange: Boolean = false
        var openInNewTab: Boolean = false

        fun build() = LinkToUrl(
                title = title,
                targetUrl = targetUrl,
                icon = icon,
                tooltip = tooltip,
                includeVariableValues = includeVariableValues,
                keepTimeRange = keepTimeRange,
                openInNewTab = openInNewTab
        )
    }
}

/**
 * Creates a [LinkToUrl] with given [DashboardLinkBuilder] context.
 *
 * Note that all basic information will be taken from [DashboardLinkBuilder]
 * so you need fill [DashboardLinkBuilder] with relevant information before call this function.
 */
fun DashboardLinkBuilder.url(targetUrl: String, init: LinkToUrl.Builder.() -> Unit = {}): DashboardLink {
    return LinkToUrl.Builder(this.title, targetUrl).apply(init).build()
}
