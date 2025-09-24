import kotlin.math.min

/**
 * 209: Minimum Size Subarray Sum
 * Medium
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 *
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [nums[l], nums[l+1], …, nums[r]] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Constraints:
 * - 1 <= target <= 10^9 :contentReference[oaicite:0]{index=0}
 * - 1 <= nums.length <= 10^5 :contentReference[oaicite:1]{index=1}
 * - 1 <= nums[i] <= 10^4 :contentReference[oaicite:2]{index=2}
 *
 * @param target the target sum that the subarray should reach or exceed
 * @param nums the array of positive integers
 * @return Int minimal length of a contiguous subarray with sum ≥ target, or 0 if none exists
 */
private fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var l = 0
    var sum = 0
    var result = Int.MAX_VALUE
    for (r in nums.indices) {
        sum += nums[r]
        while (sum >= target) {
            result = min(r - l + 1, result)
            sum -= nums[l]
            l++
        }
    }
    return if (result == Int.MAX_VALUE) 0 else result
}

fun main() {
    val testCases = listOf(
        Triple(7, intArrayOf(2,3,1,2,4,3), 2),
        Triple(4, intArrayOf(1,4,4), 1),
        Triple(11, intArrayOf(1,1,1,1,1,1,1,1), 0),
        Triple(15, intArrayOf(1,2,3,4,5), 5),        // whole array needed
        Triple(15, intArrayOf(5,1,3,5,10,7,4,9,2,8), 2), // 10+7 meets 15
        Triple(3, intArrayOf(1,1,1), 3),
        Triple(3, intArrayOf(4,4,4), 1),             // any single ≥ target
        Triple(100, intArrayOf(1,2,3,4,5), 0),       // sum < target
        Triple(8, intArrayOf(2,3,1,2,4,3), 3),        // 2+3+1+2 =8 length 4, but 4+3 length 2 meets earlier; minimal length 2
        Triple(8, intArrayOf(2,3,1,2,4,3,1,1), 3)
    )

    validateSolution(testCases, ::minSubArrayLen)
}
