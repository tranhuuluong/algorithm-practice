/**
 * 1929: Concatenation of Array
 * Easy
 * https://leetcode.com/problems/concatenation-of-array/
 *
 * You are given an integer array `nums` of length n. You want to create an array `ans` of length 2n,
 * where:
 * - ans[i] == nums[i] for 0 <= i < n
 * - ans[i + n] == nums[i] for 0 <= i < n
 *
 * In other words, `ans` is formed by concatenating `nums` with itself.
 *
 * Example 1:
 * Input: nums = [1,2,1]
 * Output: [1,2,1,1,2,1]
 *
 * Example 2:
 * Input: nums = [1,3,2,1]
 * Output: [1,3,2,1,1,3,2,1]
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 1000
 * - 1 <= nums[i] <= 1000
 *
 * @param nums the input integer array
 * @return IntArray the concatenation of `nums` with itself
 */
private fun getConcatenation(nums: IntArray): IntArray {
    val result = IntArray(nums.size * 2)
    for (i in nums.indices) {
        result[i] = nums[i]
        result[i + nums.size] = nums[i]
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 2, 1), intArrayOf(1, 2, 1, 1, 2, 1)),
        Pair(intArrayOf(1, 3, 2, 1), intArrayOf(1, 3, 2, 1, 1, 3, 2, 1)),
        // Additional valid cases
        Pair(intArrayOf(), IntArray(0)),
        Pair(intArrayOf(5), intArrayOf(5, 5)),
        Pair(intArrayOf(1, 2, 3, 4), intArrayOf(1, 2, 3, 4, 1, 2, 3, 4)),
        Pair(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0, 0, 0, 0)),
        Pair(intArrayOf(1000, 999), intArrayOf(1000, 999, 1000, 999)),
        Pair(intArrayOf(10, 20, 30), intArrayOf(10, 20, 30, 10, 20, 30)),
        Pair(IntArray(1000) { it + 1 }, IntArray(2000) { if (it < 1000) it + 1 else it - 999 }),
        Pair(intArrayOf(1, 1, 1, 1, 1), intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1))
    )

    validateSolution(testCases, ::getConcatenation)
}
