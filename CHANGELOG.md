### NEXT_VERSION_TYPE=MAJOR|MINOR|PATCH
### NEXT_VERSION_DESCRIPTION_BEGIN
### NEXT_VERSION_DESCRIPTION_END
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

* Build configuration moved to `yamoney-kotlin-module-plugin`

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