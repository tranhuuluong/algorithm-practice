import kotlin.math.max

/**
 * 2016: Maximum Difference Between Increasing Elements
 * Easy
 * https://leetcode.com/problems/maximum-difference-between-increasing-elements/
 *
 * Given a 0-indexed integer array `nums` of size `n`, find the maximum difference between `nums[i]` and `nums[j]`
 * (i.e., `nums[j] - nums[i]`), such that `0 <= i < j < n` and `nums[i] < nums[j]`.
 * Return the maximum difference. If no such `i` and `j` exists, return −1.
 *
 * Example 1:
 * Input: nums = [7,1,5,4]
 * Output: 4
 * Explanation: The maximum difference occurs with i = 1 and j = 2, nums[j] − nums[i] = 5 − 1 = 4.
 *
 * Example 2:
 * Input: nums = [9,4,3,2]
 * Output: −1
 *
 * Example 3:
 * Input: nums = [1,5,2,10]
 * Output: 9
 *
 * Constraints:
 *   n == nums.length
 *   2 <= n <= 1000 :contentReference[oaicite:0]{index=0}
 *   1 <= nums[i] <= 10⁹ :contentReference[oaicite:1]{index=1}
 *
 * @param nums the input array of integers
 * @return Int the maximum difference satisfying the conditions, or −1 if none exists
 */
private fun maximumDifference(nums: IntArray): Int {
    var result = -1
    var min = nums[0]
    for (i in 1..nums.lastIndex) {
        when {
            nums[i] > min -> result = max(result, nums[i] - min)
            nums[i] < min -> min = nums[i]
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(7,1,5,4), 4),
        Pair(intArrayOf(9,4,3,2), -1),
        Pair(intArrayOf(1,5,2,10), 9),
        // Additional cases
        Pair(intArrayOf(1,2), 1),
        Pair(intArrayOf(2,1), -1),
        Pair(intArrayOf(5,5,5,5), -1),
        Pair(intArrayOf(1,1000,1,1000), 999),
        Pair(intArrayOf(100, 90, 80, 120), 40),
        Pair(intArrayOf(1,2,3,4,5), 4),
        Pair(intArrayOf(10,1,11), 10),
        Pair(intArrayOf(2,3,10,1,4), 8)
    )

    validateSolution(testCases, ::maximumDifference)
}