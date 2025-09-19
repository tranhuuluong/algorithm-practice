/**
 * 557: Reverse Words in a String III
 * Easy
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
 *
 * Given a string s that contains words separated by spaces, reverse the characters in each word while
 * still preserving whitespace and the original word order.
 *
 * Example 1:
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * Example 2:
 * Input: s = "God Ding"
 * Output: "doG gniD"
 *
 * Constraints:
 * - 1 <= s.length <= 5 * 10⁴
 * - s consists of printable ASCII characters and spaces.
 * - There is at least one word in s.
 * - Words are separated by single spaces.
 *
 * @param s the input string containing one or more words separated by spaces
 * @return String a new string where each word from s has its characters reversed, but spaces and word order unchanged
 */
private fun reverseWords(s: String): String {
    val result = s.toCharArray()
    var l = 0
    for (r in s.indices) {
        if (r == s.lastIndex) {
            result.reverse(l, r + 1)
        } else if (s[r + 1] == ' ') {
            result.reverse(l, r + 1)
            l = r + 2
        }
    }

    return String(result)
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair("Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc"),
        Pair("God Ding", "doG gniD"),
        // Additional test cases
        Pair("hello world", "olleh dlrow"),
        Pair("a", "a"),
        Pair("ab", "ba"),
        Pair("ab cd", "ba dc"),
        Pair("leading", "gnidael"),
        Pair("trailing", "gniliart"),
        Pair("multiple spaces here", "elpitlum secaps ereh"),
        Pair("UPPER lower", "REPPU rewol"),
        Pair("Palindrome emordnilaP", "emordnilaP Palindrome")
    )

    validateSolution(testCases, ::reverseWords)
}
