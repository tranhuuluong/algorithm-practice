import kotlin.math.max
import kotlin.math.min

/**
 * 42: Trapping Rain Water
 * Hard
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * You are given n non-negative integers representing an elevation map where the width of each bar is 1.
 * Compute how much water it can trap after raining.
 *
 * Example:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 * Constraints:
 * - n == height.length
 * - 1 <= n <= 2 * 10^4
 * - 0 <= height[i] <= 10^5
 *
 * @param height the elevation map represented as an array of non-negative integers
 * @return Int the total amount of water that can be trapped
 */
private fun trap(height: IntArray): Int {
    var maxLeft = height.first()
    var maxRight = height.last()
    var result = 0
    var l = 0
    var r = height.lastIndex
    while (l < r) {
        if (height[l] < height[r]) {
            l++
            maxLeft = max(height[l], maxLeft)
            result += max(0, min(maxLeft, maxRight) - height[l])
        } else {
            r--
            maxRight = max(height[r], maxRight)
            result += max(0, min(maxLeft, maxRight) - height[r])
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official example
        Pair(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1), 6),
        Pair(intArrayOf(1), 0),
        Pair(intArrayOf(2, 0, 2), 2),
        Pair(intArrayOf(3, 0, 1, 0, 2), 5),
        Pair(intArrayOf(3, 0, 2, 0, 4), 7),
        Pair(intArrayOf(4, 2, 0, 3, 2, 5), 9),
        Pair(intArrayOf(1, 2, 3, 4, 5), 0),
        Pair(intArrayOf(5, 4, 1, 2), 1),
        Pair(intArrayOf(2, 1, 0, 1, 3), 4),
        Pair(intArrayOf(0, 0, 0, 0), 0)
    )

    validateSolution(testCases, ::trap)
}
