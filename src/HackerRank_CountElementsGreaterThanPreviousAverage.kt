/**
 * HackerRank / TCS Problem: Count Elements Greater Than Previous Average
 *
 * Problem Statement:
 * Given an integer array, count the number of elements that are strictly greater
 * than the average of all elements that precede them in the array.
 *
 * Example:
 * Input: [10, 2, 8, 4, 9]
 * Output: 2
 * Explanation:
 * - Index 0 (10): No previous elements.
 * - Index 1 (2): Prev sum = 10, count = 1, avg = 10.0. 2 is not > 10.0.
 * - Index 2 (8): Prev sum = 12, count = 2, avg = 6.0. 8 > 6.0 -> Count!
 * - Index 3 (4): Prev sum = 20, count = 3, avg = 6.66. 4 is not > 6.66.
 * - Index 4 (9): Prev sum = 24, count = 4, avg = 6.0. 9 > 6.0 -> Count!
 * Total count = 2.
 *
 * @param nums The input array of integers.
 * @return The count of elements greater than their prefix average.
 */
private fun countGreater(nums: IntArray): Int {
    var ans = 0
    var sum = nums[0].toLong()
    for (i in 1 until nums.size) {
        if (nums[i] > sum / i) ans++
        sum += nums[i]
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(10, 2, 8, 4, 9), 2),
        Pair(intArrayOf(1, 2, 3, 4, 5), 4),   // Increasing: always > avg
        Pair(intArrayOf(5, 4, 3, 2, 1), 0),   // Decreasing: never > avg
        Pair(intArrayOf(10, 10, 10, 10), 0),  // Equal: never strictly greater
        Pair(intArrayOf(3, 8, 15, 2), 2)      // 8 > 3.0 (Yes), 15 > 5.5 (Yes), 2 > 8.66 (No)
    )

    validateSolution(testCases, ::countGreater)
}