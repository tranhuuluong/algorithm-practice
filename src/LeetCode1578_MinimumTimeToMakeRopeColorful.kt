import kotlin.math.max
import kotlin.math.min

/**
 * 1578: Minimum Time to Make Rope Colorful
 * Medium
 * https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
 *
 * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i]
 * is the color of the i-th balloon.
 *
 * Alice wants the rope to be colorful: she does not want two consecutive balloons to be of the same color.
 * So she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a
 * 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove
 * the i-th balloon from the rope.
 *
 * Return the minimum time Bob needs to make the rope colorful.
 *
 * Example 1:
 * Input: colors = "abaac", neededTime = [1,2,3,4,5]
 * Output: 3
 * Explanation: Remove the balloon at index 2 (time=3). Then no two consecutive balloons share the same color.
 *
 * Example 2:
 * Input: colors = "abc", neededTime = [1,2,3]
 * Output: 0
 * Explanation: The rope is already colorful.
 *
 * Example 3:
 * Input: colors = "aabaa", neededTime = [1,2,3,4,1]
 * Output: 2
 * Explanation: Remove the balloons at indices 0 and 4 (each time=1) for a total cost of 2.
 *
 * Constraints:
 *  • n == colors.length == neededTime.length
 *  • 1 <= n <= 10^5
 *  • 1 <= neededTime[i] <= 10^4
 *  • colors consists only of lowercase English letters.
 *
 * @param colors the string of balloon colors
 * @param neededTime the array of times required to remove each balloon
 * @return the minimum total time to remove balloons such that no two adjacent balloons share the same color
 */
private fun minCost(colors: String, neededTime: IntArray): Int {
    var result = 0
    for (i in 1..colors.lastIndex) {
        if (colors[i] == colors[i - 1]) {
            result += min(neededTime[i], neededTime[i - 1])
            neededTime[i] = max(neededTime[i], neededTime[i - 1])
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Triple("abaac", intArrayOf(1,2,3,4,5), 3),     // Example 1
        Triple("abc", intArrayOf(1,2,3), 0),          // Example 2
        Triple("aabaa", intArrayOf(1,2,3,4,1), 2),    // Example 3
        Triple("aa", intArrayOf(5,4), 4),
        Triple("bbba", intArrayOf(3,5,10,7), 8),
        Triple("cddde", intArrayOf(1,2,3,4,5), 5),
        Triple("aabbcc", intArrayOf(1,1,1,1,1,1), 3),
        Triple("aaaa", intArrayOf(1,2,3,4), 6),
        Triple("abccc", intArrayOf(2,2,2,2,2), 4),
        Triple("aba", intArrayOf(1,100,1), 0)
    )

    validateSolution(testCases, ::minCost)
}
