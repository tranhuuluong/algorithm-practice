import kotlin.math.max

/**
 * 121: Best Time to Buy and Sell Stock
 * Easy
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * You are given an array prices where prices[i] is the price of a given stock on the i-th day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different
 * day in the future to sell that stock. Return the maximum profit you can achieve from this transaction.
 * If you cannot achieve any profit, return 0.
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: Prices are decreasing, so no profit is possible.
 *
 * Constraints:
 *  - 1 <= prices.length <= 10^5
 *  - 0 <= prices[i] <= 10^4
 *
 * @param prices the list of stock prices per day
 * @return the maximum profit from a single buy-sell transaction (or 0 if no profit)
 */
private fun maxProfit(prices: IntArray): Int {
    var minPrice = prices.first()
    var profit = 0
    for (i in 1..prices.lastIndex) {
        val price = prices[i]
        profit = max(profit, price - minPrice)
        if (price < minPrice) minPrice = price
    }
    return profit
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(7, 1, 5, 3, 6, 4), 5),
        Pair(intArrayOf(7, 6, 4, 3, 1), 0),
        // Additional realistic test cases
        Pair(intArrayOf(1, 2, 3, 4, 5), 4),       // always increasing
        Pair(intArrayOf(5, 4, 3, 2, 1), 0),       // always decreasing
        Pair(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4), 4), // buy at 0, sell at 4
        Pair(intArrayOf(2, 4, 1), 2),             // buy at 2, sell at 4
        Pair(intArrayOf(2, 1, 2, 0, 1), 1),       // buy at 1, sell at 2
        Pair(intArrayOf(0), 0),                   // single day, no transaction
        Pair(intArrayOf(1, 2), 1),                // simple two-day rising
        Pair(intArrayOf(2, 1), 0)                 // simple two-day falling
    )

    validateSolution(testCases, ::maxProfit)
}
