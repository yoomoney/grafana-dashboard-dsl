package ru.yandex.money.tools.grafana.dsl.panels

/**
 * Specify how your data/metric query should be transformed into a table format.
 *
 * @author Aleksandr Korkin (avkorkin@yamoney.ru)
 * @since 12/12/19
 */
enum class TableTransform(val transform: String) {
    // In the most simple mode you can turn time series to rows. This means you get a Time, Metric and a Value column.
    // Where Metric is the name of the time series.
    TIMESERIES_TO_ROWS("timeseries_to_rows"),
    // This transform allows you to take multiple time series and group them by time.
    // Which will result in the primary column being Time and a column for each time series.
    TIMESERIES_TO_COLUMNS("timeseries_to_columns"),
    // This table transformation will lay out your table into rows by metric, allowing columns of
    // Avg, Min, Max, Total, Current and Count.
    // More than one column can be added.
    TIMESERIES_AGGREGATIONS("timeseries_aggregations"),
    // If you have an Elasticsearch Raw Document query or an Elasticsearch query without a date histogram
    // use this transform mode and pick the columns using the Columns section.
    JSON("json"),
    // If you have annotations enabled in the dashboard you can have the table show them.
    // If you configure this mode then any queries you have in the metrics tab will be ignored
    ANNOTATIONS("annotations"),
}
