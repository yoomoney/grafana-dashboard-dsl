# Grafana Dashboards Kotlin DSL

DSL для генерации Grafana dashboards.

> Авторы:
>
> Василий Созыкин (vsozykin@yamoney.ru)
>
> Дмитрий Павлов (dupavlov@yamoney.ru)
>
> Дмитрий Комаров (komarovdmitry@yamoney.ru)

## Особенности

Проект предоставляет удобный DSL для генерации дэшбордов в виде JSON-документов, 
пригодных для импорта в Grafana. Вот главные особенности:

* Grafana Dashboards as a Code: ревью и версионирование дэшбордов
* Переиспользование дэшбордов, панелей и т.д.
* Единообразная визуализация метрик
* Легкость поддержки метрик в актуальном состоянии
* Прозрачность расширения под большинство существующих возможностей Grafana
* Простое встраивание в CI: на выходе просто JSON-документ
* Возможность использования конструкций Kotlin: циклы, условия и т.д.

## Содержание

1. [Подключение](#подключение)
2. [Импорт JSON](#импорт-json)
3. [Примеры](#примеры)
4. [Разработка](#разработка)

## Подключение

> build.gradle
```groovy
sourceSets {
    grafana {
        kotlin
    }
}

dependencies {
    grafanaCompile 'ru.yandex.money.common:yamoney-grafana-dashboard-dsl:1.0.0'    
}
```
Код для генерации должен располагается в `${projectDir}/src/grafana/kotlin/`. Генерация производится [вручную](#вручную):
```kotlin
import ru.yandex.money.tools.grafana.dsl.dashboard

fun main(args: Array<String>) {
    println(dashboard(title = "My custom dashboard") {
        panels {
            // ...
        }
    })
}
```

## Импорт JSON

![Import](https://bitbucket.yamoney.ru/projects/BACKEND-TOOLS/repos/grafana-dashboard-dsl/raw/import_optimized.gif?at=refs%2Fheads%2Fmaster)

## Примеры
> Простая панель с текстом, использующая дополнительные свойства для кастомизации.
```kotlin
import ru.yandex.money.tools.grafana.dsl.dashboard
import ru.yandex.money.tools.grafana.dsl.json.set
import ru.yandex.money.tools.grafana.dsl.panels.panel
import ru.yandex.money.tools.grafana.dsl.time.off

dashboard(title = "My first Dashboard") {
    refresh = off

    panels {
        panel(title = "My first Panel") {
            // Свойства, которые будут вставлены прямо в JSON-элемент этой панели.
            properties {
                it["mode"] = "markdown"
                it["type"] = "text"
                it["content"] = "# Wow, it's generated!"
            }
        }
    }
}
```

## Разработка

Для разработки дэшбордов, панелей, метрик и т.п. применяется стандартный подход для построения DSL на Kotlin:
* класс для хранения конфигурации новой сущности (обычно имеют название `*Configuration` или `*Builder`), который должен 
  быть помечен аннотацией `ru.yandex.money.tools.grafana.dsl.DashboardElement`
* Data-класс для хранения данных (например, тип графика) новой сущности (примером может служить класс 
  `ru.yandex.money.tools.grafana.dsl.dashboard.Dashboard`), который должен реализовывать интерфейс 
  `ru.yandex.money.tools.grafana.dsl.json.Json`, если предполагается сериализация этой сущности

**NB:** проект представляет собой исключительно DSL. Не рекомендуется разрабатывать в его рамках специфичные панели
и дэшборды.