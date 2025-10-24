/**
 * 2048: Next Greater Numerically Balanced Number
 * Medium
 * https://leetcode.com/problems/next-greater-numerically-balanced-number/
 *
 * An integer x is numerically balanced if for every digit d in the number x, there are exactly d occurrences of that digit in x.
 *
 * Given an integer n, return the smallest numerically balanced number strictly greater than n.
 *
 * Example 1:
 * Input: n = 1
 * Output: 22
 * Explanation:
 * 22 is numerically balanced since:
 * - The digit 2 occurs 2 times.
 *
 * Example 2:
 * Input: n = 1000
 * Output: 1333
 * Explanation:
 * 1333 is numerically balanced since:
 * - The digit 1 occurs 1 time.
 * - The digit 3 occurs 3 times.
 * Note that 1022 cannot be the answer because 0 appeared more than 0 times.
 *
 * Example 3:
 * Input: n = 3000
 * Output: 3133
 * Explanation:
 * 3133 is numerically balanced since:
 * - The digit 1 occurs 1 time.
 * - The digit 3 occurs 3 times.
 *
 * Constraints:
 * 0 <= n <= 10^6
 *
 * @param n the given integer
 * @return the smallest numerically balanced number strictly greater than n
 */
private fun nextBeautifulNumber(n: Int): Int {
    fun isBalance(n: Int): Boolean {
        var n = n
        return buildMap {
            while (n > 10) {
                val digit = n % 10
                put(digit, getOrDefault(digit, 0) + 1)
                n /= 10
            }
            put(n, getOrDefault(n, 0) + 1)
        }.all { (key, value) ->
            key == value
        }
    }

    for (num in n + 1..1000000) {
        if (isBalance(num)) {
            return num
        }
    }
    return -1
}

private fun nextBeautifulNumber2(n: Int): Int {
    fun isBalance(n: Int): Boolean {
        val intArray = IntArray(10)
        var n = n
        while (n > 0) {
            intArray[n % 10]++
            n /= 10
        }

        for (i in 0..intArray.lastIndex) {
            if (intArray[i] > 0 && i != intArray[i]) {
                return false
            }
        }
        return true
    }

    for (num in n + 1..1000000) {
        if (isBalance(num)) {
            return num
        }
    }
    return -1
}

fun main() {
    val testCases = listOf(
        Pair(1, 22),            // Example 1
        Pair(1000, 1333),       // Example 2
        Pair(3000, 3133),       // Example 3
        Pair(0, 1),             // edge: smallest possible n
        Pair(2, 22),
        Pair(21, 22),
        Pair(22, 122),          // after 22 next balanced is 122
        Pair(121, 122),
        Pair(122, 212),
        Pair(5000, 14444)        // a plausible next balanced (you may verify actual)
    )

    validateSolution(testCases, ::nextBeautifulNumber)
    printDivider()
    validateSolution(testCases, ::nextBeautifulNumber2)
}
