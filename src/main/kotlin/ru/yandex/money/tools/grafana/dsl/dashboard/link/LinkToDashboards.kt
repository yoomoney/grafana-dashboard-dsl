package ru.yandex.money.tools.grafana.dsl.dashboard.link

import ru.yandex.money.tools.grafana.dsl.DashboardElement
import ru.yandex.money.tools.grafana.dsl.dashboard.Tags
import ru.yandex.money.tools.grafana.dsl.dashboard.link.DashboardLinkType.DASHBOARDS
import ru.yandex.money.tools.grafana.dsl.json.jsonObject

/**
 * Dashboard link representation that points to other dashboards, which are found by their [tags]
 *
 * @property title display title
 * @property tags tags are used for searching dashboards
 * @property showAsDropdown if true all links will be collapsed into a dropdown list
 * @property includeVariableValues if true dashboard variables will be propagated to linked dashboard
 * @property keepTimeRange if true linked dashboard will be opened with the same time range
 * @property openInNewTab if true links will be opened in new tab
 */
class LinkToDashboards private constructor(
    private val title: String,
    private val tags: Tags,
    private val showAsDropdown: Boolean,
    private val includeVariableValues: Boolean,
    private val keepTimeRange: Boolean,
    private val openInNewTab: Boolean
) : DashboardLink {

    override fun toJson() = jsonObject {
        "type" to DASHBOARDS.value
        "title" to title
        "tags" to tags
        "asDropdown" to showAsDropdown
        "includeVars" to includeVariableValues
        "keepTime" to keepTimeRange
        "targetBlank" to openInNewTab
    }

    /**
     * Mutable builder that used to build [LinkToDashboards].
     */
    @DashboardElement
    class Builder(private val title: String, private val tags: List<String>) {
        var showAsDropdown = false
        var includeVariableValues = false
        var keepTimeRange = false
        var openInNewTab = false

        fun build() = LinkToDashboards(
                title = title,
                tags = Tags(tags),
                showAsDropdown = showAsDropdown,
                includeVariableValues = includeVariableValues,
                keepTimeRange = keepTimeRange,
                openInNewTab = openInNewTab
        )
    }
}

/**
 * Creates a [LinkToDashboards] with given [DashboardLinkBuilder] context and [tags].
 *
 * Note that all basic information will be taken from [DashboardLinkBuilder]
 * so you need fill [DashboardLinkBuilder] with relevant information before call this function.
 */
fun DashboardLinkBuilder.dashboardsWithTags(
    vararg tags: String,
    init: LinkToDashboards.Builder.() -> Unit = {}
): DashboardLink {
    return LinkToDashboards.Builder(this.title, tags.toList()).apply(init).build()
}
