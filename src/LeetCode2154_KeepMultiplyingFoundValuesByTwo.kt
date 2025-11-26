/**
 * 2154: Keep Multiplying Found Values by Two
 * Easy
 * https://leetcode.com/problems/keep-multiplying-found-values-by-two/
 *
 * You are given an integer array nums and an integer original.
 *
 * You are asked to perform the following operation:
 * If original exists in nums, multiply original by 2. Repeat this operation as long as the new original exists in nums.
 *
 * Return the final value of original after performing the operation any number of times.
 *
 * Example 1:
 * Input: nums = [5,3,6,1,12], original = 3
 * Output: 24
 * Explanation: 3 is in nums -> original = 6
 * 6 is in nums -> original = 12
 * 12 is in nums -> original = 24
 * 24 is not in nums -> stop and return 24
 *
 * Example 2:
 * Input: nums = [2,7,9], original = 4
 * Output: 4
 * Explanation: 4 is not in nums, so we immediately return 4.
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], original <= 10^9
 *
 * @param nums the array of integers to check membership against
 * @param original the starting integer to be multiplied while present in nums
 * @return the final value of original after repeatedly multiplying by 2 while it is present in nums
 */
private fun findFinalValue(nums: IntArray, original: Int): Int {
    var original = original
    while (original in nums.toSet()) {
        original *= 2
    }
    return original
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(5,3,6,1,12), 3, 24),            // Example 1
        Triple(intArrayOf(2,7,9), 4, 4),                  // Example 2
        Triple(intArrayOf(1,2,3,4), 1, 8),                // 1->2->4->8
        Triple(intArrayOf(1,1,2,2,4,8), 1, 16),           // duplicates present
        Triple(intArrayOf(10), 5, 5),                     // original not present
        Triple(intArrayOf(5,10,20,40), 5, 80),            // chain to 80
        Triple(intArrayOf(3,6,12,24,48), 3, 96),          // chain continues
        Triple(intArrayOf(1000000000), 1000000000, 2000000000), // large values, fits in 32-bit signed
        Triple(intArrayOf(2,4,8,16,32), 2, 64),           // powers of two chain
        Triple(intArrayOf(7,14,28), 1, 1)                 // original absent, unchanged
    )

    validateSolution(testCases, ::findFinalValue)
}
