/**
 * 974: Subarray Sums Divisible by K
 * Medium
 * https://leetcode.com/problems/subarray-sums-divisible-by-k
 *
 * Given an integer array `nums` and an integer `k`, return the number of non-empty subarrays that have a sum divisible by `k`.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [4, 5, 0, -2, -3, 1], k = 5
 * Output: 7
 * Explanation:
 *   There are 7 subarrays with a sum divisible by k = 5:
 *   [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0], [0,-2,-3], [-2,-3]
 *
 * Example 2:
 * Input: nums = [5], k = 9
 * Output: 0
 *
 * Constraints:
 * - 1 <= nums.length <= 3 * 10⁴
 * - -10⁴ <= nums[i] <= 10⁴
 * - 2 <= k <= 10⁴
 *
 * @param nums the integer array
 * @param k the integer divisor
 * @return the total number of non-empty subarrays whose sum is divisible by k
 */
private fun subarraysDivByK(nums: IntArray, k: Int): Int {
    var ans = 0
    val map = mutableMapOf<Int, Int>()
    map[0] = 1
    var sum = 0
    for (num in nums) {
        sum += (num % k) + k
        val target = sum % k
        ans += map[target] ?: 0
        map[target] = map.getOrDefault(target, 0) + 1
    }
    return ans
}

private fun subarraysDivByK2(nums: IntArray, k: Int): Int {
    var ans = 0
    val map = mutableMapOf<Int, Int>()
    map[0] = 1
    var sum = 0
    for (num in nums) {
        // sum = (sum + num % k + k) % k;
        sum += (num % k) + k
        sum %= k
        ans += map[sum] ?: 0
        map[sum] = map.getOrDefault(sum, 0) + 1
    }
    return ans
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(4, 5, 0, -2, -3, 1), 5, 7),
        Triple(intArrayOf(5), 9, 0),

        // Additional test cases
        Triple(intArrayOf(1, 2, 3), 3, 3),
        Triple(intArrayOf(2, -2, 2, -4), 4, 4),
        Triple(intArrayOf(-1, -1, 1), 2, 2),
        Triple(intArrayOf(0, 0, 0), 5, 6),
        Triple(intArrayOf(5, 5, 5, 5), 5, 10),
        Triple(intArrayOf(4, 3, 2, 6, -1), 3, 4),
        Triple(intArrayOf(10000, -10000, 10000), 7, 2),
        Triple(intArrayOf(1), 2, 0)
    )

    validateSolution(testCases, :: subarraysDivByK)
    printDivider()
    validateSolution(testCases, :: subarraysDivByK2)
}
