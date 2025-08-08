/**
 * 1: Two Sum
 * Easy
 * https://leetcode.com/problems/two-sum/
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to the target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Constraints:
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Exactly one valid answer exists.
 *
 * @param nums the array of integers to search within
 * @param target the target sum value
 * @return an array of two indices corresponding to the numbers that add up to the target
 */
private fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        val complement = target - num
        if (map[num] != null) {
            return intArrayOf(map[num]!!, index)
        }
        map[complement] = index
    }
    return intArrayOf()
}

private fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
    for (i in 0..nums.lastIndex - 1) {
        for (j in i + 1..nums.lastIndex) {
            if (nums[i] + nums[j] == target) {
                return intArrayOf(i, j)
            }
        }
    }
    return intArrayOf()
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(2, 7, 11, 15), 9, intArrayOf(0, 1)),
        Triple(intArrayOf(3, 2, 4), 6, intArrayOf(1, 2)),
        Triple(intArrayOf(3, 3), 6, intArrayOf(0, 1)),
        Triple(intArrayOf(-1, -2, -3, -4, -5), -8, intArrayOf(2, 4)),
        Triple(intArrayOf(1, -1, 0, 2, -2), 0, intArrayOf(0, 1)),
        Triple(intArrayOf(0, 4, 3, 0), 0, intArrayOf(0, 3)),
        Triple(intArrayOf(1, 2, 3, 4, 5), 9, intArrayOf(3, 4)),
        Triple(intArrayOf(1, 5, 1, 5), 10, intArrayOf(1, 3)),
        Triple(intArrayOf(1000000000, 2000000000, -1000000000), 1000000000, intArrayOf(1, 2)),
        Triple(intArrayOf(10, 26, 30, 31, 47, 60), 40, intArrayOf(0, 2))
    )

    validateSolution(testCases, ::twoSumBruteForce)
    printDivider()
    validateSolution(testCases, ::twoSum)
}
