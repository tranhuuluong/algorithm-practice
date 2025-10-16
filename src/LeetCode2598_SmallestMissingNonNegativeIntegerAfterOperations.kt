/**
 * 2598: Smallest Missing Non-negative Integer After Operations
 * Medium
 * https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations/
 *
 * You are given a 0-indexed integer array `nums` and an integer `value`.
 * You can perform operations on this array where each operation allows you to either add or subtract `value` from any element in `nums`.
 * You can perform any number of these operations.
 *
 * The MEX (minimum excluded) of an array is defined as the smallest non-negative integer that is not present in the array.
 * Return the maximum possible MEX value you can achieve after applying the add/subtract operations any number of times.
 *
 * Example 1:
 * Input: nums = [1,-10,7,13,6,8], value = 5
 * Output: 4
 * Explanation: You can transform to [1,0,7,13,6,8] (adjust -10 by +10), then transform others to cover 0…3, but 4 cannot be formed.
 *
 * Example 2:
 * Input: nums = [1,-10,7,13,6,8], value = 7
 * Output: 2
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - 1 <= value <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 * @param nums the input integer array which can be modified by operations
 * @param value the increment/decrement unit
 * @return Int the maximum achievable MEX (smallest missing non-negative integer)
 */
private fun findSmallestInteger(nums: IntArray, value: Int): Int {
    val freq = IntArray(value)
    for (num in nums) {
        freq[(num % value + value) % value]++
    }
    var i = 0
    while(freq[i % value]-- > 0) i++
    return i
}

fun main() {
    val testCases = listOf(
        // Official-like examples
        Triple(intArrayOf(1, -10, 7, 13, 6, 8), 5, 4),
        Triple(intArrayOf(1, -10, 7, 13, 6, 8), 7, 2),
        // Additional edge and typical cases
        Triple(intArrayOf(0), 1, 1),
        Triple(intArrayOf(0, 1, 2), 3, 3),
        Triple(intArrayOf(5,10,15), 5, 1),
        Triple(intArrayOf(2, 7, 12, 17), 5, 0),
        Triple(intArrayOf(1,1,1,1), 2, 0),
        Triple(intArrayOf(1,3,5,7), 2, 0),
        Triple(intArrayOf(1,4,7,10), 3, 0),
        Triple(intArrayOf(0,5,10,15), 5, 1),
        Triple(intArrayOf(1,6,11,16), 5, 0)
    )

    validateSolution(testCases, ::findSmallestInteger)
}
