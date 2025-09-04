import model.Quintuple

/**
 * 88. Merge Sorted Array
 * Easy
 * https://leetcode.com/problems/merge-sorted-array/
 *
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order,
 * and two integers m and n, representing the number of valid elements in nums1 and nums2 respectively.
 * Merge nums2 into nums1 so that nums1 becomes a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 *
 * **Example 1:**
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 *
 * **Example 2:**
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 *
 * **Example 3:**
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 *
 * Constraints:
 * - nums1.length == m + n
 * - nums2.length == n
 * - 0 <= m, n <= 200
 * - 1 <= m + n <= 200
 * - -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * @param nums1 the first sorted array with enough space to hold nums2
 * @param m the number of valid initial elements in nums1
 * @param nums2 the second sorted array
 * @param n the number of elements in nums2
 * @return Unit (modifies nums1 in-place)
 */
private fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): IntArray {
    var p1 = m - 1
    var p2 = n - 1
    var insertIndex = m + n - 1
    while (p2 >= 0) {
        if (p1 < 0 || nums1[p1] < nums2[p2]) {
            nums1[insertIndex--] = nums2[p2--]
        } else {
            nums1[insertIndex--] = nums1[p1--]
        }
    }
    return nums1
}

fun main() {
    val testCases: List<Quintuple<IntArray, Int, IntArray, Int, IntArray>> = listOf(
        // Official examples
        Quintuple(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3, intArrayOf(1, 2, 2, 3, 5, 6)),
        Quintuple(intArrayOf(1), 1, intArrayOf(), 0, intArrayOf(1)),
        Quintuple(intArrayOf(0), 0, intArrayOf(1), 1, intArrayOf(1)),
        // Additional valid cases
        Quintuple(intArrayOf(4, 5, 6, 0, 0, 0), 3, intArrayOf(1, 2, 3), 3, intArrayOf(1, 2, 3, 4, 5, 6)),
        Quintuple(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(4, 5, 6), 3, intArrayOf(1, 2, 3, 4, 5, 6)),
        Quintuple(intArrayOf(1, 1, 1, 0, 0, 0), 3, intArrayOf(1, 1, 1), 3, intArrayOf(1, 1, 1, 1, 1, 1)),
        Quintuple(intArrayOf(0, 0, 1, 2, 0, 0, 0), 4, intArrayOf(0, 1, 3), 3, intArrayOf(0, 0, 0, 1, 1, 2, 3)),
        Quintuple(intArrayOf(-3, -1, 2, 0, 0, 0), 3, intArrayOf(-2, 0, 1), 3, intArrayOf(-3, -2, -1, 0, 1, 2)),
        Quintuple(intArrayOf(0, 0, 0), 0, intArrayOf(2, 2, 2), 3, intArrayOf(2, 2, 2)),
        Quintuple(intArrayOf(2, 4, 5, 0), 3, intArrayOf(1), 1, intArrayOf(1, 2, 4, 5))
    )

    validateSolution(testCases, ::merge)
}
