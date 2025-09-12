/**
 * 35: Search Insert Position
 * Easy
 * https://leetcode.com/problems/search-insert-position/
 *
 * Given a sorted array of distinct integers and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Constraints:
 * - 1 <= nums.length <= 10^4
 * - -10^4 <= nums[i] <= 10^4
 * - nums contains distinct values sorted in ascending order
 * - -10^4 <= target <= 10^4
 *
 * @param nums sorted array of distinct integers
 * @param target the target value to search for or insert
 * @return Int the index if target is found, or the index where it should be inserted
 */
private fun searchInsert(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.lastIndex
    while (l <= r) {
        val mid = l + (r - l) / 2
        when {
            nums[mid] > target -> r = mid - 1
            nums[mid] < target -> l = mid + 1
            else -> return mid
        }
    }
    return l
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1,3,5,6), 5, 2),
        Triple(intArrayOf(1,3,5,6), 2, 1),
        Triple(intArrayOf(1,3,5,6), 7, 4),
        // Additional cases
        Triple(intArrayOf(1,3,5,6), 0, 0),
        Triple(intArrayOf(1), 1, 0),
        Triple(intArrayOf(1), 0, 0),
        Triple(intArrayOf(1), 2, 1),
        Triple(intArrayOf(-10, -5, 0, 5, 10), -5, 1),
        Triple(intArrayOf(-10, -5, 0, 5, 10), -6, 1),
        Triple(intArrayOf(-10, -5, 0, 5, 10), 11, 5),
        Triple(intArrayOf(2,4,6,8,10), 5, 2),
        Triple(intArrayOf(2,4,6,8,10), 10, 4)
    )

    validateSolution(testCases, ::searchInsert)
}
