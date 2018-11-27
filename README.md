[![Build Status](https://travis-ci.org/yandex-money-tech/grafana-dashboard-dsl.svg?branch=master)](https://travis-ci.org/yandex-money-tech/grafana-dashboard-dsl)
[![Build status](https://ci.appveyor.com/api/projects/status/rm9w6w0jt994vyys?svg=true)](https://ci.appveyor.com/project/f0y/grafana-dashboard-dsl)
[![codebeat badge](https://codebeat.co/badges/8bb6412b-cef6-4808-962e-9f9bfa5a13ec)](https://codebeat.co/projects/github-com-yandex-money-tech-grafana-dashboard-dsl-master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Javadoc](https://img.shields.io/badge/javadoc-latest-blue.svg)](https://yandex-money-tech.github.io/grafana-dashboard-dsl/)
[![Download](https://api.bintray.com/packages/yandex-money-tech/maven/grafana-dashboard-dsl/images/download.svg) ](https://bintray.com/yandex-money-tech/maven/grafana-dashboard-dsl/_latestVersion)

# Grafana Dashboard DSL

DSL на kotlin для генерации Grafana dashboards.

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

Библиотека доступна в [Bintray's JCenter repository](http://jcenter.bintray.com) 

```
<dependency>
  <groupId>com.yandex.money.tech</groupId>
  <artifactId>grafana-dashboard-dsl</artifactId>
  <version>1.0.3</version>
</dependency>
```

> build.gradle
```groovy
sourceSets {
    grafana {
        kotlin
    }
}

dependencies {
    grafanaCompile 'com.yandex.money.tech:grafana-dashboard-dsl:1.0.3'    
}
```
Код для генерации должен располагаться в `${projectDir}/src/grafana/kotlin/`. Генерация производится [вручную](#вручную):
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

![Import](https://raw.githubusercontent.com/yandex-money-tech/grafana-dashboard-dsl/master/import_optimized.gif)

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

# Сборка проекта

См. конфигурации Travis (`.travis.yml`) или AppVeyor (`appveyor.yml`).
В репозитории находятся два gradle-проекта:
- файлы `build.gradle`, `gradlew`, `gradle/wrapper` относятся к проекту для работы во внутренней инфраструктуре Яндекс.Денег;
- файлы `build-public.gradle`, `gradlew-public`, `gradle-public/wrapper` относятся к проекту для работы извне.

# Импорт проекта в IDE

К сожалению на данный момент необходимо перед импортом проекта в Idea заменить файлы:
- `gradle-public/wrapper/gradle-wrapper.properties` на `gradle/wrapper/gradle-wrapper.properties`,
- `build-public.gradle` with `build.gradle`.
Это вызвано багом в Idea: https://github.com/f0y/idea-two-gradle-builds.

# Авторы

> Дмитрий Комаров (komarovdmitry@yamoney.ru)
>
> Василий Созыкин (vsozykin@yamoney.ru)
>
> Дмитрий Павлов (dupavlov@yamoney.ru)
