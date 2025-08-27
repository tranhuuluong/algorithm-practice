/**
 * 229. Majority Element II
 * Medium
 * https://leetcode.com/problems/majority-element-ii/
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Return the elements in any order.
 *
 * **Example 1:**
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * **Example 2:**
 * Input: nums = [1]
 * Output: [1]
 *
 * **Example 3:**
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 * - 1 <= nums.length <= 5 * 10⁴
 * - -10⁹ <= nums[i] <= 10⁹
 *
 * @param nums the array of integers
 * @return list of elements that appear more than ⌊ n/3 ⌋ times
 */
private fun majorityElement(nums: IntArray): List<Int> {
    var majority1 = 0
    var majority2 = 0
    var count1 = 0
    var count2 = 0

    for (num in nums) {
        when {
            num == majority1 -> count1++
            num == majority2 -> count2++
            count1 == 0 -> {
                majority1 = num
                count1++
            }
            count2 == 0 -> {
                majority2 = num
                count2++
            }
            else -> {
                count1--
                count2--
            }
        }
    }

    count1 = 0
    count2 = 0

    for (num in nums) {
        if (num == majority1) count1++
        if (num == majority2 && num != majority1) count2++
    }

    val result = mutableListOf<Int>()
    val n = nums.size / 3
    if (count1 > n) result.add(majority1)
    if (count2 > n) result.add(majority2)

    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(3, 2, 3), listOf(3)),
        Pair(intArrayOf(1), listOf(1)),
        Pair(intArrayOf(1, 2), listOf(1, 2)),
        Pair(intArrayOf(1, 1, 1, 3, 3, 2, 2, 2), listOf(1, 2)),
        Pair(intArrayOf(4, 4, 4, 4), listOf(4)),
        Pair(intArrayOf(1, 2, 3, 4), listOf()),
        Pair(intArrayOf(5, 5, 6, 6, 7), listOf(5, 6)),
        Pair(intArrayOf(1,1,2,2), listOf(1, 2)),
        Pair(intArrayOf(1,2,3), listOf()),
        Pair(intArrayOf(2,2,1,1,1,2,2), listOf(1, 2))
    )

    val comparator: (List<Int>, List<Int>) -> Boolean = { first, second ->
        first.sorted() == second.sorted()
    }
    validateSolution(testCases, ::majorityElement, comparator)
}
