import kotlin.math.abs

/**
 * 41. First Missing Positive
 * Hard
 * https://leetcode.com/problems/first-missing-positive/
 *
 * Given an unsorted integer array nums, find the smallest positive integer that is missing from the array.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 *
 * @param nums the unsorted integer array
 * @return the smallest missing positive integer
 */

// n
// X
// => 1. X in (1..n)
// => 2. X = n + 1

// [3,4,-1,1]
// [3,-4,5,-1]

//[1,2,0]
//[1,-2,4]

// [-1,0,2,3,4,5]
fun firstMissingPositive(nums: IntArray): Int {
    val n = nums.size
    for (i in nums.indices) {
        if (nums[i] <= 0) {
            nums[i] = n + 1
        }
    }

    for (i in nums.indices) {
        val index = abs(nums[i])
        if (index > n) continue
        nums[index - 1] = -1 * abs(nums[index - 1])
    }

    for (i in nums.indices) {
        if (nums[i] > 0) return i + 1
    }

    return n + 1
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(1, 2, 0), 3),
        Pair(intArrayOf(3, 4, -1, 1), 2),
        Pair(intArrayOf(7, 8, 9, 11, 12), 1),
        Pair(intArrayOf(1), 2),
        Pair(intArrayOf(2), 1),
        Pair(intArrayOf(-1, -2, -3), 1),
        Pair(intArrayOf(1, 1, 2, 2), 3),
        Pair(intArrayOf(1, 2, 3), 4),
        Pair(intArrayOf(2, 3, 4), 1),
    )

    validateSolution(testCases, ::firstMissingPositive)
}
