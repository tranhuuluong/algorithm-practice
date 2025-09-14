/**
 * 69: Sqrt(x)
 * Easy
 * https://leetcode.com/problems/sqrtx/
 *
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
 *
 * Example 1:
 * Input: x = 4
 * Output: 2
 *
 * Example 2:
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we round down, return 2.
 *
 * Constraints:
 * - 0 <= x <= 2^31 - 1
 *
 * @param x the non-negative integer whose square root (floored) is to be computed
 * @return Int the largest integer r such that r * r ≤ x
 */
private fun mySqrt(x: Int): Int {
    var l = 0
    var r = x
    var result = -1
    while (l <= r) {
        val m = l + (r - l) / 2
        val p = m.toLong() * m
        when {
            p > x -> r = m - 1
            p < x -> {
                l = m + 1
                result = m
            }
            else -> return m
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(4, 2),
        Pair(8, 2),
        // Additional cases
        Pair(0, 0),
        Pair(1, 1),
        Pair(2, 1),
        Pair(3, 1),
        Pair(15, 3),
        Pair(16, 4),
        Pair(17, 4),
        Pair(Int.MAX_VALUE, 46340),  // since 46340^2 = 2147395600 ≤ INT_MAX < 46341^2
        Pair(2147483646, 46340),
        Pair(1000000, 1000)
    )

    validateSolution(testCases, ::mySqrt)
}
