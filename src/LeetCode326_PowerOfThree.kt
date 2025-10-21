/**
 * 326: Power of Three
 * Easy
 * https://leetcode.com/problems/power-of-three/
 *
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * An integer n is a power of three, if there exists an integer x such that n == 3^x.
 *
 * Example 1:
 * Input: 27
 * Output: true
 *
 * Example 2:
 * Input: 0
 * Output: false
 *
 * Example 3:
 * Input: 9
 * Output: true
 *
 * Example 4:
 * Input: 45
 * Output: false
 *
 * Constraints:
 * -2^31 <= n <= 2^31-1
 *
 * @param n the integer to check
 * @return true if n is a power of three, otherwise false
 */
private fun isPowerOfThree1(n: Int): Boolean {
    return n > 0 && 1162261467 % n == 0
}

private fun isPowerOfThree2(n: Int): Boolean {
    return when {
        n == 1 -> true
        n >= 3 -> if (n % 3 == 0) isPowerOfThree2(n / 3) else false
        else -> false
    }
}

fun main() {
    val testCases = listOf(
        Pair(27, true),    // Example 1
        Pair(0, false),    // Example 2
        Pair(9, true),     // Example 3
        Pair(45, false),   // Example 4
        Pair(1, true),     // 3^0 = 1
        Pair(3, true),     // 3^1 = 3
        Pair(2, false),
        Pair(-3, false),
        Pair(1162261467, true),  // 3^19 = 1162261467 fits in 32-bit
        Pair(1162261468, false)
    )

    validateSolution(testCases, ::isPowerOfThree1)
    printDivider()
    validateSolution(testCases, ::isPowerOfThree2)
}
