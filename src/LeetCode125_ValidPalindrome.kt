/**
 * 125: Valid Palindrome
 * Easy
 * https://leetcode.com/problems/valid-palindrome/
 *
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters
 * and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.
 *
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 *
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 *
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters. Since an empty string reads the same forward and backward, it is a palindrome.
 *
 * Constraints:
 * - 1 <= s.length <= 2 * 10^5
 * - s consists only of printable ASCII characters.
 *
 * @param s the input string to check
 * @return true if the processed string (lowercased and alphanumeric-only) is a palindrome; otherwise false
 */
private fun isPalindrome(s: String): Boolean {
    if (s.length == 1) return true

    var left = 0
    var right = s.lastIndex

    while (left <= right) {
        if (!s[left].isLetterOrDigit()) {
            left++
            continue
        }
        while (!s[right].isLetterOrDigit()) {
            right--
            continue
        }
        if (s[left].lowercase() != s[right].lowercase()) return false
        left++
        right--
    }
    return true
}

fun main() {
    // Test cases as Pair<input, expected>, including the official examples.
    val testCases: List<Pair<String, Boolean>> = listOf(
        // Official Example 1
        Pair("A man, a plan, a canal: Panama", true),
        // Official Example 2
        Pair("race a car", false),
        // Official Example 3
        Pair(" ", true),
        // Single character
        Pair("a", true),
        // Two characters, palindrome
        Pair("aa", true),
        // Two characters, not palindrome
        Pair("ab", false),
        // Mixed case palindrome
        Pair("AbBa", true),
        // With punctuation and spaces
        Pair("No 'x' in Nixon", true),
        // Only non-alphanumeric characters
        Pair("!!!", true),
        // Palindrome with numbers
        Pair("12321", true),
        // Not palindrome with numbers and letters
        Pair("1a2", false)
    )


    validateSolution(testCases, ::isPalindrome)
}
