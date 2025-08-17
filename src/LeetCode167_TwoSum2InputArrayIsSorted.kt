/**
 * 167: Two Sum II - Input Array Is Sorted
 * Easy
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 *
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
 * find two numbers such that they add up to a specific target number.
 * Return the indices of the two numbers (as [index1, index2]), where 1 <= index1 < index2 <= numbers.length.
 * There is exactly one solution, and you may not use the same element twice.
 * Your solution must use only constant extra space.
 *
 * Example 1:
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 *
 * Example 2:
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 *
 * Example 3:
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * :contentReference[oaicite:1]{index=1}
 *
 * Constraints:
 * - 2 <= numbers.length <= 3 * 10^4
 * - -1000 <= numbers[i] <= 1000
 * - numbers is sorted in non-decreasing order
 * - -1000 <= target <= 1000
 * - Exactly one solution exists, and you may not use the same element twice :contentReference[oaicite:2]{index=2}
 *
 * @param nums the sorted 1-indexed integer array (as a Kotlin IntArray, 0-indexed internally)
 * @param target the target sum to find
 * @return a Pair of indices (1-based) (index1, index2) whose values sum to target
 */
private fun twoSum(nums: IntArray, target: Int): IntArray {
    var left = 0
    var right = nums.lastIndex
    while(left < right) {
        when {
            nums[left] + nums[right] > target -> right--
            nums[left] + nums[right] < target -> left++
            else -> return intArrayOf(left + 1, right + 1)
        }
    }
    return intArrayOf()
}

fun main() {
    val testCases: List<Triple<IntArray, Int, IntArray>> = listOf(
        // Official Example 1
        Triple(intArrayOf(2, 7, 11, 15), 9, intArrayOf(1, 2)),
        // Official Example 2
        Triple(intArrayOf(2, 3, 4), 6, intArrayOf(1, 3)),
        // Official Example 3
        Triple(intArrayOf(-1, 0), -1, intArrayOf(1, 2)),
        // Minimal valid case with only two numbers
        Triple(intArrayOf(1, 2), 3, intArrayOf(1, 2)),
        // Negative + positive
        Triple(intArrayOf(-3, -1, 2, 4), 1, intArrayOf(2, 3)), // -1 + 2 = 1
        // Mixed negatives and positives, unique solution
        Triple(intArrayOf(-5, -2, 1, 3, 7), 2, intArrayOf(1, 5)), // -5 + 7 = 2
        // Duplicates present but arranged for unique answer
        Triple(intArrayOf(1, 1, 2, 3), 5, intArrayOf(3, 4)), // 2 (index 3) + 3 (index 4) = 5
        // Larger sorted array with unique answer
        Triple(intArrayOf(0, 4, 5, 6, 10, 12), 16, intArrayOf(2, 6)), // 4 + 12 = 16
        // Extreme values at edges
        Triple(intArrayOf(-1000, 0, 500, 1000), 0, intArrayOf(1, 4)),
        // Pair formed by identical values, but unique solution guaranteed
        Triple(intArrayOf(2, 2, 3, 6), 4, intArrayOf(1, 2)) // 2 + 2 = 4
    )

    validateSolution(testCases, ::twoSum)
}
