import model.Quadruple
import java.util.LinkedList
import java.util.Queue

/**
 * 1625: Lexicographically Smallest String After Applying Operations
 * Medium
 * https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/
 *
 * You are given a string `s` of even length consisting of digits from 0 to 9, and two integers `a` and `b`.
 * You can apply either of the following two operations any number of times and in any order on `s`:
 *   • Add `a` to all digits at **odd indices** (0-based). Digits that exceed ‘9’ wrap around to ‘0’.
 *     For example, if `s = "3456"` and `a = 5`, then odd indices are 1 and 3: '4'→'9', '6'→'1', so result = `"3951"`. :contentReference[oaicite:0]{index=0}
 *   • Rotate `s` to the right by `b` positions. For example, if `s = "3456"` and `b = 1`, result = `"6345"`. :contentReference[oaicite:1]{index=1}
 * Return the lexicographically smallest string you can obtain by applying the above operations any number of times. :contentReference[oaicite:2]{index=2}
 *
 * Example 1:
 * Input: s = "5525", a = 9, b = 2
 * Output: "2050"
 * Explanation: Start = "5525" → rotate → "2555" → add → "2454" → add → "2353" → rotate → "5323" → add → "5222" → add → "5121" → rotate → "2151" → add → "2050". :contentReference[oaicite:3]{index=3}
 *
 * Example 2:
 * Input: s = "74", a = 5, b = 1
 * Output: "24" :contentReference[oaicite:4]{index=4}
 *
 * Constraints:
 * - 2 ≤ s.length ≤ 100
 * - s.length is even
 * - s consists of digits from '0' to '9' only
 * - 1 ≤ a ≤ 9
 * - 1 ≤ b ≤ s.length − 1 :contentReference[oaicite:5]{index=5}
 *
 * @param s the initial string of digits (even length)
 * @param a the amount to add to digits at odd indices (modulo 10)
 * @param b the number of positions to rotate right
 * @return String the lexicographically smallest string obtainable
 */
private fun findLexSmallestString(s: String, a: Int, b: Int): String {
    fun String.rotate(): String {
        return substring(b) + substring(0, b)
    }

    fun String.add(): String {
        val sb = StringBuilder(this)
        for (i in 1..lastIndex step 2) {
            var num = sb[i].digitToInt() + a
            if (num >= 10) num -= 10
            sb.setCharAt(i, num.digitToChar())
        }
        return sb.toString()
    }

    val visit = mutableSetOf<String>()
    val queue: Queue<String> = LinkedList()
    var min = s
    queue.offer(s)
    visit.add(s)

    while (!queue.isEmpty()) {
        val cur = queue.poll()
        if (cur < min) min = cur

        val rotated = cur.rotate()
        if (visit.add(rotated)) queue.offer(rotated)

        val added = cur.add()
        if (visit.add(added)) queue.offer(added)
    }

    return min
}

fun main() {
    val testCases = listOf(
        Quadruple("5525", 9, 2, "2050"),
        Quadruple("74", 5, 1, "24"),
        Quadruple("0011", 4, 2, "0011"),

        // Additional cases
        Quadruple("1234", 3, 2, "1032"),
        Quadruple("3456", 5, 1, "0189"),
        Quadruple("9090", 2, 2, "9090"),
        Quadruple("8372", 7, 3, "0011"),
        Quadruple("0000", 8, 1, "0000"),
        Quadruple("9182", 4, 2, "8099"),
        Quadruple("5678", 3, 3, "0022"),
    )

    validateSolution(testCases, ::findLexSmallestString)
}
