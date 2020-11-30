package ru.yoomoney.tech.grafana.dsl.annotations

import ru.yoomoney.tech.grafana.dsl.datasource.Datasource
import ru.yoomoney.tech.grafana.dsl.json.jsonObject
import ru.yoomoney.tech.grafana.dsl.panels.Color

/**
 * A basic information of annotation.
 *
 * It's recommended to use this class as delegate for other concrete annotation (e.g. [ZabbixAnnotation]).
 *
 * @property name the name of annotation that will be displayed on dashboard screen
 * @property enabled display annotation marks on graphs or not. If **true** then annotation marks will be displayed on
 * graphs
 * @property hidden display annotation toggle on dashboard screen or not. If **true** then annotation toggle will be
 * hidden from dashboard screen
 * @property color the color of annotation marks
 * @property datasource the datasource that used for querying data to display annotation marks
 */
class BasicAnnotation(
    private val name: String,
    private val enabled: Boolean,
    private val hidden: Boolean,
    private val color: Color,
    private val datasource: Datasource
) : Annotation {

    override fun toJson() = jsonObject {
        "name" to name
        "enable" to enabled
        "hide" to hidden
        "iconColor" to color.asRgbaString()
        "datasource" to datasource.asDatasourceName()
    }
}
