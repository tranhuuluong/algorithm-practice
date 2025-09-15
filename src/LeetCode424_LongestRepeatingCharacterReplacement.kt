import kotlin.math.max

/**
 * 424: Longest Repeating Character Replacement
 * Medium
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Example 1:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA". The substring "BBBB" has the longest repeating letters, which is 4.
 *
 * Constraints:
 * - 1 <= s.length <= 10⁵ :contentReference[oaicite:0]{index=0}
 * - s consists of only uppercase English letters. :contentReference[oaicite:1]{index=1}
 * - 0 <= k <= s.length :contentReference[oaicite:2]{index=2}
 *
 * @param s the input uppercase string
 * @param k the maximum number of character replacements allowed
 * @return Int the length of the longest substring containing repeating letters after at most k replacements
 */
private fun characterReplacement(s: String, k: Int): Int {
    var result = 0
    val freqArr = IntArray(26)
    var l = 0
    var maxFreq = 0
    for (r in s.indices) {
        maxFreq = max(maxFreq, ++freqArr[s[r] - 'A'])
        val window = r - l + 1
        if (window - maxFreq <= k) {
            result = max(result, window)
        } else {
            freqArr[s[l++] - 'A']--
        }
    }
    return result
}

// AABABBA

fun main() {
    val testCases = listOf(
        // Official examples
        Triple("ABAB", 2, 4),
        Triple("AABABBA", 1, 4),
        // Additional test cases
        Triple("", 0, 0),
        Triple("A", 0, 1),
        Triple("A", 1, 1),
        Triple("AAAA", 2, 4),
        Triple("ABAA", 0, 2),
        Triple("ABAA", 1, 4),
        Triple("ABCDE", 2, 3),
        Triple("AABCC", 1, 3),
        Triple("BAAAB", 2, 5),
        Triple("ABCABCABC", 3, 5) 
    )

    validateSolution(testCases, ::characterReplacement)
}
