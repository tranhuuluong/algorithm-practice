/**
 * 70: Climbing Stairs
 * Easy
 * https://leetcode.com/problems/climbing-stairs/
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * Constraints:
 * 1 <= n <= 45
 *
 * @param n the number of steps in the staircase
 * @return the number of distinct ways to climb to the top
 */
private fun climbStairs(n: Int): Int {
    if (n <= 3) return n

    var prev1 = 3
    var prev2 = 2
    var cur = 0
    repeat(n - 3) {
        cur = prev1 + prev2
        prev2 = prev1
        prev1 = cur
    }
    return cur
}

fun main() {
    val testCases = listOf(
        Pair(2, 2),    // Example 1
        Pair(3, 3),    // Example 2
        Pair(1, 1),
        Pair(4, 5),
        Pair(5, 8),
        Pair(6, 13),
        Pair(10, 89),
        Pair(20, 10946),
        Pair(30, 1346269),
        Pair(45, 1836311903)
    )

    validateSolution(testCases, ::climbStairs)
}
