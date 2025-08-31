/**
 * 560. Subarray Sum Equals K
 * Medium
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Explanation: subarrays [1,1] and [1,1] have sum equal to k = 2.
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 * Explanation: subarrays [1,2] and [3] have sum equal to k = 3.
 *
 * Constraints:
 * - 1 <= nums.length <= 2 * 10^4
 * - -10^3 <= nums[i] <= 10^3
 * - -10^7 <= k <= 10^7
 *
 * @param nums the input integer array
 * @param k the target sum
 * @return the total number of subarrays whose sum equals k
 */
private fun subarraySum(nums: IntArray, k: Int): Int {
    var result = 0
    var sum = 0
    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        sum += num
        if (sum == k) result++
        map[sum - k]?.let { frequency ->
            result += frequency
        }
        map[sum] = map.getOrDefault(sum, 0) + 1
    }
    return result
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(1, 1, 1), 2, 2),
        Triple(intArrayOf(1, 2, 3), 3, 2),
        Triple(intArrayOf(1), 1, 1),
        Triple(intArrayOf(1), 0, 0),
        Triple(intArrayOf(3, 4, 7, 2, -3, 1, 4, 2), 7, 4),  // e.g., [7], [3,4], [7,2,-3,1], [3,4,7,2,-3,1,4,2] etc.
        Triple(intArrayOf(-1, -1, 1), 0, 1), // [-1, -1, 1] from index 0–2
        Triple(intArrayOf(0, 0, 0, 0), 0, 10), // all subarrays
        Triple(intArrayOf(1000, -1000, 1000), 1000, 3), // [1000], [1000, -1000, 1000]
        Triple(intArrayOf(1, -1, 1, -1, 1), 0, 6), // various subarrays summing to 0
    )

    validateSolution(testCases, ::subarraySum)
}
