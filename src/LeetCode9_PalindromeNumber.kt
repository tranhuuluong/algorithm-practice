/**
 * 9: Palindrome Number
 * Easy
 * https://leetcode.com/problems/palindrome-number/
 *
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 * A palindrome is a number that reads the same forward and backward.
 *
 * Example 1:
 * Input: x = 121
 * Output: true
 *
 * Example 2:
 * Input: x = -121
 * Output: false
 *
 * Example 3:
 * Input: x = 10
 * Output: false
 *
 * Constraints:
 * -2^31 <= x <= 2^31 - 1
 *
 * @param x the integer to check
 * @return true if x reads the same forward and backward, false otherwise
 */
private fun isPalindrome(x: Int): Boolean {
    if (x < 0) return false

    var original = x
    var reversed = 0
    while (original > 0) {
        val lastDigit = original % 10
        reversed = reversed * 10 + lastDigit
        original /= 10
    }
    return reversed == x
}

private fun isPalindromeBruteForce(x: Int): Boolean {
    if (x < 0) return false

    val numAsString = x.toString()
    var l = 0
    var r = numAsString.lastIndex
    while (l < r) {
        if (numAsString[l] != numAsString[r]) return false
        l++
        r--
    }
    return true
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(121, true),
        Pair(-121, false),
        Pair(10, false),
        // Additional realistic test cases
        Pair(0, true),
        Pair(7, true),
        Pair(11, true),
        Pair(1001, true),
        Pair(-101, false),
        Pair(12321, true),
        Pair(123321, true)
    )

    validateSolution(testCases, ::isPalindrome)
    printDivider()
    validateSolution(testCases, ::isPalindromeBruteForce)
}
