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

### С использованием Gradle-plugin (рекомендуется)
> build.gradle
```groovy
buildscript {
    dependencies {
        classpath 'ru.yandex.money.gradle.plugins:yamoney-grafana-plugin:1.0.5'
    }
}
apply plugin: 'grafana'
```

### Без использования Gradle-plugin
> build.gradle
```groovy
sourceSets {
    grafana {
        kotlin
    }
}

dependencies {
    grafanaCompile 'ru.yandex.money.common:yamoney-grafana-kotlin-dsl:0.1.0'    
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

### С использованием Jenkins
Для каждой master-ветки существует Jenkins job `importGrafanaDashboards`, которая 
в обязательном порядке требует использование Gradle-plugin. Данная задача автоматически
запустит скрипты генерации JSON-документов и произведет их импорт (с перезаписыванием 
существующих дэшбордов).

### Вручную
![Import](https://bitbucket.yamoney.ru/projects/BACKEND-TOOLS/repos/grafana-kotlin-dsl/raw/import_optimized.gif?at=refs%2Fheads%2Fmaster)

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

> Дэшборд Common-Info с использованием строк и переменных.
```kotlin
import ru.yandex.money.tools.grafana.dsl.datasource.Zabbix
import ru.yandex.money.tools.grafana.dsl.kit.Component
import ru.yandex.money.tools.grafana.dsl.kit.INCOMING_REQUESTS
import ru.yandex.money.tools.grafana.dsl.kit.OUTGOING_REQUESTS
import ru.yandex.money.tools.grafana.dsl.kit.PORTAL_BACK_OTHER
import ru.yandex.money.tools.grafana.dsl.kit.QUEUE
import ru.yandex.money.tools.grafana.dsl.kit.commonInfo
import ru.yandex.money.tools.grafana.dsl.time.h
import ru.yandex.money.tools.grafana.dsl.time.m
import ru.yandex.money.tools.grafana.dsl.time.now

dashboard(title = "Catalog common info (autogen)") {
        timeRange = now-2.h .. now

        val hosts by variable(datasource = Zabbix) {
            query(PORTAL_BACK_OTHER) {
                regex = ".*catalog.*"
            }
        }

        val autoInterval by variable {
            interval(1.m, 10.m, 30.m, 1.h)
        }

        tags += "catalog"

        commonInfo(hosts, autoInterval, Component("catalog", "Catalog")) {
            notifications {
                id { 181 }
            }

            thresholds {
                INCOMING_REQUESTS { 20 }
                OUTGOING_REQUESTS { 20 }
                QUEUE { 15 }
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
  `ru.yandex.money.tools.grafana.dsl.json.Json`, если предполагается сериализация это сущности
  
Если планируется разработка переиспользуемого дэшборда (например, `common-info`) или панели, которая является 
специфичной для наших метрик и инфраструктуры, располагайте классы вместе с их зависимостями в пакете 
`ru.yandex.money.tools.grafana.dsl.kit`, который в будущем может быть вынесен в отдельную библиотеку.