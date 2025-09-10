/**
 * 219: Contains Duplicate II
 * Easy
 * https://leetcode.com/problems/contains-duplicate-ii/
 *
 * You are given an integer array `nums` and an integer `k`. Return `true` if there are two distinct indices `i` and `j` in the array such that:
 *  - `nums[i] == nums[j]`
 *  - `abs(i - j) <= k`
 * Otherwise, return `false`.
 *
 * Example:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Another Example:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^9 <= nums[i] <= 10^9
 * - 0 <= k <= 10^5
 *
 * @param nums the integer array to check for nearby duplicates
 * @param k the maximum allowed distance between duplicate indices
 * @return Boolean true if such a pair exists, false otherwise
 */
private fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    val lastIndex = nums.lastIndex
    for (i in 0..lastIndex - 1) {
        for (j in (i + 1)..lastIndex) {
            if (nums[i] == nums[j] && j - i <= k) return true
        }
    }
    return false
}

private fun containsNearbyDuplicate2(nums: IntArray, k: Int): Boolean {
    val window = mutableSetOf<Int>()
    var l = 0
    for (r in nums.indices) {
        if (r - l > k) {
            window.remove(nums[l])
            l++
        }
        if (!window.add(nums[r])) return true
    }
    return false
}

private fun containsNearbyDuplicate3(nums: IntArray, k: Int): Boolean {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices) {
        val index = map[nums[i]]
        if (index == null || i - index > k) {
            map[nums[i]] = i
        } else {
            return true
        }
    }
    return false
}

fun main() {
    // testCases defined using Triple(input array, k, expected result)
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1, 2, 3, 1), 3, true),
        Triple(intArrayOf(1, 0, 1, 1), 1, true),
        Triple(intArrayOf(1, 2, 3, 1, 2, 3), 2, false),
        // Additional cases
        Triple(intArrayOf(), 0, false),
        Triple(intArrayOf(1), 1, false),
        Triple(intArrayOf(1, 2, 1), 0, false),      // duplicates but k = 0 => no
        Triple(intArrayOf(1, 2, 1), 2, true),
        Triple(intArrayOf(1, 2, 3, 4, 5), 3, false),
        Triple(intArrayOf(1, 1), 1, true),
        Triple(intArrayOf(1, 1), 0, false),         // same element but k = 0
        Triple(intArrayOf(1, 2, 2, 3, 1), 2, true),
        Triple(intArrayOf(1, 2, 3, 4, 1, 2), 3, false) // duplicates exist but too far apart
    )

    validateSolution(testCases, ::containsNearbyDuplicate)
    printDivider()
    validateSolution(testCases, ::containsNearbyDuplicate2)
    printDivider()
    validateSolution(testCases, ::containsNearbyDuplicate3)
}
