import kotlin.math.abs

/**
 * 16: 3Sum Closest
 * Medium
 * https://leetcode.com/problems/3sum-closest/
 *
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example 1:
 * Input: nums = [-1, 2, 1, -4], target = 1
 * Output: 2
 *
 * Example 2:
 * Input: nums = [0, 0, 0], target = 1
 * Output: 0
 *
 * Constraints:
 *  - 3 <= nums.length <= 500
 *  - -1000 <= nums[i] <= 1000
 *  - -10^4 <= target <= 10^4
 *
 * @param nums the input array of integers
 * @param target the target integer to approach
 * @return the sum of three integers closest to the target
 */
private fun threeSumClosest(nums: IntArray, target: Int): Int {
    var result = 100000
    nums.sort()
    for (i in nums.indices) {
        val a = nums[i]
        var left = i + 1
        var right = nums.lastIndex
        while (left < right) {
            val b = nums[left]
            val c = nums[right]
            val sum = a + b + c
            val diff = abs(target - sum)
            result = if (diff < abs(target - result)) sum else result
            when {
                sum < target -> left++
                sum > target -> right--
                else -> return target
            }
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(-1, 2, 1, -4), 1, 2),
        Triple(intArrayOf(0, 0, 0), 1, 0),
        // Additional realistic test cases
        Triple(intArrayOf(1, 1, -1, -1, 3), 1, 1),
        Triple(intArrayOf(1, 2, 5, 10, 11), 12, 13),
        Triple(intArrayOf(-3, -1, 0, 2, 4), 1, 1),
        Triple(intArrayOf(-5, 2, 3, 3, 5), 0, 0),
        Triple(intArrayOf(0, 2, 1, -3), 1, 0),
        Triple(intArrayOf(1, 1, 1, 0), 100, 3),
        Triple(intArrayOf(-2, 3, 2, 4), 0, 3),
        Triple(intArrayOf(4, 0, 5, -5, 3, 3, 0, -4, -5), -2, -2)
    )

    validateSolution(testCases, ::threeSumClosest)
}
