import kotlin.math.max
import kotlin.math.min

/**
 * 11: Container With Most Water
 * Medium
 * https://leetcode.com/problems/container-with-most-water/
 *
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]). Find two lines that together with the x-axis form a container, such that the container contains the most water. Return the maximum amount of water a container can store. Notice that you may not slant the container.
 *
 * Example 1:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Example 2:
 * Input: height = [1,1]
 * Output: 1
 *
 * Constraints:
 *  - n == height.size
 *  - 2 <= n <= 3 * 10^4
 *  - 0 <= height[i] <= 3 * 10^4
 *
 * @param height the list of non-negative integer heights
 * @return the maximum area of water the container can store
 */
private fun maxArea(height: IntArray): Int {
    var result = 0
    var left = 0
    var right = height.lastIndex
    while (left < right) {
        val w = right - left
        val h = min(height[left], height[right])
        result = max(result, w * h)
        if (height[left] <= height[right]) left++ else right--
    }
    return result
}

private fun maxAreaBruteForce(height: IntArray): Int {
    var result = 0
    for (i in 0..height.lastIndex - 1) {
        for (j in (i + 1)..height.lastIndex) {
            val w = j - i
            val h = min(height[i], height[j])
            result = max(result, w * h)
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7), 49),
        Pair(intArrayOf(1, 1), 1),
        // Additional realistic test cases
        Pair(intArrayOf(4, 3, 2, 1, 4), 16),
        Pair(intArrayOf(1, 2, 1), 2),
        Pair(intArrayOf(2, 1, 2), 4),
        Pair(intArrayOf(1, 3, 2, 5, 25, 24, 5), 24),
        Pair(intArrayOf(2, 3, 10, 5, 7, 8, 9), 36),
        Pair(intArrayOf(3, 9, 3, 4, 7, 2), 21),
        Pair(intArrayOf(1, 2, 4, 3), 4),
        Pair(intArrayOf(1, 100, 1, 1, 100), 300)
    )

    validateSolution(testCases, ::maxArea)
    printDivider()
    validateSolution(testCases, ::maxAreaBruteForce)
}
