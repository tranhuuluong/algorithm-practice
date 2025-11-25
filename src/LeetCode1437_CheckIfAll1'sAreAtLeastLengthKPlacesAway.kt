/**
 * 1437: Check If All 1's Are at Least Length K Places Away
 * Easy
 * https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
 *
 * Given a binary array nums and an integer k, return true if all 1's are at least k places away from each other, otherwise return false. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: nums = [1,0,0,0,1,0,0,1], k = 2
 * Output: true
 * Explanation: Each of the 1s are at least 2 places away from each other. :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: nums = [1,0,0,1,0,1], k = 2
 * Output: false
 * Explanation: The second 1 and third 1 are only one apart from each other. :contentReference[oaicite:2]{index=2}
 *
 * Example 3:
 * Input: nums = [1,1,1,1,1], k = 0
 * Output: true :contentReference[oaicite:3]{index=3}
 *
 * Example 4:
 * Input: nums = [0,1,0,1], k = 1
 * Output: true :contentReference[oaicite:4]{index=4}
 *
 * Constraints:
 * • 1 <= nums.length <= 10^5 :contentReference[oaicite:5]{index=5}
 * • 0 <= k <= nums.length :contentReference[oaicite:6]{index=6}
 * • nums[i] is either 0 or 1. :contentReference[oaicite:7]{index=7}
 *
 * @param nums the binary array of 0s and 1s
 * @param k the minimum number of places required between any two 1s
 * @return true if all 1s in nums are at least k places apart, false otherwise
 */
private fun kLengthApart(nums: IntArray, k: Int): Boolean {
    var lastIndex = -1
    for (i in nums.indices) {
        if (nums[i] == 1) {
            if (lastIndex >= 0 && i - lastIndex - 1 < k) return false
            lastIndex = i
        }
    }
    return true
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(1,0,0,0,1,0,0,1), 2, true),   // Example 1
        Triple(intArrayOf(1,0,0,1,0,1), 2, false),      // Example 2
        Triple(intArrayOf(1,1,1,1,1), 0, true),         // Example 3
        Triple(intArrayOf(0,1,0,1), 1, true),            // Example 4
        Triple(intArrayOf(1,0,1,0,0,0,1), 2, false),
        Triple(intArrayOf(0,0,0,0), 1, true),
        Triple(intArrayOf(1), 1, true),
        Triple(intArrayOf(1,0,0,1), 2, true),
        Triple(intArrayOf(1,0,0,1), 3, false),
        Triple(intArrayOf(1,0,0,0,0,1), 4, true)
    )

    validateSolution(testCases, ::kLengthApart)
}
