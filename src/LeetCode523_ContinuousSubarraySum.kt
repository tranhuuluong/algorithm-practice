/**
 * 523: Continuous Subarray Sum
 * Medium
 * https://leetcode.com/problems/continuous-subarray-sum/
 *
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of length
 * at least two whose sum is a multiple of k.
 *
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k.
 * 0 is always considered a multiple of k. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose sum is 6. :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 *
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * -2^31 <= sum(nums) <= 2^31 - 1  (sum fits 32-bit signed int) :contentReference[oaicite:3]{index=3}
 *
 * @param nums the integer array
 * @param k the integer divisor
 * @return true if there is a continuous subarray of length ≥ 2 whose sum is a multiple of k, else false
 */
private fun checkSubarraySum(nums: IntArray, k: Int): Boolean {
    val map = mutableMapOf<Int, Int>()
    var mod = 0
    map[0] = -1
    for (i in 0 until nums.size) {
        mod = (mod + nums[i]) % k
        if (map[mod] == null) {
            map[mod] = i
        } else if (i - map[mod]!! >= 2) {
            return true
        }
    }
    return false
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(23, 2, 4, 6, 7), 6, true),
        Triple(intArrayOf(23, 2, 6, 4, 7), 6, true),
        Triple(intArrayOf(23, 2, 6, 4, 7), 13, false),
        Triple(intArrayOf(0, 1, 0), 1, true),
        Triple(intArrayOf(5, 0, 0, 0), 3, true),
        Triple(intArrayOf(1, 2, 3), 5, true),
        Triple(intArrayOf(1, 2), 4, false),
        Triple(intArrayOf(2, 4, 4), 2, true),
        Triple(intArrayOf(1_000_000_000, 1_000_000_000), 1_000_000_000, true)
    )

    validateSolution(testCases, ::checkSubarraySum)
}
