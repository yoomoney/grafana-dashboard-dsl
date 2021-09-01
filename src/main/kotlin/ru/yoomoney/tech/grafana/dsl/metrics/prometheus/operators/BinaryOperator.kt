package ru.yoomoney.tech.grafana.dsl.metrics.prometheus.operators

import ru.yoomoney.tech.grafana.dsl.metrics.prometheus.InstantVectorTypedMetric

/**
 * Prometheus binary operator
 *
 * [Official documentation](https://prometheus.io/docs/prometheus/latest/querying/operators/#arithmetic-binary-operators)
 *
 * @author Petr Zinin pgzinin@yoomoney.ru
 * @since 27.08.2021
 */
internal class BinaryOperator internal constructor(
    private val leftMetric: InstantVectorTypedMetric,
    private val rightMetric: InstantVectorTypedMetric,
    private val binaryOperationType: BinaryOperationType
) : InstantVectorTypedMetric {
    override fun asString() = "${leftMetric.asString()} ${binaryOperationType.sign} ${rightMetric.asString()}"
}

/**
 * Addition operator (+)
 */
operator fun InstantVectorTypedMetric.plus(right: InstantVectorTypedMetric): InstantVectorTypedMetric {
    return BinaryOperator(this, right, BinaryOperationType.ADDITION)
}

/**
 * Subtraction operator (-)
 */
operator fun InstantVectorTypedMetric.minus(right: InstantVectorTypedMetric): InstantVectorTypedMetric {
    return BinaryOperator(this, right, BinaryOperationType.SUBTRACTION)
}

/**
 * Multiplication operator (*)
 */
operator fun InstantVectorTypedMetric.times(right: InstantVectorTypedMetric): InstantVectorTypedMetric {
    return BinaryOperator(this, right, BinaryOperationType.MULTIPLICATION)
}

/**
 * Division operator (/)
 */
operator fun InstantVectorTypedMetric.div(right: InstantVectorTypedMetric): InstantVectorTypedMetric {
    return BinaryOperator(this, right, BinaryOperationType.DIVISION)
}

/**
 * Modulo operator (%)
 */
operator fun InstantVectorTypedMetric.rem(right: InstantVectorTypedMetric): InstantVectorTypedMetric {
    return BinaryOperator(this, right, BinaryOperationType.MODULO)
}

/**
 * Power/exponentiation operator (^)
 */
fun InstantVectorTypedMetric.pow(right: InstantVectorTypedMetric): InstantVectorTypedMetric {
    return BinaryOperator(this, right, BinaryOperationType.POWER)
}

internal enum class BinaryOperationType(val sign: String) {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    MODULO("%"),
    POWER("^"),
}