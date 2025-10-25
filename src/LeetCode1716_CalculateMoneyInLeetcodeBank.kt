/**
 * 1716: Calculate Money in Leetcode Bank
 * Easy
 * https://leetcode.com/problems/calculate-money-in-leetcode-bank/
 *
 * Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
 *
 * He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
 *
 * Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.
 *
 * Example 1:
 * Input: n = 4
 * Output: 10
 * Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.
 *
 * Example 2:
 * Input: n = 10
 * Output: 37
 * Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2nd Monday, Hercy only puts in $2.
 *
 * Example 3:
 * Input: n = 20
 * Output: 96
 * Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
 *
 * Constraints:
 * 1 <= n <= 1000
 *
 * @param n number of days
 * @return total amount of money in the Leetcode bank after n days
 */
private fun totalMoney(n: Int): Int {
    var circleCount = 0
    var curDay = 1
    var dayCount = 1
    var totalMoney = 0
    while (dayCount <= n) {
        totalMoney += (if (curDay == 7) 7 else curDay % 7) + circleCount
        if (curDay == 7) {
            circleCount++
            curDay = 1
        } else {
            curDay++
        }
        dayCount++
    }
    return totalMoney
}

fun main() {
    val testCases = listOf(
        Pair(4, 10),       // Example 1
        Pair(10, 37),      // Example 2
        Pair(20, 96),      // Example 3
        Pair(1, 1),
        Pair(7, 28),
        Pair(8, 30),
        Pair(14, 63),
        Pair(28, 154),
        Pair(100, 1060),
        Pair(1000, 74926)
    )

    validateSolution(testCases, ::totalMoney)
}
