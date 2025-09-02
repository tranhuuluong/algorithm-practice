/**
 * 268. Missing Number
 * Easy
 * https://leetcode.com/problems/missing-number/
 *
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 *
 * Constraints:
 * - n == nums.length
 * - 1 <= n <= 10⁴
 * - 0 <= nums[i] <= n
 * - All the numbers of nums are unique.
 *
 * @param nums the array containing n distinct numbers in the range [0, n]
 * @return the single number in the range [0, n] that is missing from nums
 */
private fun missingNumber(nums: IntArray): Int {
    return (0..nums.size).sum() - nums.sum()
}

private fun missingNumber2(nums: IntArray): Int {
    val n = nums.size
    return (n * (n + 1) / 2f).toInt() - nums.sum()
}

private fun missingNumber3(nums: IntArray): Int {
    var result = nums.size
    for (i in nums.indices) {
        result = result xor i xor nums[i]
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(3, 0, 1), 2),
        Pair(intArrayOf(0, 1), 2),
        Pair(intArrayOf(9, 6, 4, 2, 3, 5, 7, 0, 1), 8),
        Pair(intArrayOf(0), 1),
        Pair(intArrayOf(1), 0),
        Pair(intArrayOf(0, 2, 3), 1),
        Pair(intArrayOf(1, 2, 3), 0),
        Pair(intArrayOf(0, 1, 2, 3, 5), 4),
        Pair(intArrayOf(5, 4, 3, 2, 1, 0), 6),
    )

    validateSolution(testCases, ::missingNumber)
    printDivider()
    validateSolution(testCases, ::missingNumber2)
    printDivider()
    validateSolution(testCases, ::missingNumber3)
}
