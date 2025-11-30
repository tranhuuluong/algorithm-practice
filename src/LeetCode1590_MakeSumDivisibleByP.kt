import kotlin.math.min

/**
 * 1590: Make Sum Divisible by P
 * Medium
 * https://leetcode.com/problems/make-sum-divisible-by-p/
 *
 * Given an array of positive integers `nums`, remove the smallest subarray (possibly empty) such
 * that the sum of the remaining elements is divisible by `p`. It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * Example 1:
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 *
 * Example 2:
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 *
 * Example 3:
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5 :contentReference[oaicite:0]{index=0}
 * - 1 <= nums[i] <= 10^9 :contentReference[oaicite:1]{index=1}
 * - 1 <= p <= 10^9 :contentReference[oaicite:2]{index=2}
 *
 * @param nums the integer array
 * @param p the integer divisor
 * @return the minimum length of subarray to remove, or -1 if not possible
 */
private fun minSubarrayBruteForce(nums: IntArray, p: Int): Int {
    val sum = nums.sumOf { it.toLong() }
    if (sum % p == 0L) return 0

    var ans = nums.size
    for (i in nums.indices) {
        var subSum = 0
        for (j in i..nums.lastIndex) {
            subSum += nums[j]
            if ((sum - subSum) % p == 0L) {
                ans = min(ans, j - i + 1)
            }
        }
    }
    return if (ans == nums.size) -1 else ans
}

private fun minSubarrayOptimized(nums: IntArray, p: Int): Int {
    val sum = nums.sumOf { it.toLong() }
    val target = (sum % p).toInt()
    if (target == 0) return 0

    var ans = nums.size
    val map = mutableMapOf<Int, Int>()
    map[0] = -1
    var prefixSum = 0
    for (i in nums.indices) {
        prefixSum = (prefixSum + nums[i]) % p
        val needed = (prefixSum - target + p) % p
        if (map.containsKey(needed)) {
            ans = min(ans, i - map[needed]!!)
        }
        map[prefixSum] = i
    }
    return if (ans == nums.size) -1 else ans
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(3, 1, 4, 2), 6, 1),
        Triple(intArrayOf(6, 3, 5, 2), 9, 2),
        Triple(intArrayOf(1, 2, 3), 3, 0),

        // Additional test cases
        Triple(intArrayOf(1, 2, 3), 7, -1),
        Triple(intArrayOf(5, 5, 5), 5, 0),
        Triple(intArrayOf(5, 5, 5, 5), 7, -1),  // remove one 5 to make sum = 15 which mod 7 = 1 → need to remove 1 mod 7 => one 5 leaves 10 %7 =3 no so best subarray? maybe impossible; this is to test negative result
        Triple(intArrayOf(5, 5, 5, 5), 3, 1),
        Triple(intArrayOf(1000000000, 1000000000, 1000000000), 3, 0),
        Triple(intArrayOf(4, 3, 2), 7, 1),
        Triple(intArrayOf(4, 3, 2), 5, 1)
    )

    validateSolution(testCases, ::minSubarrayBruteForce)
    printDivider()
    validateSolution(testCases, ::minSubarrayOptimized)
}
