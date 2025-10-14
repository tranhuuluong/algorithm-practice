/**
 * 2273: Find Resultant Array After Removing Anagrams
 * Easy
 * https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/
 *
 * You are given a 0-indexed string array `words`, where `words[i]` consists of lowercase English letters.
 * In one operation, select any index i such that 0 < i < words.length and words[i-1] and words[i] are anagrams, and delete words[i] from words.
 * Keep performing this operation as long as you can select an index that satisfies the conditions.
 * Return words after performing all operations. It can be shown that selecting the indices for each operation in any arbitrary order will lead to the same result.
 *
 * Example 1:
 * Input: words = ["abba","baba","bbaa","cd","cd"]
 * Output: ["abba","cd"]
 *
 * Example 2:
 * Input: words = ["a","b","c","d","e"]
 * Output: ["a","b","c","d","e"]
 *
 * Constraints:
 * - 1 <= words.length <= 100
 * - 1 <= words[i].length <= 10
 * - words[i] consists of lowercase English letters
 *
 * @param words the array of lowercase strings
 * @return List<String> the resultant array after removing consecutive anagrams
 */
private fun removeAnagrams(words: Array<String>): List<String> {
    val result = mutableListOf<String>()
    var charArray = IntArray(26)
    for (word in words) {
        val temp = IntArray(26)
        for (c in word) {
            temp[c - 'a']++
        }
        if (!temp.contentEquals(charArray)) {
            result.add(word)
            charArray = temp
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(arrayOf("abba","baba","bbaa","cd","cd"), listOf("abba","cd")),
        Pair(arrayOf("a","b","c","d","e"), listOf("a","b","c","d","e")),
        Pair(arrayOf("aa","aa","aa"), listOf("aa")),
        Pair(arrayOf("abc","bac","bca","xyz","zxy","yxz"), listOf("abc","xyz")),
        Pair(arrayOf("abc","def","fed","ghi","ihg","ghi"), listOf("abc","def","ghi")),
        Pair(arrayOf("a"), listOf("a")),
        Pair(arrayOf("ab","ba","ab","ba","cd","dc","cd"), listOf("ab","cd")),
        Pair(arrayOf("abab","aabb","baba","baba","abba","cd","dc"), listOf("abab","cd")),
        Pair(arrayOf("xyz","zyx","zxy","yxz","abc","bac"), listOf("xyz","abc")),
        Pair(arrayOf("aaa","aa","aaa","aaa"), listOf("aaa","aa","aaa"))
    )

    validateSolution(testCases, ::removeAnagrams)
}
