/**
 * 392: Is Subsequence
 * Easy
 * https://leetcode.com/problems/is-subsequence/
 *
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not). :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * s and t consist only of lowercase English letters. :contentReference[oaicite:1]{index=1}
 *
 * @param s the string to check as subsequence
 * @param t the string in which to check
 * @return true if s is a subsequence of t, false otherwise
 */
private fun isSubsequence(s: String, t: String): Boolean {
    var p1 = 0
    var p2 = 0
    while (p1 <= s.lastIndex && p2 <= t.lastIndex) {
        if (s[p1] == t[p2]) {
            p1++
        }
        p2++
    }
    return p1 > s.lastIndex
}

fun main() {
    val testCases = listOf(
        Triple("abc", "ahbgdc", true),
        Triple("axc", "ahbgdc", false),
        Triple("", "ahbgdc", true),
        Triple("a", "", false),
        Triple("ace", "abcde", true),
        Triple("aec", "abcde", false),
        Triple("aaaa", "aaaa", true),
        Triple("aaa", "aa", false),
        Triple("ab", "ba", false),
        Triple("xyz", "xxyyzz", true)
    )

    validateSolution(testCases, ::isSubsequence)
}
