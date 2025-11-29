/**
 * 3512: Minimum Operations to Make Array Sum Divisible by K
 * Easy
 * https://leetcode.com/problems/minimum-operations-to-make-array-sum-divisible-by-k
 *
 * You are given an integer array `nums` and an integer `k`. You can perform the following operation any number of times:
 *   - Select an index `i` and replace `nums[i]` with `nums[i] - 1`.
 *
 * Return the minimum number of operations required to make the sum of the array divisible by `k`.
 *
 * Example 1:
 * Input: nums = [3,9,7], k = 5
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,1,3], k = 4
 * Output: 0
 *
 * Example 3:
 * Input: nums = [3,2], k = 6
 * Output: 5
 *
 * Constraints:
 * - 1 <= nums.length <= 1000
 * - 1 <= nums[i] <= 1000
 * - 1 <= k <= 100
 *
 * @param nums the integer array
 * @param k the integer divisor
 * @return the minimum number of operations needed
 */
private fun minOperations(nums: IntArray, k: Int): Int {
    return nums.sum() % k
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(3, 9, 7), 5, 4),
        Triple(intArrayOf(4, 1, 3), 4, 0),
        Triple(intArrayOf(3, 2), 6, 5),

        // Additional test cases
        Triple(intArrayOf(1), 1, 0),
        Triple(intArrayOf(1), 2, 1),
        Triple(intArrayOf(2, 2, 2), 3, 0),
        Triple(intArrayOf(2, 2, 2), 4, 2),
        Triple(intArrayOf(5, 5, 5), 5, 0),
        Triple(intArrayOf(5, 5, 6), 5, 1),
        Triple(intArrayOf(1000, 1000, 1000), 7, (1000 + 1000 + 1000) % 7)
    )

    validateSolution(testCases, ::minOperations)
}
