/**
 * 930: Binary Subarrays With Sum
 * Medium
 * https://leetcode.com/problems/binary-subarrays-with-sum/
 *
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays
 * that have a sum equal to goal.
 *
 * A subarray is a contiguous part of the array.
 *
 * Example 1:
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The subarrays that sum to 2 are:
 * [1,0,1], [1,0,1,0], [0,1,0,1], [1,0,1].
 *
 * Example 2:
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * nums[i] is either 0 or 1.
 * 0 <= goal <= nums.length
 *
 * @param nums binary array of 0s and 1s
 * @param goal the target subarray sum
 * @return number of subarrays whose sum equals goal
 */
private fun numSubarraysWithSum(nums: IntArray, goal: Int): Int {
    val map = mutableMapOf<Int, Int>()
    var sum = 0
    var ans = 0
    for (num in nums) {
        sum += num
        if (sum == goal) ans++
        ans += map[sum - goal] ?: 0
        map[sum] = map.getOrDefault(sum, 0) + 1
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(1, 0, 1, 0, 1), 2, 4),
        Triple(intArrayOf(0, 0, 0, 0, 0), 0, 15),
        Triple(intArrayOf(1), 1, 1),
        Triple(intArrayOf(1), 0, 0),
        Triple(intArrayOf(0, 1, 0, 1, 0), 1, 8),
        Triple(intArrayOf(1, 1, 1), 2, 2),
        Triple(intArrayOf(1, 1, 1), 3, 1),
        Triple(intArrayOf(0, 1, 1, 0, 1), 2, 5),
        Triple(intArrayOf(0, 0, 1, 0), 1, 6),
        Triple(intArrayOf(1, 0, 0, 1, 0, 1), 2, 5)
    )

    validateSolution(testCases, ::numSubarraysWithSum)
}
