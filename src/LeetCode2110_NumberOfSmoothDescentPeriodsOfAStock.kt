/**
 * 2110: Number of Smooth Descent Periods of a Stock
 * Medium
 * https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/
 *
 * You are given an integer array prices representing the daily price of a stock.
 *
 * A smooth descent period is defined as a subarray where for each i (1 <= i < subarray.length),
 * prices[i] = prices[i - 1] - 1.
 *
 * Note that a subarray of length 1 is always considered a smooth descent period.
 *
 * Return the number of smooth descent periods.
 *
 * Example 1:
 * Input: prices = [3,2,1,4]
 * Output: 7
 * Explanation:
 * The smooth descent periods are:
 * [3], [2], [1], [4], [3,2], [2,1], [3,2,1]
 *
 * Example 2:
 * Input: prices = [8,6,7,7]
 * Output: 4
 * Explanation:
 * The smooth descent periods are:
 * [8], [6], [7], [7]
 *
 * Constraints:
 * 1 <= prices.length <= 100000
 * 1 <= prices[i] <= 100000
 *
 * @param prices array of daily stock prices
 * @return number of smooth descent periods
 */
private fun getDescentPeriods(prices: IntArray): Long {
    var l = 0
    var r = 1
    var ans = 1L
    while (r <= prices.size - 1) {
        if (prices[r] != prices[r - 1] - 1) {
            l = r
        }
        ans += r - l + 1
        r++
    }
    return ans
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(3,2,1,4), 7L),
        Pair(intArrayOf(8,6,7,7), 4L),

        // Additional tests
        Pair(intArrayOf(1), 1L),
        Pair(intArrayOf(5,4,3,2,1), 15L),
        Pair(intArrayOf(1,2,3,4), 4L),
        Pair(intArrayOf(10,9,8,8,7), 9L),
        Pair(intArrayOf(100,99,98,97), 10L),
        Pair(intArrayOf(5,5,5), 3L),
        Pair(intArrayOf(9,8,7,6,5,4), 21L),
        Pair(intArrayOf(2,1,2,1), 6L)
    )

    validateSolution(testCases, ::getDescentPeriods)
}
