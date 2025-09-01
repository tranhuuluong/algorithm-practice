import kotlin.math.max

/**
 * 3. Longest Substring Without Repeating Characters
 * Medium
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 *
 * Constraints:
 * - 0 <= s.length <= 5 * 10⁴
 * - s consists of English letters, digits, symbols and spaces.
 *
 * @param s the input string
 * @return the length of the longest substring without repeating characters
 */
private fun lengthOfLongestSubstring(s: String): Int {
    var result = 0
    var p1 = 0
    var p2 = 0
    while (p2 < s.length) {
        val substring = s.substring(p1, p2 + 1)
        if (substring.toSet().size == substring.length) {
            result = max(result, substring.length)
            p2++
        } else {
            p1++
            continue
        }
        if (p1 == p2) p2++
    }
    return result
}

// "abcacb"
private fun lengthOfLongestSubstring2(s: String): Int {
    var result = 0
    var p1 = 0
    var p2 = 0
    val set = mutableSetOf<Char>()
    while (p2 < s.length) {
        if (s[p2] in set) {
            set.remove(s[p1])
            p1++
            continue
        } else {
            set.add(s[p2])
            result = max(result, p2 - p1 + 1)
        }
        p2++
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair("abcabcbb", 3),
        Pair("bbbbb", 1),
        Pair("pwwkew", 3),
        Pair("", 0),
        Pair(" ", 1),
        Pair("au", 2),
        Pair("dvdf", 3),
        Pair("anviaj", 5),
        Pair("tmmzuxt", 5),
        Pair("abba", 2)
    )

    validateSolution(testCases, ::lengthOfLongestSubstring)
    printDivider()
    validateSolution(testCases, ::lengthOfLongestSubstring2)
}
