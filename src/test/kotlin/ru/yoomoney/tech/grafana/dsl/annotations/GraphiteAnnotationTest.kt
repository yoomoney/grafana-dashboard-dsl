package ru.yoomoney.tech.grafana.dsl.annotations

import org.testng.annotations.Test
import ru.yoomoney.tech.grafana.dsl.jsonFile
import ru.yoomoney.tech.grafana.dsl.metrics.functions.aliasByNode
import ru.yoomoney.tech.grafana.dsl.panels.Color
import ru.yoomoney.tech.grafana.dsl.shouldEqualToJson

/**
 * @author Ilya Doroshenko
 * @since 23.12.2019
 */
class GraphiteAnnotationTest {

    @Test
    fun `should create Graphite annotation`() {
        val builder = AnnotationBuilder(name = "test graphite")

        builder.enabled = true
        builder.color = Color.RED

        val annotation = builder.graphite {
            targetQuery = "*.*.some_event".aliasByNode(2, 3)
        }

        annotation.toJson().toString() shouldEqualToJson jsonFile("GraphiteAnnotation.json")
    }
}