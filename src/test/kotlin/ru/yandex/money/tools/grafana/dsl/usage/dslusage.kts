import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.h
import ru.yandex.money.tools.grafana.dsl.minus
import ru.yandex.money.tools.grafana.dsl.now
import ru.yandex.money.tools.grafana.dsl.rangeTo

dashboard {
    title = "Title"
    time = now-6.h..now

    panels {

    }
}
