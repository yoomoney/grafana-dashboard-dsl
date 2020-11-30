package ru.yoomoney.tech.grafana.dsl.variables

/**
 * Sort order of variable values.
 *
 * @author Dmitry Komarov
 * @since 14.03.2019
 */
enum class SortOrder(val code: Int) {

    /**
     * No sorting will be applied to variable values. Order of values determined by query.
     */
    DISABLED(0),

    /**
     * Values will be sorted in alphabetical ascending order.
     *
     * For example, array `["1", "10", "20", "100"]` will be sorted as `["1", "10", "100", "20"]`.
     *
     * Note that this method is case sensitive so array `["ab", "Ab"]` will be sorted as `["Ab", "ab"]`.
     */
    ALPHABETICAL_ASC(1),

    /**
     * Values will be sorted in alphabetical descending order.
     *
     * For example, array `["1", "10", "20", "100"]` will be sorted as `["20", "100", "10", "1"]`.
     *
     * Note that this method is case sensitive so array `["ab", "Ab"]` will be sorted as `["ab", "Ab"]`.
     */
    ALPHABETICAL_DESC(2),

    /**
     * Values will be sorted in numerical ascending order.
     *
     * For example, array `["1", "10", "20", "100"]` will be sorted as `["1", "10", "20", "100"]`.
     */
    NUMERICAL_ASC(3),

    /**
     * Values will be sorted in numerical ascending order.
     *
     * For example, array `["1", "10", "20", "100"]` will be sorted as `["100", "20", "10", "1"]`.
     */
    NUMERICAL_DESC(4),

    /**
     * Values will be sorted in alphabetical ascending order.
     *
     * For example, array `["1", "10", "20", "100"]` will be sorted as `["1", "10", "100", "20"]`.
     *
     * Note that this method is case **insensitive** so array `["ab", "Ab"]` will be sorted as `["ab", "Ab"]`.
     */
    ALPHABETICAL_CASE_INSENSITIVE_ASC(5),

    /**
     * Values will be sorted in alphabetical descending order.
     *
     * For example, array `["1", "10", "20", "100"]` will be sorted as `["20", "100", "10", "1"]`.
     *
     * Note that this method is case **insensitive** so array `["ab", "Ab"]` will be sorted as `["ab", "Ab"]`.
     */
    ALPHABETICAL_CASE_INSENSITIVE_DESC(6)
}
