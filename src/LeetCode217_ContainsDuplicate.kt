/**
 * 217. Contains Duplicate
 *
 * Given an integer array `nums`, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 *
 * Examples:
 *
 * Input: nums = [1,2,3,1]
 * Output: true
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 *
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 */

private fun containsDuplicate(nums: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    for (num in nums) {
        if (!set.add(num)) return true
    }
    return false
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(1, 2, 3, 1), true),
        Pair(intArrayOf(1, 2, 3, 4), false),
        Pair(intArrayOf(1, 1, 1, 3, 3, 4, 3, 2, 4, 2), true),
        Pair(intArrayOf(), false),
        Pair(intArrayOf(1), false),
        Pair(intArrayOf(1, 1), true),
        Pair(intArrayOf(-1, -2, -3, -1), true),
        Pair(intArrayOf(1000000000, -1000000000), false)
    )

    for ((index, test) in testCases.withIndex()) {
        val (nums, expected) = test
        val actual = containsDuplicate(nums)
        println("Test Case #$index")
        println("Input: nums = ${nums.joinToString()}")
        println("Expected: $expected, Actual: $actual")
        println(if (expected == actual) "✅ Passed" else "❌ Failed")
        println("----")
    }
}