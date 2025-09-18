import kotlin.math.min

/**
 * 541: Reverse String II
 * Easy
 * https://leetcode.com/problems/reverse-string-ii/
 *
 * Given a string s and an integer k, you need to reverse the first k characters for every 2k characters
 * counting from the start of the string. If there are fewer than k characters left, reverse all of them.
 * If there are at least k but fewer than 2k characters, then reverse the first k characters and leave
 * the others as original.
 *
 * Example 1:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 *
 * Example 2:
 * Input: s = "abcd", k = 2
 * Output: "bacd"
 *
 * Constraints:
 * - 1 <= s.length <= 10⁴ :contentReference[oaicite:0]{index=0}
 * - s consists of lowercase English letters. :contentReference[oaicite:1]{index=1}
 * - 1 <= k <= 10⁴ :contentReference[oaicite:2]{index=2}
 *
 * @param s the input string to be modified
 * @param k the number of characters to reverse in each 2k block
 * @return String the resulting string after applying the described reversals
 */
private fun reverseStr(s: String, k: Int): String {
    val result = s.toCharArray()
    for (i in s.indices step 2 * k) {
        reverse(result, i, min(s.lastIndex, i + k - 1))
    }

    return String(result)
}

private fun reverseStr2(s: String, k: Int): String {
    val result = s.toCharArray()
    var i = 0
    while (i <= s.lastIndex) {
        reverse(result, i, min(s.lastIndex, i + k - 1))
        i += 2 * k
    }

    return String(result)
}

private fun reverse(arr: CharArray, l: Int, r: Int) {
    var l = l
    var r = r
    while (l < r) {
        val temp = arr[l]
        arr[l] = arr[r]
        arr[r] = temp
        l++
        r--
    }
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple("abcdefg", 2, "bacdfeg"),
        Triple("abcd", 2, "bacd"),
        // Additional cases
        Triple("a", 1, "a"),
        Triple("abc", 2, "bac"),
        Triple("abc", 3, "cba"),
        Triple("abcdef", 3, "cbadef"),
        Triple("abcde", 4, "dcbae"),
        Triple("abcdefgh", 3, "cbadefhg"),
        Triple("abcdefghij", 3, "cbadefihgj"),
    )

    validateSolution(testCases, ::reverseStr)
    printDivider()
    validateSolution(testCases, ::reverseStr2)
}
