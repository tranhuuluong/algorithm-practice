/**
 * 3432: Count Partitions with Even Sum Difference
 * Easy
 * https://leetcode.com/problems/count-partitions-with-even-sum-difference/
 *
 * You are given an integer array nums of length n.
 *
 * A partition is defined as an index i where 0 <= i < n - 1, splitting the array into two non-empty
 * subarrays such that:
 * - Left subarray contains indices [0, i].
 * - Right subarray contains indices [i + 1, n - 1].
 *
 * Return the number of partitions where the difference between the sum of the left and right subarrays
 * is even.
 *
 * Example 1:
 * Input: nums = [10,10,3,7,6]
 * Output: 4
 *
 * Example 2:
 * Input: nums = [1,2,2]
 * Output: 0
 *
 * Example 3:
 * Input: nums = [2,4,6,8]
 * Output: 3
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * @param nums input integer array
 * @return number of valid partitions
 */
private fun countPartitions1(nums: IntArray): Int {
    val sum = nums.sum()
    var count = 0
    for (num in 0 until nums.lastIndex) {
        if ((sum - 2 * num) % 2 == 0) count++
    }
    return count
}

private fun countPartitions2(nums: IntArray): Int {
    return if (nums.sum() % 2 == 0) nums.size - 1 else 0
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(10, 10, 3, 7, 6), 4),
        Pair(intArrayOf(1, 2, 2), 0),
        Pair(intArrayOf(2, 4, 6, 8), 3),
        Pair(intArrayOf(1, 1), 1),
        Pair(intArrayOf(5, 5, 5), 0),
        Pair(intArrayOf(0, 0, 0, 0), 3),
        Pair(intArrayOf(-2, -2, -2, -2), 3),
        Pair(intArrayOf(1000000000, -1000000000), 1),
        Pair(intArrayOf(3, 7, 11, 13, 17), 0),
        Pair(intArrayOf(4, 1, 6, 3, 8, 2), 5)
    )

    validateSolution(testCases, ::countPartitions1)
    printDivider()
    validateSolution(testCases, ::countPartitions2)
}
