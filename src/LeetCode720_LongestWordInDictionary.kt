/**
 * 720: Longest Word in Dictionary
 * Medium
 * https://leetcode.com/problems/longest-word-in-dictionary/
 *
 * Given an array of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words.
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 * If there is no answer, return the empty string. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl". :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply". :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * words[i] consists only of lowercase English letters. :contentReference[oaicite:3]{index=3}
 *
 * @param words the array of strings representing the dictionary
 * @return the longest word that can be built one character at a time by other words in words
 */
private fun longestWord1(words: Array<String>): String {
    val wordSet = words.toSet()
    var result = ""
    for (word in words) {
        var temp = word
        while (temp in wordSet) {
            temp = temp.dropLast(1)
        }
        if (temp.isEmpty()) {
            if (word.length == result.length && word < result) {
                result = word
            } else if (word.length > result.length) {
                result = word
            }
        }
    }
    return result
}

private fun longestWord2(words: Array<String>): String {
    words.sort()
    var result = ""
    val wordSet = mutableSetOf<String>()
    for (word in words) {
        if (word.length == 1 || word.dropLast(1) in wordSet) {
            if (word.length > result.length) result = word
            wordSet.add(word)
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(arrayOf("w","wo","wor","worl","world"), "world"),             // Example 1
        Pair(arrayOf("a","banana","app","appl","ap","apply","apple"), "apple"),  // Example 2
        Pair(arrayOf("k","ki","kir","kira","kiran"), "kiran"),
        Pair(arrayOf("a","b","c"), "a"),
        Pair(arrayOf("ab","abc","abcd","a","bcd","abcde"), "abcde"),
        Pair(arrayOf("a","ar","art","arti","artic","artich","artiche","articher","articherry"), "articher"),
        Pair(arrayOf("x","xy","xyz","xyza","xyzar","xyl"), "xyzar"),
        Pair(arrayOf("c","ca","cat","cats","catss","catsu","catsut"), "catsut"),
        Pair(arrayOf("a","aa","aaa","aaaa","aaaab"), "aaaab"),
        Pair(arrayOf("z","za","zab","zabc","zabcd","zabcde","zabcdef"), "zabcdef")
    )

    validateSolution(testCases, ::longestWord1)
    printDivider()
    validateSolution(testCases, ::longestWord2)
}