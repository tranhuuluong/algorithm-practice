import kotlin.math.abs

/**
 * 977: Squares of a Sorted Array
 * Easy
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 *
 * Given an integer array `nums` sorted in non-decreasing order, return an array of the squares of each number
 * sorted in non-decreasing order.
 *
 * Example:
 * Input: nums = [-4, -1, 0, 3, 10]
 * Output: [0, 1, 9, 16, 100]
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums is sorted in non-decreasing order
 *
 * @param nums the sorted input integer array
 * @return IntArray the array of squared values in sorted order
 */
private fun sortedSquares(nums: IntArray): IntArray {
    var l = 0
    var r = nums.lastIndex
    val result = IntArray(nums.size)
    for (i in r downTo 0) {
        if (abs(nums[l]) >= abs(nums[r])) {
            result[i] = nums[l] * nums[l]
            l++
        } else {
            result[i] = nums[r] * nums[r]
            r--
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(-4, -1, 0, 3, 10), intArrayOf(0, 1, 9, 16, 100)),
        Pair(intArrayOf(-7, -3, 2, 3, 11), intArrayOf(4, 9, 9, 49, 121)),
        Pair(intArrayOf(0), intArrayOf(0)),
        Pair(intArrayOf(1), intArrayOf(1)),
        Pair(intArrayOf(-1), intArrayOf(1)),
        Pair(intArrayOf(-2, -1), intArrayOf(1, 4)),
        Pair(intArrayOf(1, 2, 3), intArrayOf(1, 4, 9)),
        Pair(intArrayOf(-3, -1, 0, 1, 2), intArrayOf(0, 1, 1, 4, 9)),
        Pair(intArrayOf(-5, -4, -3, -2, -1), intArrayOf(1, 4, 9, 16, 25)),
        Pair(intArrayOf(2, 3, 5, 8, 13), intArrayOf(4, 9, 25, 64, 169))
    )

    validateSolution(testCases, ::sortedSquares)
}
