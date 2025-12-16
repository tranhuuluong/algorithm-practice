/**
 * 33: Search in Rotated Sorted Array
 * Medium
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 *
 * There is an integer array `nums` sorted in ascending order (with distinct values).
 * Prior to being passed to your function, `nums` is possibly left rotated at an unknown pivot index $k$ ($1 \le k < nums.length$) such that the resulting array is $[nums[k], nums[k+1], \dots, nums[n-1], nums[0], nums[1], \dots, nums[k-1}]$ (0-indexed).
 * For example, $[0,1,2,4,5,6,7]$ might be left rotated at pivot index 3 and become $[4,5,6,7,0,1,2]$.
 * Given the array `nums` after the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or $-1$ if it is not in `nums`.
 *
 * You must write an algorithm with $O(\log n)$ runtime complexity.
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Constraints:
 * $1 \le nums.length \le 5000$
 * $-10^4 \le nums[i] \le 10^4$
 * All values of `nums` are unique.
 * `nums` is an ascending array that is possibly rotated.
 * $-10^4 \le target \le 10^4$
 *
 * @param nums The array of integers, possibly rotated.
 * @param target The value to search for.
 * @return The index of the target if found, otherwise $-1$.
 */
private fun search(nums: IntArray, target: Int): Int {
    var l = 0
    var r = nums.size - 1
    while (l <= r) {
        val mid = (r + l) / 2
        val num = nums[mid]
        when {
            num == target -> return mid
            num >= nums[l] -> {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid - 1
                } else {
                    l = mid + 1
                }
            }
            else -> {
                if (nums[mid] <= target && target <= nums[r]) {
                    l = mid + 1
                } else {
                    r = mid - 1
                }
            }
        }
    }
    return -1
}
// [5,6,7,0,1,2,3]

fun main() {
    // Function takes 2 arguments (nums, target), so we must use Triple for (arg1, arg2, expected)
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0, 4),
        Triple(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3, -1),
        Triple(intArrayOf(1), 0, -1),

        // Additional edge cases (min 10 total)
        // Array not rotated
        Triple(intArrayOf(1, 2, 3, 4, 5), 3, 2),
        Triple(intArrayOf(1, 2, 3, 4, 5), 6, -1),

        // Smallest length (1) - target found
        Triple(intArrayOf(1), 1, 0),

        // Longer array target found
        Triple(intArrayOf(13, 15, 17, 18, 0, 1, 3, 5, 7, 9, 10, 11), 3, 6),
        // Longer array target not found
        Triple(intArrayOf(13, 15, 17, 18, 0, 1, 3, 5, 7, 9, 10, 11), 14, -1),

        // Pivot point at index 1
        Triple(intArrayOf(3, 1, 2), 1, 1),
        // Two elements, rotated
        Triple(intArrayOf(2, 1), 2, 0)
    )

    validateSolution(testCases, ::search)
}