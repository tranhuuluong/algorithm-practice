/**
 * 58: Length of Last Word
 * Easy
 * https://leetcode.com/problems/length-of-last-word/
 *
 * Given a string `s` consisting of words and spaces, return the length of the last word in the string.
 * A word is a maximal substring consisting of non-space characters only.
 *
 * Example 1:
 * Input: s = "Hello World"
 * Output: 5
 *
 * Example 2:
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 *
 * Example 3:
 * Input: s = "luffy is still joyboy"
 * Output: 6
 *
 * Constraints:
 * - 1 ≤ s.length ≤ 10⁴ :contentReference[oaicite:0]{index=0}
 * - s consists of only English letters and spaces ' '. :contentReference[oaicite:1]{index=1}
 * - There will be at least one word in s. :contentReference[oaicite:2]{index=2}
 *
 * @param s the input string
 * @return the length of the last word in s
 */
private fun lengthOfLastWord(s: String): Int {
    var ans = 0
    for (i in s.lastIndex downTo 0) {
        if (s[i] == ' ') {
            if (ans > 0) return ans
        } else {
            ans++
        }
    }
    return ans
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair("Hello World", 5),
        Pair("   fly me   to   the moon  ", 4),
        Pair("luffy is still joyboy", 6),

        // Additional test cases
        Pair("a", 1),
        Pair("   a   ", 1),
        Pair(" day", 3),
        Pair("day ", 3),
        Pair("   multiple   spaces   here   ", 4),
        Pair("singleword", 10),
        Pair(" endsWithSpace ", 13)
    )

    validateSolution(testCases, ::lengthOfLastWord)
}
