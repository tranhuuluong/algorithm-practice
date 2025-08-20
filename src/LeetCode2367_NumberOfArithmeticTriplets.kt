/**
 * 2367: Number of Arithmetic Triplets
 * Easy
 * https://leetcode.com/problems/number-of-arithmetic-triplets/
 *
 * You are given a 0-indexed, strictly increasing integer array nums and a positive integer diff.
 * A triplet (i, j, k) is an arithmetic triplet if the following conditions are met:
 * i < j < k,
 * nums[j] - nums[i] == diff, and
 * nums[k] - nums[j] == diff.
 * Return the number of unique arithmetic triplets.
 *
 * Example 1:
 * Input: nums = [0,1,4,6,7,10], diff = 3
 * Output: 2
 *
 * Example 2:
 * Input: nums = [4,5,6,7,8,9], diff = 2
 * Output: 2
 *
 * Constraints:
 *  - 3 <= nums.length <= 200
 *  - 0 <= nums[i] <= 200
 *  - 1 <= diff <= 50
 *  - nums is strictly increasing
 *
 * @param nums the strictly increasing integer array
 * @param diff the positive integer difference
 * @return the number of arithmetic triplets in the array
 */
private fun arithmeticTriplets(nums: IntArray, diff: Int): Int {
    val map = mutableMapOf<Int, Int>()
    var result = 0
    for (i in nums.indices) {
        map[nums[i]] = i
    }
    for (num in nums) {
        if (map[diff + num] != null && map[diff * 2 + num] != null) result++
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(0, 1, 4, 6, 7, 10), 3, 2),
        Triple(intArrayOf(4, 5, 6, 7, 8, 9), 2, 2),
        // Additional realistic test cases
        Triple(intArrayOf(1, 3, 5, 7, 9), 2, 3),
        Triple(intArrayOf(1, 2, 3, 4, 5), 1, 3),
        Triple(intArrayOf(1, 4, 7, 10), 3, 2),
        Triple(intArrayOf(1, 4, 7, 10), 2, 0),
        Triple(intArrayOf(0, 2, 4, 6, 8), 2, 3),
        Triple(intArrayOf(0, 3, 6, 9, 12), 3, 3),
        Triple(intArrayOf(0, 5, 10, 15), 5, 2),
        Triple(intArrayOf(0, 5, 10, 15), 10, 0)
    )

    validateSolution(testCases, ::arithmeticTriplets)
}
