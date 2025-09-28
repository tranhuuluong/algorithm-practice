import kotlin.math.max

/**
 * 643: Maximum Average Subarray I
 * Easy
 * https://leetcode.com/problems/maximum-average-subarray-i/
 *
 * You are given an integer array `nums` consisting of `n` elements, and an integer `k`.
 * Find a contiguous subarray whose length is equal to `k` that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10⁻⁵ will be accepted. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 − 5 − 6 + 50) / 4 = 51 / 4 = 12.75 :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000 :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 * - n == nums.length :contentReference[oaicite:3]{index=3}
 * - 1 <= k <= n <= 10⁵ :contentReference[oaicite:4]{index=4}
 * - -10⁴ <= nums[i] <= 10⁴ :contentReference[oaicite:5]{index=5}
 *
 * @param nums the input array of integers
 * @param k the length of subarray to consider
 * @return Double the maximum average of any contiguous subarray of length k
 */
private fun findMaxAverage(nums: IntArray, k: Int): Double {
    var max = Int.MIN_VALUE
    var sum = 0
    for (i in nums.indices) {
        sum += nums[i]
        if (i >= k) {
            sum -= nums[i - k]
        }
        if (i >= k - 1) {
            max = max(max, sum)
        }
    }
    return max.toDouble() / k
}

private fun findMaxAverage2(nums: IntArray, k: Int): Double {
    var sum = 0
    for (i in 0 until k) {
        sum += nums[i]
    }
    var max = sum
    for (i in k..nums.lastIndex) {
        sum += nums[i] - nums[i - k]
        max = max(max, sum)
    }
    return max.toDouble() / k
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1, 12, -5, -6, 50, 3), 4, 12.75),
        Triple(intArrayOf(5), 1, 5.0),
        // Additional test cases
        Triple(intArrayOf(1, 1, 1, 1, 1), 3, 1.0),
        Triple(intArrayOf(-1, -2, -3, -4, -5), 2, -1.5),
        Triple(intArrayOf(1, 2, 3, 4, 5), 5, 3.0),
        Triple(intArrayOf(10, -10, 10, -10, 10), 1, 10.0),
        Triple(intArrayOf(10, -10, 10, -10, 10), 2, 0.0),
        Triple(intArrayOf(3, 7, 5, -2, 4), 3, 5.0),
        Triple(intArrayOf(0, 1000, 2000, -1000, 500), 3, 1000.0),
        Triple(intArrayOf(-10000, 10000, -10000, 10000), 2, 0.0)
    )

    validateSolution(testCases, ::findMaxAverage)
    printDivider()
    validateSolution(testCases, ::findMaxAverage2)
}
