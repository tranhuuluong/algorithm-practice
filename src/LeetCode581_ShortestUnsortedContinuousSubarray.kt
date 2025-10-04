import kotlin.math.max
import kotlin.math.min

/**
 * 581: Shortest Unsorted Continuous Subarray
 * Medium
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 *
 * Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
 * Return the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: nums = [2,6,4,8,10,9,15]
 * Output: 5
 *
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 0
 *
 * Constraints:
 * - 1 <= nums.length <= 10⁴ :contentReference[oaicite:0]{index=0}
 * - -10⁵ <= nums[i] <= 10⁵ :contentReference[oaicite:1]{index=1}
 *
 * @param nums the input integer array
 * @return Int the length of the shortest continuous subarray that if sorted makes the whole array sorted
 */
private fun findUnsortedSubarrayBruteForce(nums: IntArray): Int {
    val sorted = nums.copyOf().apply { sort() }
    var l = 0
    var r = nums.lastIndex
    while (l <= nums.lastIndex && nums[l] == sorted[l]) l++
    while (r >= 0 && nums[r] == sorted[r]) r--
    return (r - l + 1).coerceAtLeast(0)
}

private fun findUnsortedSubarray(nums: IntArray): Int {
    var l = 0
    while (l < nums.lastIndex && nums[l] <= nums[l + 1]) l++
    if (l == nums.lastIndex) return 0

    var r = nums.lastIndex
    while (r > 0 && nums[r] >= nums[r - 1]) r--

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for (i in l..r) {
        min = min(min, nums[i])
        max = max(max, nums[i])
    }

    while (l > 0 && nums[l - 1] > min) l--
    while (r < nums.lastIndex && nums[r + 1] < max) r++

    return r - l + 1
}

//2,6,4,8,10,9,15

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(2,6,4,8,10,9,15), 5),
        Pair(intArrayOf(1,2,3,4), 0),
        Pair(intArrayOf(1), 0),
        Pair(intArrayOf(1,3,2,2,2), 4),
        Pair(intArrayOf(2,1), 2),
        Pair(intArrayOf(1,2,4,5,3), 3),
        Pair(intArrayOf(-1,-2,-3,0,1), 3),
        Pair(intArrayOf(1,2,3,3,3), 0),
        Pair(intArrayOf(1,1,1,0,1), 4),
        Pair(intArrayOf(1,3,5,4,2,6), 4)
    )

    validateSolution(testCases, ::findUnsortedSubarrayBruteForce)
    printDivider()
    validateSolution(testCases, ::findUnsortedSubarray)
}