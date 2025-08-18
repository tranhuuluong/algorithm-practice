import kotlin.collections.sorted

/**
 * 15: 3Sum
 * Medium
 * https://leetcode.com/problems/3sum/
 *
 * Given an integer array `nums`, return all unique triplets `[nums[i], nums[j], nums[k]]` such that:
 * - i, j, and k are distinct indices,
 * - `nums[i] + nums[j] + nums[k] == 0`.
 * The solution set must not contain duplicate triplets.
 *
 * Example 1:
 * Input: nums = [-1, 0, 1, 2, -1, -4]
 * Output: [[-1, -1, 2], [-1, 0, 1]]
 *
 * Example 2:
 * Input: nums = [0, 1, 1]
 * Output: []
 *
 * Example 3:
 * Input: nums = [0, 0, 0]
 * Output: [[0, 0, 0]]
 *
 * Constraints:
 * - 3 <= nums.length <= 3000
 * - -10^5 <= nums[i] <= 10^5
 *
 * @param nums the input integer array (unsorted)
 * @return a list of unique integer triplets that sum to zero
 */
private fun threeSum(nums: IntArray): List<List<Int>> {
    if (nums.size == 3) {
        return if (nums.sum() == 0) listOf(nums.toList()) else emptyList()
    }

    val result = mutableListOf<List<Int>>()
    nums.sort()
    for (i in 0..nums.lastIndex) {
        val a = nums[i]
        if (nums[i] > 0) return result
        if (i > 0 && a == nums[i - 1]) continue

        var left = i + 1
        var right = nums.lastIndex
        while (left < right) {
            val b = nums[left]
            val c = nums[right]
            when {
                a + b + c > 0 -> right--
                a + b + c < 0 -> left++
                else -> {
                    result.add(listOf(a, b, c))
                    left++
                    right--
                    while (left < right && nums[left] == b) {
                        left++
                    }
                }
            }
        }
    }

    return result
}

fun main() {
    val testCases = listOf(
        // Official example
        Pair(intArrayOf(-1,0,1,2,-1,-4), listOf(listOf(-1,-1,2), listOf(-1,0,1))),
        // Empty array -> no triplets
        Pair(intArrayOf(), listOf()),
        // Single element -> no triplets
        Pair(intArrayOf(0), listOf()),
        // All zeros -> one triplet
        Pair(intArrayOf(0,0,0), listOf(listOf(0,0,0))),
        // No valid triplet
        Pair(intArrayOf(1,2,-2,-1), listOf()),
        // Multiple solutions
        Pair(intArrayOf(-2,0,1,1,2), listOf(listOf(-2,0,2), listOf(-2,1,1))),
        // Large mix with duplicates
        Pair(
            intArrayOf(-4,-2,-2,-2,0,1,2,2,2,3,3,4),
            listOf(listOf(-4,0,4), listOf(-4,1,3), listOf(-4,2,2), listOf(-2,-2,4), listOf(-2,0,2))
        ),
        // Custom case
        Pair(
            intArrayOf(-5,2,3,0,1,2,-1,-4),
            listOf(listOf(-5,2,3), listOf(-4,1,3), listOf(-4,2,2), listOf(-1,0,1))
        ),
        // Duplicate 0 handling
        Pair(intArrayOf(-1,0,1,0), listOf(listOf(-1,0,1))),
        // Balanced set
        Pair(
            intArrayOf(-2,-1,0,0,1,2),
            listOf(listOf(-2,0,2), listOf(-1,0,1))
        )
    )

    val comparator: (List<List<Int>>, List<List<Int>>) -> Boolean = { expected, actual ->
        expected.map { it.sorted() } == actual.map { it.sorted() }
    }

    validateSolution(testCases, ::threeSum, comparator)
}
