/**
 * 169. Majority Element
 * Easy
 * https://leetcode.com/problems/majority-element/
 *
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n/2⌋ times. You may assume that the majority element always exists in the array.
 *
 * **Example 1:**
 * Input: nums = [3,2,3]
 * Output: 3
 *
 * **Example 2:**
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 5 * 10⁴
 * - -10⁹ <= nums[i] <= 10⁹
 *
 * @param nums array of integers
 * @return the majority element appearing more than n/2 times
 */
private fun majorityElement(nums: IntArray): Int {
    var result = 0
    var count = 0
    for (num in nums) {
        when {
            count == 0 -> {
                result = num
                count++
            }
            num == result -> count++
            else -> count--
        }
    }

    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(3, 2, 3), 3),
        Pair(intArrayOf(2, 2, 1, 1, 1, 2, 2), 2),
        Pair(intArrayOf(1), 1),
        Pair(intArrayOf(5, 5, 5, 5), 5),
        Pair(intArrayOf(7, 3, 7), 7),
        Pair(intArrayOf(-1, -1, 2, 1, -1), -1),
        Pair(intArrayOf(0, 0, 1, 0, 0), 0),
        Pair(intArrayOf(10_000_000, -10_000_000, 10_000_000, 10_000_000), 10_000_000),
        Pair(intArrayOf(100, 100, 50, 100, 50, 100, 50, 100), 100),
        Pair(IntArray(5_000) { 1 } + IntArray(1) { 2 }, 1) // large with obvious majority
    )

    validateSolution(testCases, ::majorityElement)
}