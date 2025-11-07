import kotlin.math.abs

/**
 * 504: Base 7
 * Easy
 * https://leetcode.com/problems/base-7/
 *
 * Given an integer num, return a string of its base-7 representation.
 *
 * Example 1:
 * Input: num = 100
 * Output: "202"
 *
 * Example 2:
 * Input: num = -7
 * Output: "-10"
 *
 * Constraints:
 * -10^7 <= num <= 10^7 :contentReference[oaicite:0]{index=0}
 *
 * @param num the integer to convert to base 7
 * @return the string representation of num in base-7
 */
private fun convertToBase7(num: Int): String {
    if (num == 0) return "0"
    val sign = if (num < 0) "-" else ""
    var num = abs(num)
    val sb = StringBuilder()
    while (num > 0) {
        sb.insert(0, num % 7)
        num /= 7
    }
    sb.insert(0, sign)
    return sb.toString()
}

fun main() {
    val testCases = listOf(
        Pair(100, "202"),      // Example 1
        Pair(-7, "-10"),       // Example 2
        Pair(0, "0"),
        Pair(1, "1"),
        Pair(6, "6"),
        Pair(7, "10"),
        Pair(8, "11"),
        Pair(49, "100"),
        Pair(-49, "-100"),
        Pair(343, "1000")      // 343 = 7^3
    )

    validateSolution(testCases, ::convertToBase7)
}
