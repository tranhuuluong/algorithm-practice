import kotlin.math.max

/**
 * 2529: Maximum Count of Positive Integer and Negative Integer
 * Easy
 * https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/
 *
 * Given an array `nums` sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
 *
 * Note that 0 is neither positive nor negative.
 *
 * Example 1:
 * Input: nums = [-2,-1,-1,1,2,3]
 * Output: 3
 * Explanation: There are 3 positive integers and 3 negative integers. The maximum count is 3.
 *
 * Example 2:
 * Input: nums = [-3,-2,-1,0,0,1,2]
 * Output: 3
 * Explanation: There are 2 positive integers and 3 negative integers. The maximum count is 3.
 *
 * Example 3:
 * Input: nums = [5,20,66,1314]
 * Output: 4
 * Explanation: There are 4 positive integers and 0 negative integers. The maximum count is 4.
 *
 * Constraints:
 * 1 <= nums.length <= 2000
 * -1000 <= nums[i] <= 1000
 * `nums` is sorted in a non-decreasing order.
 *
 * @param nums The array of integers sorted in non-decreasing order.
 * @return The maximum count between the number of positive integers and the number of negative integers.
 */
private fun maximumCount(nums: IntArray): Int {
    fun countNegative(): Int {
        var l = 0
        var r = nums.size - 1
        var rightMostNegativeIndex = -1
        while (l <= r) {
            val mid = (r + l) / 2
            val num = nums[mid]
            if (num < 0) {
                l = mid + 1
                rightMostNegativeIndex = mid
            } else {
                r = mid - 1
            }
        }
        return rightMostNegativeIndex + 1
    }

    fun countPositive(): Int {
        var l = 0
        var r = nums.size - 1
        var leftMostPositiveIndex = -1
        while (l <= r) {
            val mid = (r + l) / 2
            val num = nums[mid]
            if (num > 0) {
                r = mid - 1
                leftMostPositiveIndex = mid
            } else {
                l = mid + 1
            }
        }
        return if (leftMostPositiveIndex < 0) 0 else nums.size - leftMostPositiveIndex
    }

    return max(countNegative(), countPositive())
}

fun main() {
    // Function takes 1 argument (nums), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(-2, -1, -1, 1, 2, 3), 3),
        Pair(intArrayOf(-3, -2, -1, 0, 0, 1, 2), 3),
        Pair(intArrayOf(5, 20, 66, 1314), 4),

        // Additional edge cases (min 10 total)
        // 1. All negative
        Pair(intArrayOf(-10, -5, -1), 3),

        // 2. All positive
        Pair(intArrayOf(1, 5, 10), 3),

        // 3. Array contains only zeros
        Pair(intArrayOf(0, 0, 0, 0), 0),

        // 4. Single element, negative
        Pair(intArrayOf(-5), 1),

        // 5. Single element, positive
        Pair(intArrayOf(5), 1),

        // 6. Single element, zero
        Pair(intArrayOf(0), 0),

        // 7. Equal positive and negative counts, non-zero separation
        Pair(intArrayOf(-5, -4, -3, 1, 2, 3), 3),

        // 8. Positive count is greater
        Pair(intArrayOf(-2, -1, 1, 2, 3, 4, 5), 5),

        // 9. Negative count is greater
        Pair(intArrayOf(-5, -4, -3, -2, -1, 1), 5),

        // 10. Large array with zeros in the middle
        Pair(intArrayOf(-4, -3, -2, -1, 0, 0, 0, 1, 2, 3, 4, 5), 5) // Neg: 4, Pos: 5. Max: 5.
    )

    validateSolution(testCases, ::maximumCount)
}