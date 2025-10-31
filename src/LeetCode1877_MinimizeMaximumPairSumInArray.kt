import kotlin.math.max

/**
 * 1877: Minimize Maximum Pair Sum in Array
 * Medium
 * https://leetcode.com/problems/minimize-maximum-pair-sum-in-array/
 *
 * The pair sum of a pair (a, b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
 *
 * For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
 *
 * Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
 *   • Each element of nums is in exactly one pair, and
 *   • The maximum pair sum is minimized.
 *
 * Return the minimized maximum pair sum after optimally pairing up the elements. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: nums = [3,5,2,3]
 * Output: 7
 * Explanation: The elements can be paired up into pairs (3,3) and (5,2).
 *              The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7. :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: nums = [3,5,4,2,4,6]
 * Output: 8
 * Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
 *              The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8. :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 *   n == nums.length
 *   2 <= n <= 10^5
 *   n is even.
 *   1 <= nums[i] <= 10^5 :contentReference[oaicite:3]{index=3}
 *
 * @param nums the integer array of even length
 * @return the minimized maximum pair sum achievable
 */
private fun minPairSum(nums: IntArray): Int {
    nums.sort()
    var result = 0
    var l = 0
    var r = nums.lastIndex
    while (l < r) {
        result = max(result, nums[l] + nums[r])
        l++
        r--
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(3,5,2,3), 7),                // Example 1
        Pair(intArrayOf(3,5,4,2,4,6), 8),            // Example 2
        Pair(intArrayOf(1,2,3,4), 5),
        Pair(intArrayOf(2,2,2,2), 4),
        Pair(intArrayOf(1,100000), 100001),
        Pair(intArrayOf(5,5,5,5,5,5), 10),
        Pair(intArrayOf(1,2), 3),
        Pair(intArrayOf(9,1,8,2,7,3), 10),
        Pair(intArrayOf(10,20,30,40,50,60), 70),
        Pair(intArrayOf(6,2,4,8,10,12), 14)
    )

    validateSolution(testCases, ::minPairSum)
}
