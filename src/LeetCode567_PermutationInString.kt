/**
 * 567: Permutation in String
 * Medium
 * https://leetcode.com/problems/permutation-in-string/
 *
 * Given two strings s1 and s2, return true if s2 contains any permutation of s1. In other words, one of the first string’s permutations is a substring of the second string.
 *
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 * Constraints:
 * - 1 <= s1.length, s2.length <= 10,000 :contentReference[oaicite:0]{index=0}
 * - s1 and s2 consist of lowercase English letters. :contentReference[oaicite:1]{index=1}
 *
 * @param s1 the pattern string, whose permutation we seek as substring in s2
 * @param s2 the text string, in which to search for any permutation of s1
 * @return Boolean true if any permutation of s1 is a substring of s2, false otherwise
 */
private fun checkInclusion(s1: String, s2: String): Boolean {
    var l = 0
    var r = s1.lastIndex
    while (r <= s2.lastIndex) {
        val letterArray = IntArray(26)
        for (c in s1) {
            letterArray[c - 'a']++
        }
        for (c in s2.substring(l, r + 1)) {
            letterArray[c - 'a']--
        }
        if (letterArray.all { it == 0} ) return true
        l++
        r++
    }
    return false
}

private fun checkInclusionOptimized(s1: String, s2: String): Boolean {
    if (s1.length > s2.length) return false

    var matches = 0
    val s1Count = IntArray(26)
    val s2Count = IntArray(26)

    for (i in s1.indices) {
        s1Count[s1[i] - 'a']++
        s2Count[s2[i] - 'a']++
    }

    for (i in 0 until 26) {
        if (s1Count[i] == s2Count[i]) matches++
    }

    var l = 0
    for (r in s1.length..s2.lastIndex) {
        if (matches == 26) return true

        var index = s2[r] - 'a'
        s2Count[index]++
        when {
            s1Count[index] == s2Count[index] -> matches++
            s1Count[index] + 1 == s2Count[index] -> matches--
        }

        index = s2[l] - 'a'
        s2Count[index]--
        when {
            s1Count[index] == s2Count[index] -> matches++
            s1Count[index] - 1 == s2Count[index] -> matches--
        }
        l++
    }

    return matches == 26
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple("ab", "eidbaooo", true),
        Triple("ab", "eidboaoo", false),
        // Additional test case
        Triple("", "", true),
        Triple("a", "a", true),
        Triple("a", "b", false),
        Triple("abc", "eidbacooo", true),
        Triple("abc", "eidboacooo", false),
        Triple("abc", "eidbooo", false),
        Triple("abc", "ccccbbbbaaaa", false),
        Triple("aaa", "aaaaaa", true),
        Triple("abcd", "dcba", true),
        Triple("abcd", "abdcxabc", true),
    )

    validateSolution(testCases, ::checkInclusion)
    printDivider()
    validateSolution(testCases, ::checkInclusionOptimized)
}
