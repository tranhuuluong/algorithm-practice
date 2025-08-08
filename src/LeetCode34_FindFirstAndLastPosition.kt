/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Medium
 *
 * Given an array of integers nums sorted in non-decreasing order, find the starting
 * and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - nums is a non-decreasing array.
 * - -10^9 <= target <= 10^9
 */

private fun searchRange(nums: IntArray, target: Int): IntArray {
    var firstIndex = -1
    var lastIndex = -1

    var left = 0
    var right = nums.lastIndex

    while (left <= right) {
        val mid = left + (right - left) / 2
        val midNum = nums[mid]
        when {
            midNum < target -> left = mid + 1
            midNum > target -> right = mid - 1
            else -> {
                firstIndex = mid
                right = mid - 1
            }
        }
    }

    left = 0
    right = nums.lastIndex

    while (left <= right) {
        val mid = left + (right - left) / 2
        val midNum = nums[mid]
        when {
            midNum < target -> left = mid + 1
            midNum > target -> right = mid - 1
            else -> {
                lastIndex = mid
                left = mid + 1
            }
        }
    }

    return intArrayOf(firstIndex, lastIndex)
}

private fun searchRangeBruteForce(nums: IntArray, target: Int): IntArray {
    var firstIndex = -1
    var lastIndex = -1

    nums.forEachIndexed { index, num ->
        if (num == target) {
            if (firstIndex == -1) firstIndex = index
            lastIndex = index
        }
    }

    return intArrayOf(firstIndex, lastIndex)
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(0, 1, 2, 4, 5, 5, 5, 6, 7), 5, intArrayOf(4, 6)),
        Triple(intArrayOf(1, 2, 3, 4, 5, 6, 7), 3, intArrayOf(2, 2)),
        Triple(intArrayOf(5, 5, 5, 5, 5), 5, intArrayOf(0, 4)),
        Triple(intArrayOf(2, 4, 6, 8, 10), 5, intArrayOf(-1, -1)),
        Triple(intArrayOf(1, 2, 3, 4), 0, intArrayOf(-1, -1)),
        Triple(intArrayOf(1, 2, 3, 4, 5), 1, intArrayOf(0, 0)),
        Triple(intArrayOf(1, 2, 3, 4, 5), 5, intArrayOf(4, 4)),
        Triple(intArrayOf(1, 2, 5, 5, 5, 6, 7), 5, intArrayOf(2, 4)),
        Triple(intArrayOf(1, 2, 3, 3, 3, 3, 4, 5), 3, intArrayOf(2, 5)),
        Triple(intArrayOf(), 3, intArrayOf(-1, -1)),
        Triple(intArrayOf(3), 3, intArrayOf(0, 0)),
        Triple(intArrayOf(1), 3, intArrayOf(-1, -1)),
        Triple(intArrayOf(3, 3), 3, intArrayOf(0, 1)),
        Triple(intArrayOf(2, 4), 3, intArrayOf(-1, -1)),
        Triple(intArrayOf(1, 1, 2, 2, 2, 3, 3), 2, intArrayOf(2, 4)),
        Triple(intArrayOf(1, 2, 3, 4, 5, 6, 6, 6, 7, 8), 6, intArrayOf(5, 7)),
        Triple(intArrayOf(1, 2, 3, 4, 5), 6, intArrayOf(-1, -1)),
        Triple(intArrayOf(6, 7, 8, 9, 10), 5, intArrayOf(-1, -1)),
        Triple(intArrayOf(-10, -5, -5, -5, 0, 1, 2), -5, intArrayOf(1, 3)),
        Triple(intArrayOf(1, 2, 3, 4, 4, 4, 4, 5, 6), 4, intArrayOf(3, 6)),
        Triple(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 1, intArrayOf(0, 0)),
        Triple(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 10, intArrayOf(9, 9)),
    )

    validateSolution(testCases, ::searchRange)
    printDivider()
    validateSolution(testCases, ::searchRangeBruteForce)
}