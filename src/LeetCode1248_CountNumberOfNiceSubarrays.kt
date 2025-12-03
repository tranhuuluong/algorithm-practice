/**
 * 1248: Count Number of Nice Subarrays
 * Medium
 * https://leetcode.com/problems/count-number-of-nice-subarrays
 *
 * Given an array of integers `nums` and an integer `k`.
 * A continuous subarray is called nice if there are exactly `k` odd numbers in it.
 * Return the number of nice subarrays.
 *
 * Example 1:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only subarrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1]
 *
 * Example 2:
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 *
 * Example 3:
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 * Constraints:
 * - 1 <= nums.length <= 50000 :contentReference[oaicite:0]{index=0}
 * - 1 <= nums[i] <= 10^5 :contentReference[oaicite:1]{index=1}
 * - 1 <= k <= nums.length :contentReference[oaicite:2]{index=2}
 *
 * @param nums the integer array
 * @param k the exact number of odd numbers required in subarray
 * @return the total number of continuous subarrays that contain exactly k odd numbers
 */
private fun numberOfNiceSubarraysBruteForce(nums: IntArray, k: Int): Int {
    var ans = 0
    for (i in nums.indices) {
        var oddCount = 0
        for (j in i until nums.size) {
            if (nums[j] % 2 != 0) oddCount++
            when {
                oddCount == k -> ans++
                oddCount > k -> break
            }
        }
    }
    return ans
}

private fun numberOfNiceSubarraysOptimized(nums: IntArray, k: Int): Int {
    var ans = 0
    var oddCount = 0
    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        if (num % 2 != 0) oddCount++
        if (oddCount == k) ans++
        ans += map[oddCount - k] ?: 0
        map[oddCount] = map.getOrDefault(oddCount, 0) + 1
    }
    return ans
}

// var oddCount = 4
// [1,1,2,1,1], k = 3
// [
// 1 to 1
// 2 to 2
// 3 to 1
// 4 to 1
// ]
// if (map.containsKey(oddCount - k)) ans += map[oddCount - k]

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1,1,2,1,1), 3, 2),
        Triple(intArrayOf(2,4,6), 1, 0),
        Triple(intArrayOf(2,2,2,1,2,2,1,2,2,2), 2, 16),

        // Additional test cases
        Triple(intArrayOf(1), 1, 1),
        Triple(intArrayOf(2), 1, 0),
        Triple(intArrayOf(1,2,1,2,1), 2, 4),
        Triple(intArrayOf(1,2,3,4,5), 3, 1),
        Triple(intArrayOf(2,2,2,2), 0, 10),
        Triple(intArrayOf(1,1,1,1,1), 5, 1),
        Triple(intArrayOf(1,1,1,1,1), 3, 3)
    )

    validateSolution(testCases, ::numberOfNiceSubarraysBruteForce)
    printDivider()
    validateSolution(testCases, ::numberOfNiceSubarraysOptimized)
}
