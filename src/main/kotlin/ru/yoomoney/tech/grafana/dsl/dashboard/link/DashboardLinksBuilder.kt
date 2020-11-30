package ru.yoomoney.tech.grafana.dsl.dashboard.link

import ru.yoomoney.tech.grafana.dsl.DashboardElement

/**
 * Mutable builder for building links collection.
 */
@DashboardElement
class DashboardLinksBuilder {
    internal val links: MutableList<DashboardLink> = mutableListOf()

    /**
     * Adds new link to [links] with given [title].
     */
    fun link(title: String, build: DashboardLinkBuilder.() -> DashboardLink) {
        links += DashboardLinkBuilder(title).build()
    }
}
