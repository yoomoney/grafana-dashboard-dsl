package ru.yandex.money.tools.grafana.dsl.dashboard.link

import ru.yandex.money.tools.grafana.dsl.DashboardElement

/**
 * Mutable builder of basic link information.
 *
 * Used for supplying such basic information to concrete annotation builders (see [LinkToUrl.Builder]).
 */
@DashboardElement
class DashboardLinkBuilder(val title: String)
