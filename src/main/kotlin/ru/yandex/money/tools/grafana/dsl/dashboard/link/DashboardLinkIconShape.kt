package ru.yandex.money.tools.grafana.dsl.dashboard.link

/**
 * Type of dashboard's link icon
 */
enum class DashboardLinkIconShape(val value: String) {

    /** Icon indicates external link */
    EXTERNAL_LINK("external link"),

    /** Icon indicates link to dashboard */
    DASHBOARD("dashboard"),

    /** Question mark icon */
    QUESTION("question"),

    /** info icon, like 🛈 */
    INFO("info"),

    /** bolt icon ,like 🗲 */
    BOLT("bolt"),

    /** doc icon, like 🗎 */
    DOC("doc"),

    /** cloud icon, like ☁ */
    CLOUD("cloud")
}
