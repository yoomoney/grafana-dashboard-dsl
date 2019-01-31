[![Build Status](https://travis-ci.org/yandex-money-tech/grafana-dashboard-dsl.svg?branch=master)](https://travis-ci.org/yandex-money-tech/grafana-dashboard-dsl)
[![Build status](https://ci.appveyor.com/api/projects/status/rm9w6w0jt994vyys?svg=true)](https://ci.appveyor.com/project/f0y/grafana-dashboard-dsl)
[![codecov](https://codecov.io/gh/yandex-money-tech/grafana-dashboard-dsl/branch/master/graph/badge.svg)](https://codecov.io/gh/yandex-money-tech/grafana-dashboard-dsl)
[![codebeat badge](https://codebeat.co/badges/8bb6412b-cef6-4808-962e-9f9bfa5a13ec)](https://codebeat.co/projects/github-com-yandex-money-tech-grafana-dashboard-dsl-master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Javadoc](https://img.shields.io/badge/javadoc-latest-blue.svg)](https://yandex-money-tech.github.io/grafana-dashboard-dsl/)
[![Download](https://api.bintray.com/packages/yandex-money-tech/maven/grafana-dashboard-dsl/images/download.svg) ](https://bintray.com/yandex-money-tech/maven/grafana-dashboard-dsl/_latestVersion)

# Grafana Dashboard DSL

Kotlin DSL for generating Grafana dashboards.

## Features

* Grafana Dashboards as a Code: review and vcs control over dashboards
* Reuse dashboards, panels, configs, etc
* Uniform metric visualisation
* Easy to keep metrics up-to-date
* Easy to extend to most features of Grafana
* Easy to include in CI cycle: dashboard is a JSON-document
* Power of Kotlin language

## Usage with gradle plugin

See documentation at [Grafana Dashboard Plugin](https://github.com/yandex-money-tech/grafana-dashboard-plugin)

## Manual usage

> build.gradle

```groovy
sourceSets {
    grafana {
        kotlin
    }
}

dependencies {
    grafanaCompile 'com.yandex.money.tech:grafana-dashboard-dsl:1.0.5'    
}
```

Code for dashboards generation must be in `${projectDir}/src/grafana/kotlin/`. Generation performed manually:

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

## JSON Import

![Import](https://raw.githubusercontent.com/yandex-money-tech/grafana-dashboard-dsl/master/import_optimized.gif)

## Examples

Examples are in `./src/examples/kotlin/ru/yandex/money/tools/grafana/dsl/examples`

## Development

To create new dashboards, panels, metrics, and others, create a class (usually with postfix `*Configuration` or`*Builder`),
mark it with annotation `ru.yandex.money.tools.grafana.dsl.DashboardElement`, and create data-class for it's contents,
that's implements `ru.yandex.money.tools.grafana.dsl.json.Json`

For example see `DashboardBuilder` and `Dashboard` classes

# How to contribute?

Just fork the repo and send us a pull request.

Make sure your branch builds without any warnings/issues.

# How to build?

See configuration for Travis (`.travis.yml`) or AppVeyor (`appveyor.yml`).
There are two gradle projects in this repository:

* Files `build.gradle`, `gradlew`, `gradle/wrapper` is for internal use in Yandex.Money infrastructure
* Files `build-public.gradle`, `gradlew-public`, `gradle-public/wrapper` are for public use

# Importing into IntelliJ IDEA

Unfortunately, at this moment, intellij does not support this build configuration, so you have to change some files before importing:

* Move `gradle-public/wrapper/gradle-wrapper.properties` into `gradle/wrapper/gradle-wrapper.properties`
* Move `build-public.gradle` into `build.gradle`

Vote for this issue [IDEA-199116](https://youtrack.jetbrains.net/issue/IDEA-199116), to make intellij support these types of configuration.

# Contributors

* Dmitriy Komarov (komarovdmitry@yamoney.ru)
* Vasiliy Sozykin (vsozykin@yamoney.ru)
* Dmitriy Pavlov (dupavlov@yamoney.ru)
