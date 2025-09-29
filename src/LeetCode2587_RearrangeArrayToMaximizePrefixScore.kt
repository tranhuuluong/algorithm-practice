/**
 * 2587: Rearrange Array to Maximize Prefix Score
 * Medium
 * https://leetcode.com/problems/rearrange-array-to-maximize-prefix-score/
 *
 * You are given a 0-indexed integer array `nums`. You can rearrange the elements of `nums` to any order (including the given order).
 * Let `prefix` be the array containing the prefix sums of `nums` after rearranging it. In other words, `prefix[i]` is the sum of the elements from 0 to i in `nums` after rearranging it.
 * The score of `nums` is the number of positive integers in the array `prefix`.
 * Return the maximum score you can achieve.
 *
 * Example 1:
 * Input: nums = [2,-1,0,1,-3,3,-3]
 * Output: 6
 * Explanation: We can rearrange into [2,3,1,-1,-3,0,-3].
 * prefix = [2,5,6,5,2,2,-1], so the score is 6.
 *
 * Example 2:
 * Input: nums = [-2,-3,0]
 * Output: 0
 * Explanation: Any rearrangement yields no positive prefix.
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5 :contentReference[oaicite:0]{index=0}
 * - -10^6 <= nums[i] <= 10^6 :contentReference[oaicite:1]{index=1}
 *
 * @param nums the input integer array which can be reordered arbitrarily
 * @return Int the maximum count of positive prefix sums achievable
 */
private fun maxScore(nums: IntArray): Int {
    nums.sort()
    var result = 0
    var sum: Long = 0
    for (i in nums.lastIndex downTo 0) {
        sum += nums[i]
        if (sum > 0) result++ else return result
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(2, -1, 0, 1, -3, 3, -3), 6),
        Pair(intArrayOf(-2, -3, 0), 0),
        // Additional cases
        Pair(intArrayOf(1, 2, 3), 3),
        Pair(intArrayOf(-1, -2, -3), 0),
        Pair(intArrayOf(0, 0, 0), 0),
        Pair(intArrayOf(5, -5, 5, -5), 3),
        Pair(intArrayOf(5, 4, -9), 2),
        Pair(intArrayOf(1000000, -1, -1, -1), 4),
        Pair(intArrayOf(10, -1, 9, -5), 4),
        Pair(intArrayOf(3, -1, -1, -1, -1), 3),
        Pair(intArrayOf(4, -2, 1, -1, 0), 5)
    )

    validateSolution(testCases, ::maxScore)
}
