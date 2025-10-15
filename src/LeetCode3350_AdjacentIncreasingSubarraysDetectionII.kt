import kotlin.math.min

/**
 * 3350: Adjacent Increasing Subarrays Detection II
 * Medium
 * https://leetcode.ca/2024-11-24-3350-Adjacent-Increasing-Subarrays-Detection-II/ :contentReference[oaicite:0]{index=0}
 *
 * Given an array `nums` of `n` integers, your task is to find the maximum value of `k` for which there exist two adjacent subarrays of length `k` each, such that both subarrays are strictly increasing. Specifically, check if there are two subarrays of length `k` starting at indices `a` and `b` (`a < b`), where:
 *   • Both subarrays `nums[a..a + k - 1]` and `nums[b..b + k - 1]` are strictly increasing. :contentReference[oaicite:1]{index=1}
 *   • The subarrays must be adjacent, meaning `b = a + k`. :contentReference[oaicite:2]{index=2}
 * Return the maximum possible value of `k`. :contentReference[oaicite:3]{index=3}
 *
 * Example 1:
 * Input: nums = [2,5,7,8,9,2,3,4,3,1]
 * Output: 3 :contentReference[oaicite:4]{index=4}
 *
 * Example 2:
 * Input: nums = [1,2,3,4,4,4,4,5,6,7]
 * Output: 2 :contentReference[oaicite:5]{index=5}
 *
 * Constraints:
 * - 2 <= nums.length <= 2 * 10^5 :contentReference[oaicite:6]{index=6}
 * - -10^9 <= nums[i] <= 10^9 :contentReference[oaicite:7]{index=7}
 *
 * @param nums the input integer array
 * @return Int the maximum k such that two adjacent strictly-increasing subarrays of length k exist
 */
private fun maxIncreasingSubarrays(nums: IntArray): Int {
    var cur = 0
    var prev = 0
    var ans = 0
    for (i in nums.indices) {
        cur++
        if (i == nums.lastIndex || nums[i] >= nums[i + 1]) {
            ans = maxOf(ans, cur / 2, min(prev, cur))
            prev = cur
            cur = 0
        }
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(2, 5, 7, 8, 9, 2, 3, 4, 3, 1), 3),
        Pair(intArrayOf(1, 2, 3, 4, 4, 4, 4, 5, 6, 7), 2),
        Pair(intArrayOf(1, 1, 1, 1), 1),
        Pair(intArrayOf(1, 2), 1),
        Pair(intArrayOf(1, 2, 3, 2, 3, 4), 3),
        Pair(intArrayOf(5, 4, 3, 2, 1), 1),
        Pair(intArrayOf(-1, 0, 1, 2, -1, 0, 1, 2), 4),
        Pair(intArrayOf(10, 20, 30, 5, 6, 7, 8), 3),
        Pair(intArrayOf(3, 3, 4, 5, 6, 6, 7, 8), 3),
        Pair(intArrayOf(1, 2, 1, 2, 1, 2, 1, 2), 2)
    )

    validateSolution(testCases, ::maxIncreasingSubarrays)
}
