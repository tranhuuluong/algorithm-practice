/**
 * 17: Letter Combinations of a Phone Number
 * Medium
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent. Return the answer in any order.
 *
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Example 2:
 * Input: digits = ""
 * Output: []
 *
 * Constraints:
 *  - 0 <= digits.length <= 4
 *  - digits[i] is a digit in the range ['2', '9'].
 *
 * @param digits the string of digits from 2 to 9
 * @return a list of all possible letter combinations the number could represent
 */
private fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()) return emptyList()

    val list = mutableListOf<CharArray>()

    for (c in digits) {
        val digit = c.digitToInt()
        var startChar = 'a' + (digit - 2) * 3

        if (digit > 7) {
            startChar += 1
        }
        if (digit == 7 || digit == 9) {
            list.add(charArrayOf(startChar, startChar + 1, startChar + 2, startChar + 3))
        } else {
            list.add(charArrayOf(startChar, startChar + 1, startChar + 2))
        }
    }

    var result = listOf("")
    for (charArray in list) {
        result = result.flatMap { current ->
            charArray.map { "$current$it" }
        }
    }

    return result
}

// 2 = [a,b,c] 'a' - 2 + 2
// 3 = [d,e,f] 'a' + (3 - 2) * 3
// 4 = [g,h,i] 'a' + (4 -2) * 3
fun main() {
    val testCases = listOf(
        // Official examples
        Pair("", emptyList<String>()),
        Pair("23", listOf("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf")),
        // Additional realistic test cases
        Pair("2", listOf("a", "b", "c")),
        Pair("7", listOf("p", "q", "r", "s")),
        Pair(
            "79", listOf(
                "pw", "px", "py", "pz",
                "qw", "qx", "qy", "qz",
                "rw", "rx", "ry", "rz",
                "sw", "sx", "sy", "sz"
            )
        ),
        Pair("9", listOf("w", "x", "y", "z")),
        Pair("8", listOf("t", "u", "v")),
        Pair(
            "29", listOf(
                "aw", "ax", "ay", "az",
                "bw", "bx", "by", "bz",
                "cw", "cx", "cy", "cz"
            )
        ),
        Pair(
            "58", listOf(
                "jt", "ju", "jv",
                "kt", "ku", "kv",
                "lt", "lu", "lv"
            )
        )
    )

    validateSolution(testCases, ::letterCombinations)
}
