/**
 * 3583: Count Special Triplets
 * Medium
 * https://leetcode.com/problems/count-special-triplets/
 *
 * You are given an integer array nums.
 *
 * A special triplet is defined as a triplet of indices (i, j, k) such that:
 *   0 <= i < j < k < n, where n = nums.length,
 *   nums[i] == nums[j] * 2,
 *   nums[k] == nums[j] * 2.
 *
 * Return the total number of special triplets in the array.
 * Since the answer may be large, return it modulo 10^9 + 7. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: nums = [6,3,6]
 * Output: 1
 * Explanation: The only special triplet is (i, j, k) = (0, 1, 2). :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: nums = [0,1,0,0]
 * Output: 1
 * Explanation: The only special triplet is (i, j, k) = (0, 2, 3). :contentReference[oaicite:2]{index=2}
 *
 * Example 3:
 * Input: nums = [8,4,2,8,4]
 * Output: 2
 * Explanation: The special triplets are (0,1,3) and (1,2,4). :contentReference[oaicite:3]{index=3}
 *
 * Constraints:
 * 3 <= nums.length <= 10^5 :contentReference[oaicite:4]{index=4}
 * 0 <= nums[i] <= 10^5 :contentReference[oaicite:5]{index=5}
 *
 * @param nums the input integer array
 * @return the number of special triplets modulo 10^9 + 7
 */
private fun countSpecialTriplets(nums: IntArray): Int {
    var ans = 0L
    val leftPass = IntArray(100001)
    val rightPass = IntArray(100001)
    for (num in nums) {
        rightPass[num]++
    }
    for (num in nums) {
        rightPass[num]--
        if (num * 2 <= 100001) {
            ans += rightPass[num * 2].toLong() * leftPass[num * 2]
        }
        leftPass[num]++
    }
    return (ans % 1000000007).toInt()
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(6, 3, 6), 1),
        Pair(intArrayOf(0, 1, 0, 0), 1),
        Pair(intArrayOf(8, 4, 2, 8, 4), 2),
        Pair(intArrayOf(2, 1, 2), 1),
        Pair(intArrayOf(4, 2, 4, 2, 4), 4),
        Pair(intArrayOf(1, 2, 4, 2, 4, 4), 2),
        Pair(intArrayOf(0, 0, 0), 1),
        Pair(intArrayOf(0, 0, 0, 0), 4),
        Pair(intArrayOf(5, 0, 5, 0, 5), 0),
        Pair(intArrayOf(100000, 50000, 100000), 1)
    )

    validateSolution(testCases, ::countSpecialTriplets)
}
