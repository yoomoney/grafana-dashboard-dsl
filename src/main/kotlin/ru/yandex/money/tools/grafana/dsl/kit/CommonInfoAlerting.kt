package ru.yandex.money.tools.grafana.dsl.kit

import ru.yandex.money.tools.grafana.dsl.DashboardElement

@DashboardElement
class CommonInfoAlertingBuilder {

    internal val notificationIds = mutableListOf<Long>()

    internal val thresholdValues = mutableMapOf<String, Int>()

    fun notifications(build: CommonInfoNotificationsBuilder.() -> Unit) {
        val builder = CommonInfoNotificationsBuilder()
        builder.build()
        notificationIds += builder.notificationIds
    }

    fun thresholds(build: CommonInfoThresholdsBuilder.() -> Unit) {
        val builder = CommonInfoThresholdsBuilder()
        builder.build()
        thresholdValues += builder.thresholdValues
    }
}

@DashboardElement
class CommonInfoNotificationsBuilder {

    internal val notificationIds = mutableListOf<Long>()

    fun id(fn: () -> Long) {
        notificationIds += fn()
    }
}

const val INCOMING_REQUESTS = "incomingRequests"
const val OUTGOING_REQUESTS = "outgoingRequests"
const val QUEUE = "queue"

@DashboardElement
class CommonInfoThresholdsBuilder {

    internal val thresholdValues = mutableMapOf<String, Int>()

    operator fun String.invoke(fn: () -> Int) {
        thresholdValues[this] = fn()
    }
}
