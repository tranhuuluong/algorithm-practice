import kotlin.math.sqrt

/**
 * 1925: Count Square Sum Triples
 * Easy
 * https://leetcode.com/problems/count-square-sum-triples/
 *
 * A square triple (a, b, c) is a triple where a, b, and c are integers and a^2 + b^2 = c^2.
 *
 * Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.
 *
 * Example 1:
 * Input: n = 5
 * Output: 2
 * Explanation: The square triples are (3,4,5) and (4,3,5).
 *
 * Example 2:
 * Input: n = 10
 * Output: 4
 * Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
 *
 * Constraints:
 * 1 <= n <= 250
 *
 * @param n the inclusive upper bound for a, b, and c
 * @return the number of ordered square triples (a, b, c) with 1 <= a,b,c <= n and a^2 + b^2 = c^2
 */
private fun countTriples1(n: Int): Int {
    var ans = 0
    for (i in 0..n) {
        for (j in 0..n) {
            if (i == j) continue
            val sqrt = sqrt(i * i + j * j.toFloat())
            val sqrtAsInt = sqrt.toInt()
            if (sqrt % 1 == 0f && sqrtAsInt != i && sqrtAsInt != j && sqrtAsInt <= n) {
                ans++
            }
        }
    }
    return ans
}

private fun countTriples2(n: Int): Int {
    fun sqrt(num: Int): Int {
        var l = 1
        var r = n
        while (l <= r) {
            val mid = (r + l) / 2
            val square = mid * mid
            when {
                square == num -> return mid
                square > num -> r = mid - 1
                else -> l = mid + 1
            }
        }
        return -1
    }

    var ans = 0
    for (i in 0..n) {
        for (j in 0..n) {
            if (i == j) continue
            val sqrt = sqrt(i * i + j * j)
            if (sqrt != -1 && sqrt != i && sqrt != j && sqrt <= n) {
                ans++
            }
        }
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Pair(1, 0),
        Pair(2, 0),
        Pair(3, 0),
        Pair(4, 0),
        Pair(5, 2),
        Pair(6, 2),
        Pair(7, 2),
        Pair(8, 2),
        Pair(9, 2),
        Pair(10, 4),
        Pair(12, 4),
        Pair(13, 6),
        Pair(15, 8)
    )

    validateSolution(testCases, ::countTriples1)
    printDivider()
    validateSolution(testCases, ::countTriples2)
}
