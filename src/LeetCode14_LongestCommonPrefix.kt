/**
 * 14: Longest Common Prefix
 * Easy
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 *
 * Constraints:
 *  - 0 <= strs.length <= 200
 *  - 0 <= strs[i].length <= 200
 *  - strs[i] consists of only lowercase English letters
 *
 * @param strs the array of strings
 * @return the longest common prefix among all strings
 */
private fun longestCommonPrefix(strs: Array<String>): String {
    if (strs.size == 1) return strs.first()

    var result = ""
    val str = strs[0]
    for (i in 0..str.length) {
        val prefix = str.substring(0, i)
        for (i in 1..strs.lastIndex) {
            if (!strs[i].startsWith(prefix)) {
                return result
            }
        }
        result = prefix
    }

    return result
}

private fun longestCommonPrefix2(strs: Array<String>): String {
    if (strs.size == 1) return strs.first()

    val str = strs[0]
    for (i in str.indices) {
        for (j in 1..strs.lastIndex) {
            if (strs[j].getOrNull(i) != str[i]) {
                return str.substring(0, i)
            }
        }
    }
    return str
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(arrayOf("flower", "flow", "flight"), "fl"),
        Pair(arrayOf("dog", "racecar", "car"), ""),
        Pair(arrayOf("single"), "single"),
        Pair(arrayOf("interspecies", "interstellar", "interstate"), "inters"),
        Pair(arrayOf("throne", "throne"), "throne"),
        Pair(arrayOf("prefix", "pre", "preface"), "pre"),
        Pair(arrayOf("a", "a", "b"), ""),
        Pair(arrayOf("cir", "car"), "c"),
        Pair(arrayOf("flow", "flower", "flight"), "fl")
    )

    validateSolution(testCases, ::longestCommonPrefix)
    printDivider()
    validateSolution(testCases, ::longestCommonPrefix2)
}
