/**
 * 26: Remove Duplicates from Sorted Array
 * Easy
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 *
 * Given an integer array `nums` sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array `nums`. More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of `nums` should hold the final result. It does not matter what you leave beyond the first `k` elements.
 * Return `k` after placing the final result in the first `k` slots of `nums`.
 *
 * Example 1:
 * Input: nums = [1,1,2]
 * Output: 2, nums = [1,2,_]
 *
 * Example 2:
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 *
 * Constraints:
 *  - 0 <= nums.length <= 3 × 10⁴
 *  - -10⁴ <= nums[i] <= 10⁴
 *  - `nums` is sorted in non-decreasing order
 *
 * @param nums the sorted integer array (modified in-place)
 * @return the number of unique elements after duplicates are removed
 */
private fun removeDuplicates(nums: IntArray): Int {
    var p1 = 1
    var p2 = 1
    while (p2 <= nums.lastIndex) {
        if (nums[p2] != nums[p1 -1]) {
            nums[p1] = nums[p2]
            p1++
        }
        p2++
    }
    return p1
}

private fun removeDuplicates2(nums: IntArray): Int {
    var currentNum = nums[0]
    var insertIndex = 1
    for (i in 1..nums.lastIndex) {
        if (nums[i] != currentNum) {
            nums[insertIndex] = nums[i]
            insertIndex++
            currentNum = nums[i]
        }
    }
    return insertIndex
}

fun main() {
    validateSolution(testCases(), ::removeDuplicates)
    printDivider()
    validateSolution(testCases(), ::removeDuplicates2)
}

private fun testCases() = listOf(
    // Official examples
    Pair(intArrayOf(1, 1, 2), 2),
    Pair(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4), 5),
    // Additional realistic test cases
    Pair(intArrayOf(1), 1),                       // single element
    Pair(intArrayOf(1, 2, 3, 4), 4),              // all unique already
    Pair(intArrayOf(2, 2, 2, 2), 1),              // all duplicates
    Pair(intArrayOf(1, 1, 2, 3, 3, 4, 4, 5), 5),  // mixed duplicates
    Pair(intArrayOf(-1, -1, 0, 0, 1, 1), 3),      // negatives + positives
    Pair(intArrayOf(100, 100, 100, 200, 200, 300), 3),
    Pair(intArrayOf(-10000, -10000, 0, 10000, 10000), 3)
)
