/**
 * 792: Number of Matching Subsequences
 * Medium
 * https://leetcode.com/problems/number-of-matching-subsequences/
 *
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * Example 1:
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 *
 * Example 2:
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 * Constraints:
 * - 1 <= s.length <= 5 * 10^4
 * - 1 <= words.length <= 5000
 * - 1 <= words[i].length <= 50
 * - s and words[i] consist of only lowercase English letters
 *
 * @param s the source string
 * @param words the array of query strings to test as subsequences
 * @return Int the count of words in `words` that are subsequences of `s`
 */
private fun numMatchingSubseq(s: String, words: Array<String>): Int {
    fun isSubsequence(word: String): Boolean {
        var i = 0
        for (c in s) {
            if (i < word.length && word[i] == c) i++
        }
        return i == word.length
    }

    val cache = mutableMapOf<String, Boolean>()
    var result = 0
    for (word in words) {
        val isValid = cache.getOrPut(word) {
            isSubsequence(word)
        }
        if (isValid) result++
    }
    return result
}

fun main() {
    val testCases = listOf(
        Triple("abcde", arrayOf("a","bb","acd","ace"), 3),
        Triple("dsahjpjauf", arrayOf("ahjpjau","ja","ahbwzgqnuk","tnmlanowax"), 2),
        // Additional test cases
        Triple("aaaaa", arrayOf("a","aa","aaa","aaaa","aaaaa"), 5),
        Triple("aaaaa", arrayOf("b","ab","ba","aaab","baaa"), 0),
        Triple("abc", arrayOf("","a","abc","ac","bc","abcd"), 5),
        Triple("zxy", arrayOf("z","x","y","zx","zy","xyz"), 5),
        Triple("pqrst", arrayOf("prt","ps","pqrs","pqrst","rp",""), 5),
        Triple("mississippi", arrayOf("mss","issi","sip","miss","ippi","mssippi"), 6),
        Triple("leetcode", arrayOf("leet","code","leetc","letcode","leetcode","leetcodes"), 5),
        Triple("abababab", arrayOf("aba","abab","bab","ab","ba","baba"), 6)
    )

    validateSolution(testCases, ::numMatchingSubseq)
}
