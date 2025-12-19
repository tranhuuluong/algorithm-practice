import kotlin.math.max

/**
 * 2874: Maximum Value of an Ordered Triplet II
 * Medium
 * https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/
 *
 * You are given a 0-indexed integer array `nums`.
 *
 * Return the maximum value of $nums[i] - nums[j]) * nums[k]$ where $0 \le i < j < k < nums.length$.
 * If all such triplets $(i, j, k)$ have a negative value, return 0.
 *
 * Example 1:
 * Input: nums = [12,6,1,2,7]
 * Output: 77
 * Explanation: For the triplet $(0, 2, 4)$, we have $i = 0, j = 2, k = 4$. The value is $(nums[0] - nums[2]) * nums[4] = (12 - 1) * 7 = 77$.
 *
 * Example 2:
 * Input: nums = [1,10,3,4,19]
 * Output: 133
 * Explanation: For the triplet $(1, 2, 4)$, we have $i = 1, j = 2, k = 4$. The value is $(nums[1] - nums[2]) * nums[4] = (10 - 3) * 19 = 133$.
 *
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: The only ordered triplet is $(0, 1, 2)$. The value is $(nums[0] - nums[1]) * nums[2] = (1 - 2) * 3 = -3$. Since the value is negative, we return 0.
 *
 * Constraints:
 * $3 \le nums.length \le 10^5$
 * $1 \le nums[i] \le 10^6$
 *
 * @param nums The input integer array.
 * @return The maximum value of $(nums[i] - nums[j]) * nums[k]$ for $i < j < k$, or 0 if the max value is negative.
 */
private fun maximumTripletValue(nums: IntArray): Long {
    var ans = 0L
    val n = nums.size
    val leftMax = IntArray(n)
    val rightMax = IntArray(n)
    for (i in 1 until n) {
        leftMax[i] = max(leftMax[i - 1], nums[i - 1])
        rightMax[n - 1 - i] = max(rightMax[n - i], nums[n - i])
    }
    for (i in 1 until n) {
        ans = max((leftMax[i] - nums[i]) * rightMax[i].toLong(), ans)
    }
    return ans
}

fun main() {
    // Function takes 1 argument (nums), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(12, 6, 1, 2, 7), 77L),
        Pair(intArrayOf(1, 10, 3, 4, 19), 133L),
        Pair(intArrayOf(1, 2, 3), 0L),

        // Corrected and Additional Edge Cases (min 10 total)

        // 1. Array with maximum difference found by max(i) and min(j)
        // i=0(100), j=1(1), k=2(10) -> (100 - 1) * 10 = 990
        Pair(intArrayOf(100, 1, 10, 5, 2), 990L),

        // 2. Maximum k is far from max(i)
        // i=0(10), j=1(1), k=4(1000) -> (10 - 1) * 1000 = 9000
        Pair(intArrayOf(10, 1, 5, 2, 1000), 9000L),

        // 3. Strictly decreasing array (All triplets result in non-positive difference, so max is 0)
        // i=0(10), j=1(5), k=2(2) -> (10 - 5) * 2 = 10. Max is 10. (Initial error corrected)
        Pair(intArrayOf(10, 5, 2), 10L),

        // 4. Strictly increasing array (All difference are negative, max is 0)
        // i=0(1), j=1(5), k=2(10) -> (1-5)*10 = -40. Max is 0.
        Pair(intArrayOf(1, 5, 10, 15, 20), 0L),

        // 5. Array where best i and k are far apart
        // i=0(1000), j=1(1), k=9(1000) -> (1000 - 1) * 1000 = 999000
        Pair(intArrayOf(1000, 1, 2, 3, 4, 5, 6, 7, 8, 1000), 999000L),

        // 6. Max value of 0, achieved by zero difference
        // i=0(5), j=1(5), k=2(10) -> (5-5)*10 = 0. All other are negative.
        Pair(intArrayOf(5, 5, 10, 10, 10), 0L),

        // 7. Max value of 0, achieved by negative products
        Pair(intArrayOf(5, 6, 7, 8, 9), 0L),

        // 8. Max constraint value for nums[i]
        // i=0(10^6), j=1(1), k=2(10) -> (1000000 - 1) * 10 = 9999990
        Pair(intArrayOf(1000000, 1, 10, 5), 9999990L),

        // 9. Short array with positive result
        // i=0(5), j=1(1), k=2(5) -> (5-1)*5 = 20
        Pair(intArrayOf(5, 1, 5), 20L),

        // 10. Long array, ensuring complexity is handled (Max value is 50)
        // i=4(8), j=5(3), k=9(10) -> (8-3)*10 = 50
        Pair(intArrayOf(5, 1, 4, 2, 8, 3, 9, 7, 6, 10), 50L)
    )

    validateSolution(testCases, ::maximumTripletValue)
}