/**
 * 27: Remove Element
 * Easy
 * https://leetcode.com/problems/remove-element/
 *
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * The order of elements may be changed. Then return the number of elements in nums which are not equal to val.
 *
 * Example 1:
 * Input: nums = [3,2,2,3], val = 3
 * Output: 2, nums = [2,2,_,_]
 *
 * Example 2:
 * Input: nums = [0,1,2,2,3,0,4,2], val = 2
 * Output: 5, nums = [0,1,4,0,3,_,_,_]
 *
 * Constraints:
 *  - 0 <= nums.length <= 100
 *  - 0 <= nums[i] <= 50
 *  - 0 <= val <= 100
 *
 * @param nums the array of integers (modified in-place)
 * @param val the integer value to remove
 * @return the number of elements in the array not equal to val
 */
private fun removeElement(nums: IntArray, target: Int): Int {
    var insertIndex = 0
    for (i in nums.indices) {
        if (nums[i] != target) {
            nums[insertIndex] = nums[i]
            insertIndex++
        }
    }
    return insertIndex
}

private fun removeElement2(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.lastIndex
    while (left <= right) {
        if (nums[right] == target) {
            right--
            continue
        }

        if (nums[left] != target) {
            left++
            continue
        }

        nums[left] = nums[right]
        left++
        right--
    }
    return left
}

fun main() {
    validateSolution(testCases(), ::removeElement2)
    printDivider()
    validateSolution(testCases(), ::removeElement)
}

private fun testCases(): List<Triple<IntArray, Int, Int>> {
    return listOf(
        // Official examples
        Triple(intArrayOf(3, 2, 2, 3), 3, 2),
        Triple(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2, 5),
        // Additional realistic test cases
        Triple(intArrayOf(), 1, 0),                            // empty array
        Triple(intArrayOf(1, 1, 1, 1), 1, 0),                  // all elements removed
        Triple(intArrayOf(1, 2, 3, 4), 5, 4),                  // val not present
        Triple(intArrayOf(5, 5, 5, 5), 5, 0),                  // all elements match val
        Triple(intArrayOf(2, 3, 2, 3, 2, 3), 2, 3),             // remove alternating
        Triple(intArrayOf(2, 3, 4, 2, 5, 2, 6), 2, 4),          // multiple scattered
        Triple(intArrayOf(0, 1, 0, 1, 0, 1), 0, 3),             // remove zeros in alternating pattern
        Triple(intArrayOf(Int.MAX_VALUE, 1, Int.MAX_VALUE), Int.MAX_VALUE, 1) // extreme values
    )
}
