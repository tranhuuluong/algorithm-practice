/**
 * 271: Encode and Decode Strings
 * Medium
 * https://leetcode.com/problems/encode-and-decode-strings/
 *
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Example:
 * Input: ["Hello","World"]
 * Output: ["Hello","World"]
 *
 * Constraints:
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] contains any possible characters out of 256 valid ASCII characters.
 *
 * @param strs the list of strings to encode and decode
 * @return the list of original strings after decoding
 */
private fun encode(strs: List<String>): String {
    val sb = StringBuilder()
    for (s in strs) {
        sb.append(s.length).append("#").append(s)
    }
    return sb.toString()
}

private fun decode(s: String): List<String> {
    val res = mutableListOf<String>()
    var i = 0
    while (i < s.length) {
        var j = i
        while (s[j] != '#') {
            j++
        }
        val length = s.substring(i, j).toInt()
        res.add(s.substring(j + 1, j + 1 + length))
        i = j + 1 + length
    }
    return res
}

fun main() {
    val testCases: List<Pair<List<String>, List<String>>> = listOf(
        // Official example
        Pair(listOf("Hello", "World"), listOf("Hello", "World")),
        // Additional realistic test cases
        Pair(listOf(""), listOf("")),
        Pair(listOf("leet", "code", "love", "you"), listOf("leet", "code", "love", "you")),
        Pair(listOf("", "a", ""), listOf("", "a", "")),
        Pair(listOf("#$%", " ", "😊"), listOf("#$%", " ", "😊")),
        Pair(listOf("π", "∑", "—"), listOf("π", "∑", "—")),
        Pair(
            listOf("longer string with spaces", "tabs\tand\nnewlines"),
            listOf("longer string with spaces", "tabs\tand\nnewlines")
        ),
        Pair(listOf("delimiter#test", "#start", "end#"), listOf("delimiter#test", "#start", "end#")),
        Pair(listOf("123", "4567", "89"), listOf("123", "4567", "89")),
        Pair(listOf("a".repeat(200), ""), listOf("a".repeat(200), ""))
    )

    for ((input, expected) in testCases) {
        val encoded = encode(input)
        val decoded = decode(encoded)
        val passed = decoded == expected
        println(
            "input=$input -> decoded=$decoded, expected=$expected => ${if (passed) "✅ Passed" else "❌ Failed"}"
        )
    }
}
