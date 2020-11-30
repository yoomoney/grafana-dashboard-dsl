package ru.yoomoney.tech.grafana.dsl.dashboard.link

import ru.yoomoney.tech.grafana.dsl.DashboardElement

/**
 * Mutable builder of basic link information.
 *
 * Used for supplying such basic information to concrete annotation builders (see [LinkToUrl.Builder]).
 */
@DashboardElement
class DashboardLinkBuilder(val title: String)
