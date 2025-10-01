/**
 * 713: Subarray Product Less Than K
 * Medium
 * https://leetcode.com/problems/subarray-product-less-than-k/
 *
 * Given an array of integers `nums` and an integer `k`, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than `k`.
 *
 * Example 1:
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]
 *
 * Example 2:
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 *
 * Constraints:
 * - 1 <= nums.length <= 3 * 10⁴ :contentReference[oaicite:0]{index=0}
 * - 1 <= nums[i] <= 1000 :contentReference[oaicite:1]{index=1}
 * - 0 <= k <= 10⁶ :contentReference[oaicite:2]{index=2}
 *
 * @param nums the input array of positive integers
 * @param k the threshold product
 * @return Int the number of non-empty contiguous subarrays whose product is less than k
 */
private fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
    if (k <= 1) return 0
    var product = 1
    var result = 0
    var l = 0
    for (r in nums.indices) {
        product *= nums[r]
        while (product >= k) product /= nums[l++]
        result += r - l + 1
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(10,5,2,6), 100, 8),
        Triple(intArrayOf(1,2,3), 0, 0),
        // Additional cases
        Triple(intArrayOf(1), 1, 0),
        Triple(intArrayOf(1), 2, 1),
        Triple(intArrayOf(2,5,3), 10, 3),
        Triple(intArrayOf(2,5,3), 1, 0),
        Triple(intArrayOf(10,9,10,4,3,8,3,3,6,2,10,10,9), 50, 22),
        Triple(intArrayOf(100,200,300), 100, 0),
        Triple(intArrayOf(5,1,2,3), 10, 8)
    )

    validateSolution(testCases, ::numSubarrayProductLessThanK)
}
