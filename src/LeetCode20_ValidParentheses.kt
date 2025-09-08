/**
 * 20: Valid Parentheses
 * Easy
 * https://leetcode.com/problems/valid-parentheses/
 *
 * You are given a string `s` containing just the characters '(', ')', '{', '}', '[' and ']'.
 * Determine if the input string is valid.
 *
 * An input string is valid if:
 *  1. Open brackets must be closed by the same type of brackets.
 *  2. Open brackets must be closed in the correct order.
 *
 * Example:
 * Input: s = "()"
 * Output: true
 *
 * Example:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Constraints:
 * - 1 <= s.length <= 10⁴
 * - s consists of parentheses only '()[]{}'.
 *
 * @param s the input string containing only '(', ')', '{', '}', '[' and ']'
 * @return Boolean true if the string is valid according to bracket matching rules, false otherwise
 */
private fun isValid(s: String): Boolean {
    val stack = ArrayDeque<Char>()

    for (c in s) {
        when (c) {
            '(','{','[' -> stack.addFirst(c)
            ')' -> if (stack.removeFirstOrNull() != '(') return false
            '}' -> if (stack.removeFirstOrNull() != '{') return false
            ']' -> if (stack.removeFirstOrNull() != '[') return false
        }
    }

    return stack.isEmpty()
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair("()", true),
        Pair("()[]{}", true),
        Pair("(]", false),
        Pair("([)]", false),
        Pair("{[]}", true),
        // Additional valid cases
        Pair("(([])){[()()]}", true),
        Pair("", true),  // though constraints say length ≥ 1, include for robustness
        Pair("(((((", false),
        Pair(")))))", false),
        Pair("([{}])", true),
        Pair("([{})", false)
    )

    validateSolution(testCases, ::isValid)
}
