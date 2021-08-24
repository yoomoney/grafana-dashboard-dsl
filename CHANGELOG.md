### NEXT_VERSION_TYPE=MINOR
### NEXT_VERSION_DESCRIPTION_BEGIN
* `instant` parameter was added for promQl metric
### NEXT_VERSION_DESCRIPTION_END
## [3.3.0]() (24-08-2021)

* Datasource `Datasources.PromQl` was added
* Panel with type `stat` was added

## [3.2.3](https://github.com/yoomoney-tech/grafana-dashboard-dsl/pull/11) (24-05-2021)

* Change version library-project-plugin 7.1.2 -> 7.+

## [3.2.2](https://github.com/yoomoney-tech/grafana-dashboard-dsl/pull/10) (20-05-2021)

* Сборка проекта переведена на library-project-plugin.

## [3.2.1](https://github.com/yoomoney-tech/grafana-dashboard-dsl/pull/6) (12-03-2021)

* Fix tests

## [3.2.0](https://github.com/yoomoney-tech/grafana-dashboard-dsl/pull/5) (12-03-2021)

*  Add the ability to configure `fields` parameter in class `StatPanelReduceOptions` used to set select the fields that should be included in the panel

## [3.1.0](https://github.com/yoomoney-tech/grafana-dashboard-dsl/pull/2) (01-03-2021)

* Add `for` alert property
* Add `No data` and `Keep last state` alerting states

## [3.0.0]() (01-03-2021)

* Move changes from private version
* Build and publication via artifact-release-plugin
* **breaking-changes** everything has been moved from package `ru.yandex.money.tools` to `ru.yoomoney.tech`

## [2.25.0]() (18-02-2021)

* Change yamoney-kotlin-module-plugin to ru.yoomoney.gradle.plugins.kotlin-plugin

## [2.24.0]() (08-12-2020)

* Add `removeAbovePercentile` graphite function implementation
* Add `max` alert condition

## [2.23.0]() (01-12-2020)

* Add `movingMax` graphite function implementation

## [2.22.0]() (11-11-2020)

* Added new Stat panel. Single stat now deprecated

## [2.21.0]() (22-07-2020)

* `Zabbix` datasource metric with `Text` query mode now support parameter `textFilter`.

## [2.20.0]() (08-07-2020)

* Update gradle version 6.0.1 -> 6.4.1.

## [2.19.0]() (24-04-2020)

* Add `keepLastValue` graphite function implementation

## [2.18.1]() (27-02-2020)

* Don't add bibucket pull request link into changelog.md on release

## [2.18.0]() (07-02-2020)

* Added java 11 support

## [2.17.0]() (03-02-2020)

* Update gradle version 4.10.2 -> 6.0.1

## [2.16.0]() (24-12-2019)

* Add the ability to configure dashboard annotation with Graphite datasource. See `AnnotationDemo.kts`

## [2.15.0]() (17-12-2019)

* Add an `alignTo` attribute to the `summarize` function
* Add graphite function `Group`. Takes an arbitrary number of seriesLists and adds them to a single seriesList.
* Add `TablePanel` class and builder.
The table panel is very flexible, supporting both multiple modes for time series as well as for table,
annotation and raw JSON data.
It also provides date formatting and value formatting and coloring options.
See usage examples in `GrafanaDemoLayouts.kts`

## [2.14.0]() (12-12-2019)

* Update repeating panels. Grafana 6.0 and newer `minSpan` replaced by property `maxPerRow`.
Set `maxPerRow` to tell grafana how many panels per row you want at most.

## [2.13.1]() (04-12-2019)

* Replace static code analyzer from `spotbugs` to `detekt`

## [2.13.0]() (04-12-2019)

* Add graphite functions: `AliasByMetric`, `Derivative`, `NonNegativeDerivative`
* Add `fillGradient` field to `GraphPanelBuilder`
* Make `referenceId` param optional in `MetricsBuilder#metric` by auto generation (sequence 'A'..'Z')

## [2.12.0]() (03-12-2019)

* Added `current` field of type `CurrentVariableValue` in class `QueryVariable` used to set default value for this variable type

## [2.11.0]() (27-11-2019)

* Add `BYTES_PER_SECOND` Unit for YAxis
* Add the ability to group the values of query variable into selectable tags
```kotlin
val variable by builder.query(datasource = Zabbix, query = "App version") {
tags = VariableTags("*.*", "*.\$tag")
}
```

## [2.10.0]() (22-11-2019)

* Add `MICROSECONDS` Unit for YAxis

## [2.9.0]() (21-10-2019)

* [Grafana dashboard links](https://grafana.com/docs/guides/whats-new-in-v2-1/#dashboard-links-and-navigation) are now supported.

There are two types of links:
- `LinkToUrl` a link implementation that points to an arbitrary resource.
- `LinkToDashboards` a link implementation that dynamically forms links to other dashboards by their tags

See usage examples in `LinksDemo.kts`

## [2.8.0]() (17-10-2019)

*  Some changes in YooMoney internal build cycle. No new functionality was added.

## [2.7.1]() (10-10-2019)

* The validation for Dashboard#uid field has been added.
It can contain no more than 40 characters now, according to the Grafana API. Otherwise exception will be thrown.

## [2.7.0]() (09-10-2019)

* Add the ability to make the dashboard editable

Dashboards will be created editable by default
```kotlin
dashboard(title = "Editable dashboard") {
editable = true
panels {
panel(title = "Test Panel") {
properties {
it["type"] = "graph"
}
}
}
}
```

## [2.6.0]() (08-10-2019)

* Add the ability to make rows collapsed
```kotlin
row("Collapsed row", collapsed = true) {
panel("Test panel") {}
}
```

## [2.5.1]() (04-10-2019)

* Make the text panel title optional

## [2.5.0]() (03-10-2019)

* Add the ability to change the x-axis mode.

You can select one of the 3 modes: time, series, histogram by specifying the required properties for each of them.
```kotlin
dashboard(title = "My dashboard") {
panels {
graphPanel(title = "Graph with Time X-Axis") {
xAxis = XAxis()
}
graphPanel(title = "Graph Histogram") {
val xAxis = XAxis(mode = Histogram(buckets = 10))
}
graphPanel(title = "Graph Series") {
val xAxis = XAxis(show = false, mode = Series(value = Series.ValueType.COUNT))
}
}
}
```
* Add repeating Graph panels

Template variables can be very useful to dynamically change your queries across a whole dashboard.
If you want Grafana to dynamically create new panels or rows based on what values you have selected you can use the Repeat feature.
If you have a variable with Multi-value or Include all value options enabled you can choose one panel and have Grafana
repeat that panel for every selected value.
```kotlin
dashboard(title = "My dashboard") {
panels {
val graphiteHosts by variables.query(datasource = Graphite, query = "*.*")
graphPanel(title = "Graph by ${graphiteHosts.asVariable()}") {
repeat(graphiteHosts) {
direction = Horizontal(6)
}
metrics {
metric("A") {
"*.another.metric.mean" groupByNodes 0
}
}
}
}
}
```
* Add the panel description, displayed on hover of info icon in the upper left corner of the panel.
```kotlin
dashboard(title = "My dashboard") {
panels {
graphPanel(title = "Graph") {
description = "Panel description"
metrics {
metric("A") {
"*.another.metric.mean" groupByNodes 0
}
}
}
}
}
```

## [2.4.1]() (01-10-2019)

* Fix the generation of the `id` and `position` fields for the panels for each dashboard:
1. Generator now resets an id to 1 for new panels
2. Generator now resets an position to x=0 y=0 for new panels
* Now you can use `override` function in the metrics builder

## [2.4.0]() (30-09-2019)

* Add `TextPanel` class and builder.
text panel lets you make information and description panels etc. for your dashboards.
- `mode` - Here you can choose between markdown, HTML or text.
- `content` - Here you write your content.
```kotlin
dashboard(title = "Dashboard with Text Panel") {
panels {
textPanel("Description") {
mode = ContentMode.MARKDOWN
content = "### Text Panel with MD content"
}
}
}
```
* Update `Legend.kt` class:
- Added new field `sideWidth` Available when _To the right_ is checked. Value to control the minimum width for the legend (default 0)
* Added `ConsolidateBy` graphite function and corresponding `consolidateBy()` extension functions for `Metric`
```kotlin
val metric = "*.*.*.requests.incoming.*.*.process_time.*.count" consolidateBy MAX
```

## [2.3.0]() (26-09-2019)

* `Singlestat` panel are now supported.
The panel can be configured with metrics from `Zabbix` with `Metrics` and `Text` query modes.
Can also be customized `Value Mappings` and`Time Range` tabs and
`Reapeat` function that can apply panel for all values from variable.

## [2.2.0]() (23-09-2019)

* Added ability to set a selected index in a custom variable
* Update `CustomVariable` class:
1. Remove condition for setting selected index in `constructor`
2. Add field `selectedIndex` with default value "0"

## [2.1.0]() (19-09-2019)

* Added possibility to specify names of options in a custom variable.

## [2.0.1]() (12-09-2019)

* Delete deprecated objects:
1. `Interval`
2. `Query`
* Delete deprecated builders:
1. `VariableBuilder`
2. `QueryBuilder`

## [2.0.0]() (11-09-2019)

* Update class `Dashboard`:
1. Add field `uid` to change dashboard url: `https://localhost:8500/d/uid/dashboard-name`
* Refactor class `YAxis`:
1. Field `format` change name to `unit` and type to enum `YAxis.Unit`
2. Change field `decimals` default value from `1` to `null` (`auto` in Grafana)
* Refactor class `GraphPanel`:
1. Change field `decimals` default value from `2` to `null` (`auto` in Grafana)
2. Change field `lineWidth` default value from `2` to `1`
3. Change field `fill` default value from `0` to `1`
4. Change field `nullPointMode` name to `nullValue` and default value from `null as zero` to `null`
5. Delete field `type` and add fields `lines` and `bars`. Set `lines` default value to `true`
6. Change field `stacked` default value to `false`

## [1.11.0]() (10-09-2019)

* New fields at `GraphPanel` class:
1. `staircase` field can be change `Staircase` display parameter on graph panel
2. `decimals` field can be change `Decimals` legend parameter that override automatic decimal precision
* New functions for `metrics`:
1. `sortByTotal` sorts the list of metrics in descending order by the sum of values across the time period specified.
2. `perSecond` represents packets of metric per second if packets are higher.
3. `asPercent` represents the ratio of one metric to another as a percentage.
4. `override` accepts on `alias(...)` function and overrides parameters
* New classes:
1. `HoverTooltip` overrides default Hover tooltip parameters on graph display menu

## [1.10.0]() (13-06-2019)

* Added possibility to specify several nodes in `aliasByNode` function

## [1.9.0]() (15-05-2019)

* Build configuration moved to `yoomoney-kotlin-module-plugin`

## [1.8.1]() (14-05-2019)

* Added repository with Gradle plugins

## [1.8.0]() (17-04-2019)

* [Grafana Annotations](http://docs.grafana.org/reference/annotations/) are now supported. There is `ZabbixAnnotation`
an annotation implementation that uses Zabbix as query datasource. See usage examples in `AnnotationsDemo.kts` example
* `Color` class has changes:
* New method `asRgbaString` that returns string representation of color in format `rgba(red,green,blue,1)`
* New method `asHexString` that returns string representation of color in format `#hex(reg)hex(green)hex(blue)`
* Method `asString` is alias for `asHexString` now
* Companion factory method `of` is alias for new secondary constructor

## [1.7.0]() (10-04-2019)

* Added `GroupByNodes` graphite function and corresponding `groupByNodes()` extension functions for `Metric`

## [1.6.1]() (28-03-2019)

* Fix aggregation in `Highest` graphite function

## [1.6.0]() (22-03-2019)

* Added ability to specify minimum and maximum values for Y axis

## [1.5.0]() (20-03-2019)

* Methods that created variables delegates in `DashboardBuilder` are deprecated now and will be deleted in next major
release. To create variable delegate use `VariablesBuilder` class instead.

The old way to create variable delegate:
```kotlin
dashboard(title = "My dashboard") {
    val myVar by variable {
        interval(1.m, 10.m)
    }
}
```
Since `1.5.0` this code should be like this:
```kotlin
dashboard(title = "My dashboard") {
    val myVar by variables.interval(1.m, 10.m) // variables is an instance of VariablesBuilder class provided by DashboardBuilder
}
```

* Method `Variable.asVariable()` now have default implementation

* `BaseVariable` was reworked:
* `hidden` property was removed, use `hidingMode` property instead. Now, `hidden = true` equals
to `hidingMode = HidingMode.VARIABLE` and `hidden = false` equals to `hidingMode = HidingMode.NONE`
* `query` property was moved to other variable implementations (e.g. `QueryVariable`)
* `includeAll` property was moved to other variable implementations (e.g. `CustomVariable`)
* Added optional `displayName` property for Grafana variables labels

To support backward compatibility with `1.4.0` there is new companion object's method `invoke` that supports old-style
format of `BaseVariable`. This method is marked as *deprecated* and will be deleted in next major release.

* `Query` class marked as *deprecated* and will be deleted in next major release. Use `QueryVariable` instead

* `Interval` class marked as *deprecated* and will be deleted in next major release. Use `IntervalVariable` instead

* More types of variable added:
* `const` variable with fixed value
* `custom` variable with array of fixed values
* `textbox` variable with ability to edit variable value on the fly

* `null`s are not supported for `Datasource.asDatasourceName()` any more. Since `1.5.0`, this method should return
non-null values only. `NullDatasource` marked as deprecated and will be deleted in next major release

* New graphite function was added: `highest(metric, n, aggregation)` that can be used to draw N metrics aggregated by
given function. See `Highest` class for details

## [1.4.0]() (19-03-2019)

* Improve CHANGELOG.md next version description format

## [1.3.0]() (14-03-2019)
* Now it's possible to change decimal value for Y-axis. You can specify precision of values by Y-axis correspond with
  your requirements.
```kotlin
  dashboard(title = "My dashboard") {
    panels {
      graphPanel(title = "MyPanel") {
        leftYAxis = YAxis(decimals = 3)
      }
    }
  }
```
* Support for repeatable rows has been added. It's possible to use repeatable row with set of panels, based on variable.
```kotlin
  dashboard(title = "My dashboard") {
    val values by variable(datasource = Zabbix) {
      query("My variable") {
        regex = ".*"
      }
    }
    panels {
      row("My Row", repeatFor = values) {
      }
    }
  }
```

## [1.2.1]() (29-01-2019)

* Translate README, CHANGELOG and comments to english

## [1.2.0]() (10-01-2019)

* Block `Legend` for `GraphPanel` can now be customised. There are two default implementations:
     * `DEFAULT` - shows `min`, `max`, `avg`, `current`, `total` for each metric
     * `EMPTY` -  shows only names/aliases for metrics
* `GraphPanel` can now be configured to show `points` with the specified `pointradius`
* Can now configure null point mode in `GraphPanel` via `NullPointMode`
* Fill ratio can now be configured for `fill` graphic in `GraphPanel`
* Added some common colors for Grafana to `Color`
* Block `aliasColors` can now be configured for `GraphPanel`
* Parameter `averageSeries` can now be specified in `Metric`
* `Dashboard` can now accept time interval from start of a day - `now/d`

## [1.1.1]() (05-12-2018)

* Fix link to examples in build-script

## [1.1.0]() (04-12-2018)

* Examples of DSL usage
* Function `movingMedian` can now accept `Variable`

## [1.0.5]() (29-11-2018)

* Improved README.md
* Publish javadoc

## [1.0.2]() (26-11-2018)

* Fix public build

## [1.0.1]() (26-11-2018)

* Gradle build for github

## [1.0.0]() (22-11-2018)

* First release