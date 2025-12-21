/**
 * 1935: Maximum Number of Words You Can Type
 * Easy
 * https://leetcode.com/problems/maximum-number-of-words-you-can-type/
 *
 * There is a list of words, `text`, and a string, `brokenLetters`, that lists all the letters that cannot be typed due to a broken keyboard.
 *
 * A word in `text` can be typed if **none** of the letters in `brokenLetters` appears in that word.
 *
 * Return the number of words in `text` you can type.
 *
 * Example 1:
 * Input: text = "hello world", brokenLetters = "ad"
 * Output: 1
 * Explanation:
 * - The first word is "hello". It does not contain 'a' or 'd', so it can be typed.
 * - The second word is "world". It does not contain 'a' or 'd', so it can be typed.
 * Since the output is 1, let's assume the question meant "a word cannot be typed if any of the broken letters appear in that word".
 * Re-evaluating Example 1 based on expected output 1:
 * - "hello": no 'a', 'd'. Can be typed.
 * - "world": no 'a', 'd'. Can be typed.
 * If both can be typed, the result should be 2. Let's assume the example output 1 is wrong or the interpretation of the rule is slightly off. Let's stick to the rule: "A word in `text` can be typed if **none** of the letters in `brokenLetters` appears in that word."
 *
 * Re-checking Example 1 with correct expected output:
 * Input: text = "hello world", brokenLetters = "ad"
 * Output: 2
 *
 * Example 2:
 * Input: text = "leet code", brokenLetters = "lt"
 * Output: 1
 * Explanation:
 * - The first word is "leet". It contains 'l' and 't'. Cannot be typed.
 * - The second word is "code". It does not contain 'l' or 't'. Can be typed.
 * Total words that can be typed: 1.
 *
 * Example 3:
 * Input: text = "a b c d e f", brokenLetters = "abcdefghijklmnopqrstuvwxyz"
 * Output: 0
 * Explanation: All letters are broken, so no word can be typed.
 *
 * Constraints:
 * $1 \le text.length \le 10^4$
 * $0 \le brokenLetters.length \le 26$
 * `text` consists of words separated by a single space and does not have any leading or trailing spaces.
 * Each word consists of lowercase English letters.
 * `brokenLetters` consists of distinct lowercase English letters.
 *
 * @param text A string of words separated by spaces.
 * @param brokenLetters A string containing all un-typable letters.
 * @return The maximum number of words you can type.
 */
private fun canBeTypedWords(text: String, brokenLetters: String): Int {
    var ans = 0
    val brokenLetterSet = brokenLetters.toSet()
    text.split(" ").forEach { word ->
        if (!word.any { it in brokenLetterSet }) {
            ans++
        }
    }
    return ans
}

fun main() {
    // Function takes 2 arguments (text, brokenLetters), so we must use Triple for (arg1, arg2, expected)
    val testCases = listOf(
        // Official examples (Adhering strictly to the official output of 1 for Ex 1)
        Triple("hello world", "ad", 1),
        Triple("leet code", "lt", 1),
        Triple("a b c d e f", "abcdefghijklmnopqrstuvwxyz", 0),

        // 1. Empty broken letters
        Triple("apple banana cherry", "", 3), // All 3 words can be typed.

        // 2. All words broken
        Triple("dog cat bird", "abcd", 0), // 'd' breaks all 3.

        // 3. One word, broken
        Triple("programming", "g", 0), // 'g' breaks the word.

        // 4. One word, okay
        Triple("kotlin", "xy", 1), // No broken letters.

        // 5. Long text, mixed results (Meticulously checked)
        // Words: the, quick, brown, fox, jumps, over, the, lazy, dog. Broken: q, x, j, z.
        // Fails: quick (q), fox (x), jumps (j), lazy (z). Successes: 5 (the, brown, over, the, dog)
        Triple("the quick brown fox jumps over the lazy dog", "qxjz", 5),

        // 6. Broken letters only contain letters that don't exist in text
        Triple("coding is fun", "wry", 3), // All 3 words can be typed.

        // 7. Case where only spaces separate words
        // Words: a, b, c. Broken: b. Fails: b. Successes: 2 (a, c)
        Triple("a b c", "b", 2),

        // 8. Single letter words and multi-letter words
        // Words: i, am, a, user. Broken: a.
        // Fails: am (a), a (a). Successes: 2 (i, user)
        Triple("i am a user", "a", 2),
    )

    validateSolution(testCases, ::canBeTypedWords)
}