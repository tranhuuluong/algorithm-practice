import kotlin.math.max
import kotlin.math.min

/**
 * 152: Maximum Product Subarray
 * Medium
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer. :contentReference[oaicite:0]{index=0}
 *
 * @param nums the input integer array
 * @return the largest product of a contiguous subarray
 */
private fun maxProduct(nums: IntArray): Int {
    var min = nums[0]
    var max = nums[0]
    var result = nums[0]
    for (i in 1..nums.lastIndex) {
        val num = nums[i]
        if (num < 0) {
            val temp = min
            min = max
            max = temp
        }
        max = max(num, num * max)
        min = min(num, num * min)
        result = max(result, max)
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(2,3,-2,4), 6),            // Example 1
        Pair(intArrayOf(-2,0,-1), 0),             // Example 2
        Pair(intArrayOf(0,2), 2),
        Pair(intArrayOf(-2,3,-4), 24),
        Pair(intArrayOf(-1,-3,-10,0,60), 60),
        Pair(intArrayOf(-2,-3,0,-2,-40), 80),
        Pair(intArrayOf(1,2,3,4), 24),
        Pair(intArrayOf(-1,0,-2), 0),
        Pair(intArrayOf(2,-5,-2,-4,3), 24),
        Pair(intArrayOf(-3,4,0,-2,0,-1,5), 5)
    )

    validateSolution(testCases, ::maxProduct)
}
