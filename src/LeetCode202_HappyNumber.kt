/**
 * 202: Happy Number
 * Easy
 * https://leetcode.com/problems/happy-number/
 *
 * Write an algorithm to determine if a number `n` is happy.
 *
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Return `true` if `n` is a happy number, and `false` if not. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: n = 19
 * Output: true :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: n = 2
 * Output: false :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 * - 1 ≤ n ≤ 2^31 - 1 :contentReference[oaicite:3]{index=3}
 *
 * @param n the positive integer to check for “happy-ness”
 * @return Boolean true if n is a happy number, false otherwise
 */
private fun isHappy1(n: Int): Boolean {
    val set = mutableSetOf<Int>()
    var number = calculate(n)
    while (number != 1) {
        if (!set.add(number)) return false
        number = calculate(number)
    }
    return true
}

private fun isHappy2(n: Int): Boolean {
    var slow = calculate(n)
    var fast = calculate(calculate(n))
    while (fast != 1) {
        if (slow == fast) return false
        slow = calculate(slow)
        fast = calculate(calculate(fast))
    }
    return true
}

private fun calculate(n: Int): Int {
    var number = n
    var sum = 0
    while (number >= 10) {
        val digit = number % 10
        sum += digit * digit
        number /= 10
    }
    return sum + number * number
}

fun main() {
    val testCases = listOf(
        Pair(19, true),
        Pair(2, false),
        // Additional cases
        Pair(1, true),
        Pair(7, true),
        Pair(116, false),
        Pair(100, true),
        Pair(111, false),
        Pair(4, false),
        Pair(82, true),
        Pair(10, true),
        Pair(89, false)
    )

    validateSolution(testCases, ::isHappy1)
    printDivider()
    validateSolution(testCases, ::isHappy2)
}
