import kotlin.math.pow

/**
 * 476: Number Complement
 * Easy
 * https://leetcode.com/problems/number-complement/
 *
 * Given a positive integer num, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 *
 * Example 1:
 * Input: num = 5
 * Output: 2
 * Explanation: The binary representation of 5 is "101" (no leading zero bits), and its complement is "010". So you need to output 2.
 *
 * Example 2:
 * Input: num = 1
 * Output: 0
 * Explanation: The binary representation of 1 is "1". Flipping the single bit gives 0.
 *
 * Constraints:
 * 1 <= num < 2^31
 *
 * @param num the positive integer to convert to its bitwise complement
 * @return the complement integer obtained by flipping all bits of num’s binary representation up to its most significant bit
 */
private fun findComplement1(num: Int): Int {
    fun Int.flip(): String = buildString {
        var number = this@flip
        while (number > 0) {
            insert(0, number % 2 xor 1)
            number /= 2
        }
    }

    fun String.toDecimal(): Int {
        var result = 0.0
        for (i in indices) {
            if (this[i] == '1') {
                result += 2.0.pow(lastIndex - i)
            }
        }
        return result.toInt()
    }

    return num.flip().toDecimal()
}

private fun findComplement2(num: Int): Int {
    val bitLength = Integer.toBinaryString(num).length
    val mask = (1 shl bitLength) - 1
    return num xor mask
}

fun main() {
    val testCases = listOf(
        Pair(5, 2),   // Example 1
        Pair(1, 0),   // Example 2
        Pair(2, 1),   // 2 -> "10", flips to "01" = 1
        Pair(10, 5),  // 10 -> "1010", flips to "0101" = 5
        Pair(7, 0),   // 7 -> "111", flips to "000" = 0
        Pair(8, 7),   // 8 -> "1000", flips to "0111" = 7
        Pair(4, 3),   // 4 -> "100", flips to "011" = 3
        Pair(15, 0),  // 15 -> "1111", flips to "0000" = 0
        Pair(16, 15)  // 16 -> "10000", flips to "01111" = 15
    )
    validateSolution(testCases, ::findComplement1)
    printDivider()
    validateSolution(testCases, ::findComplement2)
}
