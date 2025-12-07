/**
 * 1523: Count Odd Numbers in an Interval Range
 * Easy
 * https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/
 *
 * Given two non-negative integers low and high, return the number of odd numbers in the interval
 * [low, high] (inclusive).
 *
 * Example 1:
 * Input: low = 3, high = 7
 * Output: 3
 * Explanation: The odd numbers between 3 and 7 are [3, 5, 7].
 *
 * Example 2:
 * Input: low = 8, high = 10
 * Output: 1
 * Explanation: The odd numbers between 8 and 10 are [9].
 *
 * Constraints:
 * 0 <= low <= high <= 10^9
 *
 * @param low the inclusive start of the interval
 * @param high the inclusive end of the interval
 * @return number of odd integers in the interval
 */
private fun countOddsBruteForce(low: Int, high: Int): Int {
    var count = 0
    for (i in low..high) {
        if (i % 2 != 0) count++
    }
    return count
}

private fun countOddsOptimized(low: Int, high: Int): Int {
    return (high - low + 1) / 2 + if (low % 2 != 0 && high % 2 != 0) 1 else 0
}

fun main() {
    val testCases = listOf(
        Triple(3, 7, 3),
        Triple(8, 10, 1),
        Triple(0, 0, 0),
        Triple(0, 1, 1),
        Triple(1, 1, 1),
        Triple(2, 2, 0),
        Triple(1, 10, 5),
        Triple(10, 20, 5),
        Triple(100, 200, 50),
        Triple(999_999_999, 1_000_000_000, 1)
    )

    validateSolution(testCases, ::countOddsBruteForce)
    printDivider()
    validateSolution(testCases, ::countOddsOptimized)
}
