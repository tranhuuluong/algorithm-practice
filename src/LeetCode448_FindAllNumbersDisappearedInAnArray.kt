import kotlin.math.abs

/**
 * 448. Find All Numbers Disappeared in an Array
 * Easy
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * You are given an array `nums` of `n` integers where `nums[i]` is in the range `[1, n]`.
 * Return *all the integers in the range* `[1, n]` that do not appear in `nums`.
 *
 * **Example 1:**
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 *
 * **Example 2:**
 * Input: nums = [1,1]
 * Output: [2]
 *
 * **Constraints:**
 * - n == nums.length
 * - 1 <= n <= 10⁵
 * - 1 <= nums[i] <= n
 *
 * **Follow-up:** Could you implement an algorithm that runs in O(n) time and uses only constant extra space? (The returned list does not count as extra space.)
 *
 * @param nums array of integers where each integer is in range [1, n]
 * @return list of missing integers in the range [1, n] not present in nums
 */
private fun findDisappearedNumbers(nums: IntArray): List<Int> {
    val result = mutableListOf<Int>()
    val set = nums.toSet()
    for (i in 1..nums.size) {
        if (i !in set) result.add(i)
    }

    return result
}

// 4, 3, 2, 7, 8, 2, 3, 1
// -4, -3, -2, -7, 8, 2, -3, -1
private fun findDisappearedNumbers2(nums: IntArray): List<Int> {
    val result = mutableListOf<Int>()

    for (num in nums) {
        val index = abs(num) - 1
        nums[index] = -abs(nums[index])
    }

    for (i in nums.indices) {
        if (nums[i] > 0) result.add(i + 1)
    }

    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1), listOf(5, 6)),
        Pair(intArrayOf(1, 1), listOf(2)),
        Pair(intArrayOf(2, 2), listOf(1)),
        Pair(intArrayOf(1), listOf()),
        Pair(intArrayOf(2, 2), listOf(1)),
        Pair(intArrayOf(1, 2, 3, 4, 5), listOf()),
        Pair(intArrayOf(5, 4, 3, 2, 1), listOf()),
        Pair(intArrayOf(3, 3, 3, 3), listOf(1, 2, 4)),
        Pair(intArrayOf(2, 3, 1, 5, 5), listOf(4)),
        Pair(IntArray(10) { (it % 5) + 1 }, listOf(6, 7, 8, 9, 10))
    )

    validateSolution(testCases, ::findDisappearedNumbers)
    printDivider()
    validateSolution(testCases, ::findDisappearedNumbers2)
}
