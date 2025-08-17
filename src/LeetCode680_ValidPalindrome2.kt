/**
 * 680: Valid Palindrome II
 * Easy
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Example 1:
 * Input: s = "aba"
 * Output: true
 *
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 *
 * Example 3:
 * Input: s = "abc"
 * Output: false
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s consists of lowercase English letters.
 *
 * @param s the input string, consisting of lowercase English letters
 * @return true if the string can become a palindrome after deleting at most one character; otherwise, false
 */

private fun validPalindrome(s: String): Boolean {
    return validPalindrome(s, true) || validPalindrome(s, false)
}

private fun validPalindrome(s: String, moveLeft: Boolean): Boolean {
    var left = 0
    var right = s.lastIndex
    var removed = false
    while (left < right) {
        if (!s[left].isLetterOrDigit()) left++
        if (!s[right].isLetterOrDigit()) right--
        if (s[left] == s[right]) {
            left++
            right--
            continue
        }
        if (removed) return false
        if (moveLeft) left++ else right--
        removed = true
    }
    return true
}

private fun validPalindrome2(s: String): Boolean {
    var left = 0
    var right = s.lastIndex

    while (left < right) {
        if (!s[left].isLetterOrDigit()) left++
        if (!s[right].isLetterOrDigit()) right--
        if (s[left] != s[right]) {
            return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1)
        }
        left++
        right--
    }
    return true
}

private fun validPalindrome(s: String, left: Int, right: Int): Boolean {
    var left = left
    var right = right
    while(left < right) {
        if (!s[left].isLetterOrDigit()) left++
        if (!s[right].isLetterOrDigit()) right--
        if (s[left] != s[right]) return false
        left++
        right--
    }
    return true
}

fun main() {
    // Test cases as Pair<input, expected>
    val testCases: List<Pair<String, Boolean>> = listOf(
        // Official Example 1
        Pair("aba", true),
        // Official Example 2
        Pair("abca", true),
        // Official Example 3
        Pair("abc", false),
        // Single character
        Pair("a", true),
        // Two characters, palindrome
        Pair("aa", true),
        // Two characters, non-palindrome
        Pair("ab", true), // can delete one to make 'a' or 'b'
        // Already palindrome longer
        Pair("racecar", true),
        // One delete needs from center mismatch
        Pair("radkar", true), // delete 'd' to make "rakar"
        // Requires deleting one of duplicates
        Pair("deeee", true), // delete one 'e'
        // Long palindrome with one extra at end
        Pair("abccbaZ", true), // delete 'Z'
        // Long non-palindrome beyond one delete
        Pair("abcdefg", false)
    )

    validateSolution(testCases, ::validPalindrome)
    printDivider()
    validateSolution(testCases, ::validPalindrome2)
}
