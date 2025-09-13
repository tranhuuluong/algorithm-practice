/**
 * 374: Guess Number Higher or Lower
 * Easy
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 *
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 * You call a pre-defined API guess(int num), which returns three possible results:
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 * Return the number that I picked.
 *
 * Example:
 * Input: n = 10, pick = 6
 * Output: 6
 *
 * Constraints:
 * 1 <= pick <= n <= 2³¹ - 1
 *
 * @param n the upper bound of the range [1, n] within which the number is picked
 * @return Int the picked number
 */
private fun guessNumber(n: Int, pick: Int): Int {
    fun guess(number: Int): Int = when {
        number < pick -> 1
        number > pick -> -1
        else -> 0
    }

    if (guess(n) == 0) return n
    var l = 0
    var r = n - 1
    while (l <= r) {
        val m = l + (r - l) / 2
        when (guess(m)) {
            -1 -> r = m - 1
            1 -> l = m + 1
            else -> return m
        }
    }
    return -1
}

fun main() {
    val testCases = listOf(
        // Each test case: Pair(n, expected)
        Triple(1, 1, 1),
        Triple(2, 1, 1),  // assume pick = 1
        Triple(2, 2, 2),  // assume pick = 2
        Triple(10, 6, 6),
        Triple(10, 1, 1),
        Triple(10, 10, 10),
        Triple(100, 50, 50),
        Triple(100, 99, 99),
        Triple(1000, 500, 500),
        Triple(1000000, 123456, 123456),
        Triple(Int.MAX_VALUE, Int.MAX_VALUE - 1, Int.MAX_VALUE - 1)
    )

    validateSolution(testCases, ::guessNumber)
}
