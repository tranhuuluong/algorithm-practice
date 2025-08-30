import kotlin.math.max

/**
 * 53. Maximum Subarray
 * Medium
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4, -1, 2, 1] has the largest sum 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5, 4, -1, 7, 8] has the largest sum 23.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * @param nums the input integer array
 * @return the largest sum of any contiguous subarray (at least one element)
 */
private fun maxSubArray(nums: IntArray): Int {
    var currentMax = nums[0]
    var result = nums[0]
    for (i in 1..nums.lastIndex) {
        currentMax = max(nums[i], nums[i] + currentMax)
        result = max(result, currentMax)
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4), 6),
        Pair(intArrayOf(1), 1),
        Pair(intArrayOf(5, 4, -1, 7, 8), 23),
        Pair(intArrayOf(-1, -2, -3), -1),
        Pair(intArrayOf(0, 0, 0), 0),
        Pair(intArrayOf(2, -1, 2, 3, 4, -5), 10),
        Pair(intArrayOf(-2, -1, 0, -3), 0),
        Pair(intArrayOf(10000), 10000),
        Pair(IntArray(100_000) { if (it % 2 == 0) 1 else -1 }, 1),
        Pair(intArrayOf(-10000, 10000, -10000, 10000), 10000)
    )

    validateSolution(testCases, ::maxSubArray)
}
