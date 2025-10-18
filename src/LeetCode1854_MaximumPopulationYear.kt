/**
 * 1854: Maximum Population Year
 * Easy
 * https://leetcode.com/problems/maximum-population-year/
 *
 * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years of the iᵗʰ person.
 * The population of some year x is the number of people alive during that year. The iᵗʰ person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.
 * Return the earliest year with the maximum population.
 *
 * Example:
 * Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
 * Output: 1960
 *
 * Constraints:
 * - 1 <= logs.length <= 100
 * - 1950 <= birthi < deathi <= 2050
 *
 * @param logs Array<IntArray> where each element is [birth, death]
 * @return Int the earliest year with the maximum population
 */
private fun maximumPopulation(logs: Array<IntArray>): Int {
    val arr = IntArray(101)
    for (log in logs) {
        arr[log[0] - 1950]++
        arr[log[1] - 1950]--
    }

    var max = arr[0]
    var result = 0
    for (i in 1..arr.lastIndex) {
        arr[i] += arr[i - 1]
        if (arr[i] > max) {
            result = i
            max = arr[i]
        }
    }
    return result + 1950
}

fun main() {
    val testCases = listOf(
        // Official-style example
        Pair(arrayOf(intArrayOf(1950,1961), intArrayOf(1960,1971), intArrayOf(1970,1981)), 1960),

        // Single person -> earliest birth year
        Pair(arrayOf(intArrayOf(1950,1961)), 1950),

        // Overlap in the middle
        Pair(arrayOf(intArrayOf(2000,2010), intArrayOf(2005,2015)), 2005),

        // Multiple overlapping intervals; earliest year with max population should be 1995
        Pair(arrayOf(intArrayOf(1990,2000), intArrayOf(1995,2005), intArrayOf(2000,2010)), 1995),

        // Full-range person
        Pair(arrayOf(intArrayOf(1950,2050)), 1950),

        // Non-overlapping except endpoints (death year not counted)
        Pair(arrayOf(intArrayOf(1993,1999), intArrayOf(1999,2000)), 1993),

        // Many identical short-living people
        Pair(arrayOf(intArrayOf(1960,1961), intArrayOf(1960,1961), intArrayOf(1960,1961)), 1960),

        // Adjacent intervals, earliest year with max = 2000
        Pair(arrayOf(intArrayOf(2000,2001), intArrayOf(2001,2002)), 2000),

        // Staggered overlaps -> earliest max at 1991
        Pair(arrayOf(intArrayOf(1990,1992), intArrayOf(1991,1993), intArrayOf(1992,1994)), 1991),

        // Three-way overlap at 1959
        Pair(arrayOf(intArrayOf(1950,1960), intArrayOf(1955,1965), intArrayOf(1959,1961)), 1959),

        // Heavier overlap; earliest max at 2000
        Pair(arrayOf(intArrayOf(2000,2002), intArrayOf(2000,2002), intArrayOf(2000,2002), intArrayOf(1999,2001)), 2000),

        // Late-year single person near upper bound
        Pair(arrayOf(intArrayOf(2049,2050), intArrayOf(1950,1951)), 1950)
    )

    validateSolution(testCases, ::maximumPopulation)
}
