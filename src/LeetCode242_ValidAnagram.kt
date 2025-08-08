/**
 * LeetCode 242: Valid Anagram
 *
 * Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * ## Example 1:
 * ```
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * ```
 *
 * ## Example 2:
 * ```
 * Input: s = "rat", t = "car"
 * Output: false
 * ```
 *
 * ## Constraints:
 * - `1 <= s.length, t.length <= 5 * 10^4`
 * - `s` and `t` consist of lowercase English letters.
 *
 * ## Follow up:
 * What if the inputs contain Unicode characters? How would you adapt your solution to handle this?
 */

private fun isAnagram(s: String, t: String): Boolean {
    val alphaBetArr = IntArray(26)
    for (char in s) {
        alphaBetArr[char - 'a'] += 1
    }
    for (char in t) {
        alphaBetArr[char - 'a'] -= 1
    }
    return alphaBetArr.all { it == 0 }
}

// If we need to handle the Unicode characters
private fun isAnagramExtended(s: String, t: String): Boolean {
    val map = mutableMapOf<Char, Int>()
    for (char in s) {
        if (map[char] == null) {
            map[char] = 1
        } else {
            map[char] = map[char]!! + 1
        }
    }
    for (char in t) {
        if (map[char] != null) {
            map[char] = map[char]!! - 1
        }
    }
    return map.all { (_, value) -> value == 0 }
}

fun main() {
    val basicTestCases = listOf(
        Triple("anagram", "nagaram", true),
        Triple("rat", "car", false),
        Triple("listen", "silent", true),
        Triple("hello", "bello", false),
        Triple("", "", true),
        Triple("a", "a", true),
        Triple("a", "b", false),
        Triple("aa", "a", false),
        Triple("ab", "ba", true),
    )

    val unicodeTestCases = listOf(
        // Unicode Latin with accents
        Triple("résumé", "ésumér", true),
        Triple("café", "face", false),

        // Unicode emoji
        Triple("😊👍", "👍😊", true),
        Triple("😊😊", "😊", false),

        // Non-Latin scripts
        Triple("ありがとう", "うがとりあ", true), // Japanese
        Triple("سلام", "لامس", true),          // Arabic
        Triple("你好", "好你", true),          // Chinese

        // Unicode with extra or missing chars
        Triple("你好", "你好好", false),
        Triple("😊a", "a😊", true),
        Triple("😊a", "😊b", false)
    )

    for ((index, test) in basicTestCases.withIndex()) {
        val (s, t, expected) = test
        val actual = isAnagram(s, t)
        println("Test Case #$index")
        println("Input: s = \"$s\", t = \"$t\"")
        println("Expected: $expected, Actual: $actual")
        println(if (expected == actual) "✅ Passed" else "❌ Failed")
        println("----")
    }

    for ((index, test) in (basicTestCases + unicodeTestCases).withIndex()) {
        val (s, t, expected) = test
        val actual = isAnagramExtended(s, t)
        println("Test Case #$index")
        println("Input: s = \"$s\", t = \"$t\"")
        println("Expected: $expected, Actual: $actual")
        println(if (expected == actual) "✅ Passed" else "❌ Failed")
        println("----")
    }
}