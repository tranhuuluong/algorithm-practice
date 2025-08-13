import kotlin.math.abs
import kotlin.math.max

/**
 * 128: Longest Consecutive Sequence
 * Medium
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 *
 * Example 1:
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 *
 * Example 2:
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * Constraints:
 * - 0 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 *
 * @param nums the unsorted array of integers
 * @return the length of the longest consecutive elements sequence
 */

// Time: O(nlogn)
// Space: O(n)
private fun longestConsecutive1(nums: IntArray): Int {
    val sortedNums = nums.sorted()
    var max = 0
    var answer = 0
    var currentNum: Int? = null
    for (num in sortedNums) {
        if (currentNum == num) continue
        if (currentNum == null || abs(num - currentNum) == 1) {
            max++
        } else {
            max = 1
        }
        currentNum = num
        if (max > answer) answer = max
    }
    return answer
}

// Time: O(n)
// Space: O(n)
private fun longestConsecutive2(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    val set = nums.toSet()
    var res = 1
    for (num in nums) {
        if (num - 1 !in set) {
            var cur = num
            var streak = 0
            while (cur in set) {
                streak++
                cur++
            }
            res = max(streak, res)
        }
    }
    return res
}

// Time: O(n2)
// Space: O(1)
private fun longestConsecutiveBruteForce(nums: IntArray): Int {
    var res = 0
    for (num in nums) {
        var cur = num
        var streak = 0
        while (cur in nums) {
            cur++
            streak++
        }
        res = max(streak, res)
    }
    return res
}

fun main() {
    // Test cases as Pair<input, expected>
    val testCases: List<Pair<IntArray, Int>> = listOf(
        // Official Example 1
        Pair(intArrayOf(100, 4, 200, 1, 3, 2), 4),
        // Official Example 2
        Pair(intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1), 9),
        // Empty array
        Pair(intArrayOf(), 0),
        // Single element
        Pair(intArrayOf(10), 1),
        // All same elements
        Pair(intArrayOf(5, 5, 5, 5), 1),
        // Two separate sequences
        Pair(intArrayOf(1, 2, 4, 5, 6), 3),
        // Unordered with negative values
        Pair(intArrayOf(-1, -2, -3, 0, 1), 5),
        // Mixed integers
        Pair(intArrayOf(10, 5, 6, 3, 4, 11, 12, 8), 4),
        // Long ascending
        Pair(intArrayOf(1000, 1001, 1002, 1003, 1004), 5),
        // Sequence with gaps and duplicates
        Pair(intArrayOf(1, 3, 5, 2, 4, 4, 7, 6, 8), 8),
        // Random order large set
        Pair(intArrayOf(50, 20, 21, 22, 100, 23, 24, 99, 98, 25), 6)
    )

    validateSolution(testCases, ::longestConsecutiveBruteForce)
    printDivider()
    validateSolution(testCases, ::longestConsecutive1)
    printDivider()
    validateSolution(testCases, ::longestConsecutive2)
}
