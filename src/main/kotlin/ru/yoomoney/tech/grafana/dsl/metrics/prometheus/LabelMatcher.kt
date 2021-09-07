package ru.yoomoney.tech.grafana.dsl.metrics.prometheus

/**
 * Matchers of prometheus lables
 *
 * @author horyukova
 * @since 07.09.2021
 */
class LabelMatcher(
        val equalsLabels: Map<String, String> = emptyMap(),
        val regexMatchLabels: Map<String, String> = emptyMap(),
        val notEqualsLabels: Map<String, String> = emptyMap(),
        val notRegexMatchLabels: Map<String, String> = emptyMap()
) {
    /**
     * Get lables as string
     */
    fun getLabelsAsString(): String {
        val result = mutableListOf<String>()

        result.add(equalsLabels.entries.joinToString(", ") { """${it.key}="${it.value}"""" })
        result.add(regexMatchLabels.entries.joinToString(", ") { """${it.key}=~"${it.value}"""" })

        result.add(notEqualsLabels.entries.joinToString(", ") { """${it.key}!="${it.value}"""" })
        result.add(notRegexMatchLabels.entries.joinToString(", ") { """${it.key}!~"${it.value}"""" })

        return result.filter { it.isNotBlank() }.joinToString(", ")
    }
}