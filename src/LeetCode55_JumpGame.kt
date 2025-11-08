import kotlin.math.max

/**
 * 55: Jump Game
 * Medium
 * https://leetcode.com/problems/jump-game/
 *
 * You are given an integer array nums. You are initially positioned at the array’s first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
 * which makes it impossible to reach the last index.
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 10^5
 *
 * @param nums the integer array where each element is maximum jump length at that index
 * @return true if the last index is reachable, false otherwise
 */
private fun canJump(nums: IntArray): Boolean {
    var prevMax = 0
    for (i in nums.indices) {
        prevMax--
        prevMax = max(prevMax, nums[i])
        if (i + nums[i] >= nums.lastIndex) {
            return true
        } else if (nums[i] == 0 && prevMax == 0) {
            return false
        }
    }
    return false
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(2,3,1,1,4), true),       // Example 1
        Pair(intArrayOf(3,2,1,0,4), false),      // Example 2
        Pair(intArrayOf(0), true),
        Pair(intArrayOf(2,0,0), true),
        Pair(intArrayOf(1,1,1,1,1), true),
        Pair(intArrayOf(1,0,1,0), false),
        Pair(intArrayOf(2,5,0,0,0), true),
        Pair(intArrayOf(4,0,0,0,0,0,1), false),
        Pair(intArrayOf(1,2,3,0,0,0,0), false),
        Pair(intArrayOf(3,0,8,2,0,0,1), true)
    )

    validateSolution(testCases, ::canJump)
}
