import kotlin.math.max

/**
 * 2078: Two Furthest Houses With Different Colors
 * Easy
 * https://leetcode.com/problems/two-furthest-houses-with-different-colors/
 *
 * There are n houses built in a line. You are given a 0-indexed integer array colors of length n,
 * where colors[i] represents the color of the i-th house.
 *
 * Return the maximum distance between two houses with different colors.
 * The distance between the i-th and j-th houses is abs(i - j).
 *
 * Example 1:
 * Input: colors = [1,1,1,6,1,1,1]
 * Output: 3
 * Explanation: The furthest two houses with different colors are house 0 and house 3.
 * House 0 has color 1, and house 3 has color 6. The distance between them is abs(0 - 3) = 3.
 *
 * Example 2:
 * Input: colors = [1,8,3,8,3]
 * Output: 4
 * Explanation: The furthest two houses with different colors are house 0 and house 4.
 * House 0 has color 1, and house 4 has color 3. The distance between them is abs(0 - 4) = 4.
 *
 * Example 3:
 * Input: colors = [0,1]
 * Output: 1
 * Explanation: The furthest two houses with different colors are house 0 and house 1.
 * House 0 has color 0, and house 1 has color 1. The distance between them is abs(0 - 1) = 1.
 *
 * Constraints:
 * - n == colors.length
 * - 2 <= n <= 100
 * - 0 <= colors[i] <= 100
 * - Test data are generated such that at least two houses have different colors.
 *
 * @param colors array where colors[i] is the color of the i-th house
 * @return Int the maximum distance between two houses with different colors
 */
private fun maxDistance(colors: IntArray): Int {
    val lastIndex = colors.lastIndex
    var l = 0
    var r = lastIndex
    while (l <= lastIndex && colors[l] == colors[lastIndex]) l++
    while (r >= 0 && colors[0] == colors[r]) r--
    return max(lastIndex - l, r)
}

fun main() {
    // testCases: Pair(colors, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1,1,1,6,1,1,1), 3),
        Pair(intArrayOf(1,8,3,8,3), 4),
        Pair(intArrayOf(0,1), 1),

        // Additional cases (>= 10 total)
        Pair(intArrayOf(1,2), 1),
        Pair(intArrayOf(1,1,2), 2),
        Pair(intArrayOf(1,1,1,1,2), 4),
        Pair(intArrayOf(2,2,2,2,2,3), 5),
        Pair(intArrayOf(1,2,3,4,5), 4),
        Pair(intArrayOf(7,7,7,8,7,7,9), 6),
        Pair(intArrayOf(4,4,5,4,4), 2),
        Pair(intArrayOf(3,1,3,3,3,1,2), 6)
    )

    validateSolution(testCases, ::maxDistance)
}
