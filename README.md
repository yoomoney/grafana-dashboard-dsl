[![Build Status](https://travis-ci.org/yoomoney-tech/grafana-dashboard-dsl.svg?branch=master)](https://travis-ci.org/yoomoney-tech/grafana-dashboard-dsl)
[![codecov](https://codecov.io/gh/yoomoney-tech/grafana-dashboard-dsl/branch/master/graph/badge.svg)](https://codecov.io/gh/yoomoney-tech/grafana-dashboard-dsl)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Javadoc](https://img.shields.io/badge/javadoc-latest-blue.svg)](https://yoomoney-tech.github.io/grafana-dashboard-dsl/)
[![Download](https://img.shields.io/badge/Download-latest-green.svg) ](https://search.maven.org/artifact/ru.yoomoney.tech/grafana-dashboard-dsl)

# Grafana Dashboard DSL

Kotlin DSL for generating Grafana dashboards.

## Features

* Grafana Dashboards as a Code: review and vcs control over dashboards
* Reusable dashboards, panels, configs, etc
* Share visualization style across different metrics
* Easy to keep metrics up-to-date
* Easy to extend to most features of Grafana
* Easy to include in CI cycle: dashboard is a JSON-document
* Power of Kotlin language

## Usage with gradle plugin

See documentation at [Grafana Dashboard Plugin](https://github.com/yoomoney-tech/grafana-dashboard-plugin)

## Manual usage

> build.gradle

```groovy
sourceSets {
    grafana {
        kotlin
    }
}

dependencies {
    grafanaCompile 'ru.yoomoney.tech:grafana-dashboard-dsl:3.0.0'    
}
```

Code for dashboards generation must be placed in `${projectDir}/src/grafana/kotlin/`. Generation performed manually:

```kotlin
import ru.yoomoney.tech. grafana.dsl.dashboard

fun main(args: Array<String>) {
    println(dashboard(title = "My custom dashboard") {
        panels {
            // ...
        }
    })
}
```

## Examples

Examples are in `./src/examples/kotlin/ru/yoomoney/tech/grafana/dsl/examples`

## Development

To create new dashboards, panels, metrics, and others, create a class (usually with postfix `*Configuration` or`*Builder`),
mark it with annotation `ru.yoomoney.tech.grafana.dsl.DashboardElement`, and create data-class for it's contents,
that's implements `ru.yoomoney.tech.grafana.dsl.json.Json`

For example see `DashboardBuilder` and `Dashboard` classes

# How to contribute?

Just fork the repo and send us a pull request.

Make sure your branch builds without any warnings/issues.
