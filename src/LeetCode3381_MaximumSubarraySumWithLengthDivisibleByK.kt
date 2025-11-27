import kotlin.math.max

/**
 * 3381: Maximum Subarray Sum With Length Divisible by K
 * Medium
 * https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k/
 *
 * You are given an array of integers nums and an integer k.
 * Return the maximum sum of a non-empty contiguous subarray of nums
 * such that the length of the subarray is divisible by k.
 *
 * Example 1:
 * Input: nums = [1,2], k = 1
 * Output: 3
 * Explanation: The subarray [1, 2] has length 2 which is divisible by 1, and its sum is 3.
 *
 * Example 2:
 * Input: nums = [-1,-2,-3,-4,-5], k = 4
 * Output: -10
 * Explanation: The subarray [-1, -2, -3, -4] has length 4 (divisible by 4) and sum -10, which is maximal among valid subarrays.
 *
 * Example 3:
 * Input: nums = [-5,1,2,-3,4], k = 2
 * Output: 4
 * Explanation: The subarray [1, 2, -3, 4] has length 4 (divisible by 2) and sum 4, which is the maximum possible.
 *
 * Constraints:
 * 1 <= k <= nums.length <= 2 * 10^5
 * -10^9 <= nums[i] <= 10^9 :contentReference[oaicite:0]{index=0}
 *
 * @param nums the input integer array
 * @param k the divisor for subarray length
 * @return the maximum sum of any contiguous subarray whose length is divisible by k
 */
private fun maxSubarrayDivByK(nums: IntArray, k: Int): Long {
    var sum = 0L
    val longArray = LongArray(nums.size)
    for (i in nums.indices) {
        sum += nums[i]
        longArray[i] = sum
    }

    var loop = 1
    var ans = Long.MIN_VALUE

    while (k * loop <= nums.size) {
        val windowSize = k * loop
        for (i in nums.indices) {
            if (i + windowSize - 1 > nums.lastIndex) {
                break
            }
            val sum = longArray[i + windowSize - 1] - if (i > 0 ) longArray[i - 1] else 0
            ans = max(sum, ans)
        }
        loop++
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(1,2), 1, 3L),                        // Example 1
        Triple(intArrayOf(-1,-2,-3,-4,-5), 4, -10L),           // Example 2
        Triple(intArrayOf(-5,1,2,-3,4), 2, 4L),                // Example 3
        Triple(intArrayOf(5,-1,5), 3, 9L),                     // whole array length 3 divisible by 3
        Triple(intArrayOf(2,-1,2,3,-2,1), 2, 6L),              // subarray [2, -1, 2, 3] sum=6
        Triple(intArrayOf(3, -2, 5, -1, 2), 1, 7L),           // any length OK: full array sum=7
        Triple(intArrayOf(-2, -3, -1, -4), 2, -4L),            // pick [-2, -1] or [-1, -4], best is -3
        Triple(intArrayOf(0,0,0), 2, 0L),                      // zeros
        Triple(intArrayOf(7, -5, 6, -2, 4), 3, 8L),            // subarray [7,-5,6] sum=8 or [6,-2,4] sum=8 or [7,-5,6,-2,4] sum=10? — verify
        Triple(intArrayOf(1_000_000_000, -1_000_000_000, 1_000_000_000), 2, 0L)  // edge big values
    )

    validateSolution(testCases, ::maxSubarrayDivByK)
}
