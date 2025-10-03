/**
 * 844: Backspace String Compare
 * Easy
 * https://leetcode.com/problems/backspace-string-compare/
 *
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 *
 * Example 1:
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 *
 * Example 2:
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 *
 * Example 3:
 * Input: s = "a#c", t = "b"
 * Output: false
 *
 * Constraints:
 * - 1 <= s.length, t.length <= 200
 * - s and t only contain lowercase letters and ‘#’ characters :contentReference[oaicite:0]{index=0}
 *
 * @param s first input string with backspaces
 * @param t second input string with backspaces
 * @return Boolean true if after processing backspaces they are equal, otherwise false
 */
private fun backspaceCompareStack(s: String, t: String): Boolean {
    fun processString(string: String): ArrayDeque<Char> {
        val stack = ArrayDeque<Char>()
        for (c in string) {
            when {
                c != '#' -> stack.addFirst(c)
                stack.isNotEmpty() -> stack.removeFirst()
            }
        }
        return stack
    }

    return processString(s) == processString(t)
}

private fun backspaceCompareStringBuilder(s: String, t: String): Boolean {
    fun processString(string: String) = buildString {
        var backspaceCount = 0
        for (i in string.lastIndex downTo 0) {
            if (string[i] == '#') {
                backspaceCount++
                continue
            }

            if (backspaceCount > 0) {
                backspaceCount--
                continue
            }

            append(string[i])
        }
    }

    return processString(s) == processString(t)
}

private fun backspaceCompareTwoPointer(s: String, t: String): Boolean {
    var i = s.lastIndex
    var j = t.lastIndex
    var backspaceCountS = 0
    var backspaceCountT = 0
    while (i >= 0 || j >= 0) {
        while (i >= 0) {
            when {
                s[i] == '#' -> {
                    backspaceCountS++
                    i--
                }
                backspaceCountS > 0 -> {
                    backspaceCountS--
                    i--
                }
                else -> break
            }
        }

        while (j >= 0) {
            when {
                t[j] == '#' -> {
                    backspaceCountT++
                    j--
                }
                backspaceCountT > 0 -> {
                    backspaceCountT--
                    j--
                }
                else -> break
            }
        }

        if (i >= 0 && j >= 0 && s[i] != t[j]) return false
        if (i >= 0 != j >= 0) return false

        i--
        j--
    }
    return true
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple("ab#c", "ad#c", true),
        Triple("ab##", "c#d#", true),
        Triple("a#c", "b", false),
        // Additional cases
        Triple("", "", true),
        Triple("#", "#", true),
        Triple("###", "###", true),
        Triple("bxj##tw", "bxj###tw", false),
        Triple("bxj##tw", "bxj#tw", false),
        Triple("nzp#o#g", "b#nzp#o#g", true),
        Triple("y#fo##f", "y#f#o##f", true),
        Triple("xywrrmp", "xywrrm#p", false)
    )

    validateSolution(testCases, ::backspaceCompareStack)
    printDivider()
    validateSolution(testCases, ::backspaceCompareStringBuilder)
    printDivider()
    validateSolution(testCases, ::backspaceCompareTwoPointer)
}
