/**
 * 2729: Check if The Number is Fascinating
 * Easy
 * https://leetcode.com/problems/check-if-the-number-is-fascinating/
 *
 * You are given an integer $n$ that consists of exactly three digits.
 * We call a positive integer fascinating if the following conditions are met:
 *
 * 1. The concatenation of $n$, $2n$, and $3n$ (in this order) results in a 9-digit number.
 * 2. The 9-digit number should contain all the digits from 1 to 9 exactly once (i.e., there are no zeros or repeated digits).
 *
 * Return `true` if $n$ is a fascinating number, or `false` otherwise.
 *
 * Example 1:
 * Input: n = 192
 * Output: true
 * Explanation: The concatenation of $n$, $2n$, and $3n$ is $192384576$. This 9-digit number contains all digits from 1 to 9 exactly once.
 *
 * Example 2:
 * Input: n = 100
 * Output: false
 * Explanation: The concatenation of $n$, $2n$, and $3n$ is $100200300$. This 9-digit number contains zeros, which is not allowed.
 *
 * Constraints:
 * $100 \le n \le 999$
 *
 * @param n A three-digit integer.
 * @return `true` if $n$ is a fascinating number, `false` otherwise.
 */
private fun isFascinating(n: Int): Boolean {
    val n2 = 2 * n
    val n3 = 3 * n
    val string = buildString {
        append(n)
        append(n2)
        append(n3)
    }
    if (string.length != 9) return false
    val set = string.toSet()
    return set.size == 9 && '0' !in set
}

fun main() {
    // Function takes 1 argument (n), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(192, true),
        Pair(100, false),

        // Additional edge cases (min 10 total)
        // Fascinating number: n=201 -> 201402603 (contains 0s, fails condition 2)
        Pair(201, false),

        // Fascinating number: n=327 -> 327654981
        Pair(327, true),

        // Fascinating number: n=273 -> 273546819
        Pair(273, true),

        // Max n: 999. 999 * 3 = 2997. Concatenation: 99919982997 (13 digits, fails condition 1)
        Pair(999, false),

        // Min n: 100 - already in example
        // n=101 -> 101202303 (contains 0s, fails condition 2)
        Pair(101, false),

        // Repeats (but no 0s)
        // n=142 -> 142284426 (repeats 2, 4, fails condition 2)
        Pair(142, false),

        // n=333 -> 333666999 (repeats 3, 6, 9, fails condition 2)
        Pair(333, false),

        // n=123 -> 123246369 (repeats 2, 3, 6, 9, fails condition 2)
        Pair(123, false),

        // n=167 -> 167334501 (contains 0, fails condition 2)
        Pair(167, false),

        // n=193 -> 193386579 (repeats 3, 9, fails condition 2)
        Pair(193, false)
    )

    validateSolution(testCases, ::isFascinating)
}