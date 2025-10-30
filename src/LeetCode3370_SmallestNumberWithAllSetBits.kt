import kotlin.math.pow

/**
 * 3370: Smallest Number With All Set Bits
 * Easy
 * https://leetcode.com/problems/smallest-number-with-all-set-bits/
 *
 * You are given a positive integer n. Return the smallest number x greater than or equal to n,
 * such that the binary representation of x contains only set bits (i.e., all bits are 1s).
 *
 * Example 1:
 * Input: n = 5
 * Output: 7
 * Explanation: The binary representation of 7 is "111". :contentReference[oaicite:0]{index=0}
 *
 * Example 2:
 * Input: n = 10
 * Output: 15
 * Explanation: The binary representation of 15 is "1111". :contentReference[oaicite:1]{index=1}
 *
 * Example 3:
 * Input: n = 3
 * Output: 3
 * Explanation: The binary representation of 3 is "11", which already contains only set bits. :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 * 1 <= n <= 1000 :contentReference[oaicite:3]{index=3}
 *
 * @param n the input positive integer
 * @return the smallest integer x ≥ n whose binary representation has all bits set to 1
 */
private fun smallestNumber(n: Int): Int {
    for (i in 1..Int.MAX_VALUE) {
        val pow = 2.toDouble().pow(i.toDouble()) - 1
        if (pow >= n) {
            return pow.toInt()
        }
    }
    return 0
}

fun main() {
    val testCases = listOf(
        Pair(5, 7),
        Pair(10, 15),
        Pair(3, 3),
        Pair(1, 1),
        Pair(2, 3),
        Pair(4, 7),
        Pair(7, 7),
        Pair(8, 15),
        Pair(15, 15),
        Pair(16, 31)
    )

    validateSolution(testCases, ::smallestNumber)
}
