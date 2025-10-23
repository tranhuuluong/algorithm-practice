/**
 * 3461: Check If Digits Are Equal in String After Operations I
 * Easy
 * https://leetcode.com/problems/check-if-digits-are-equal-in-string-after-operations-i/
 *
 * You are given a string s consisting of digits. Perform the following operation repeatedly until the string has exactly two digits:
 *
 * For each pair of consecutive digits in s, starting from the first digit, calculate a new digit as the sum of the two digits modulo 10.
 * Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
 *
 * Return true if the final two digits in s are the same; otherwise, return false.
 *
 * Example 1:
 * Input: s = "3902"
 * Output: true
 *
 * Example 2:
 * Input: s = "34789"
 * Output: false
 *
 * Constraints:
 * 3 <= s.length <= 100
 * s consists of only digits.
 *
 * @param s the input string of digits
 * @return true if the final two digits after repeated operations are equal, false otherwise
 */
private fun hasSameDigits(s: String): Boolean {
    fun operation(s: String) = buildString {
        for (i in 0 until s.lastIndex) {
            append((s[i].digitToInt() + s[i + 1].digitToInt()) % 10)
        }
    }
    var s = s
    while (s.length > 2) {
        s = operation(s)
    }
    return s[0] == s[1]
}

fun main() {
    val testCases = listOf(
        Pair("3902", true),    // Example 1
        Pair("34789", false),  // Example 2
        Pair("123", false),
        Pair("11", true),      // Note: length must be >=3 per constraints, but we include for edge thought
        Pair("99", true),
        Pair("909", true),
        Pair("000", true),
        Pair("5678", false),
        Pair("989", true),
        Pair("4567", false)
    )

    validateSolution(testCases, ::hasSameDigits)
}
