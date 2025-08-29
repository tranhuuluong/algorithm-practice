/**
 * 1768: Merge Strings Alternately
 * Easy
 * https://leetcode.com/problems/merge-strings-alternately/
 *
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string. Return the merged string.
 *
 * **Example 1:**
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 *
 * **Example 2:**
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 *
 * **Example 3:**
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 *
 * Constraints:
 * - 1 <= word1.length, word2.length <= 100
 * - word1 and word2 consist of lowercase English letters
 *
 * @param word1 the first input string
 * @param word2 the second input string
 * @return the merged string by alternating characters from word1 and word2
 */
private fun mergeAlternately(word1: String, word2: String): String = buildString {
    var p1 = 0
    var p2 = 0
    while (p1 <= word1.lastIndex && p2 <= word2.lastIndex) {
        append("${word1[p1]}${word2[p2]}")
        p1++
        p2++
    }
    when {
        p1 <= word1.lastIndex -> append(word1.substring(p1, word1.length))
        p2 <= word2.lastIndex -> append(word2.substring(p2, word2.length))
    }
}

fun main() {
    val testCases = listOf(
        Triple("abc", "pqr", "apbqcr"),
        Triple("ab", "pqrs", "apbqrs"),
        Triple("abcd", "pq", "apbqcd"),
        Triple("a", "b", "ab"),
        Triple("x", "yz", "xyz"),
        Triple("hello", "world", "hweolrllod"),
        Triple("short", "longerstring", "slhoonrgterstring"),
        Triple("aaa", "bbb", "ababab"),
        Triple("abcde", "f", "afbcde"),
        Triple("z", "uvwxyz", "zuvwxyz")
    )
    validateSolution(testCases, ::mergeAlternately)
}
