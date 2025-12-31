/**
 * 717: 1-bit and 2-bit Characters
 * Easy
 * https://leetcode.com/problems/1-bit-and-2-bit-characters/
 *
 * We have two special characters:
 * 1. The first character can be represented by one bit '0'.
 * 2. The second character can be represented by two bits '10' or '11'.
 *
 * Given a binary array `bits` that ends with '0', return true if the last character must be a one-bit character.
 *
 * The input array will always be decodable.
 *
 * Example 1:
 * Input: bits = [1, 0, 0]
 * Output: true
 * Explanation: The only way to decode it is "10", "0". The last character is '0' which is a one-bit character.
 *
 * Example 2:
 * Input: bits = [1, 1, 1, 0]
 * Output: false
 * Explanation: The only way to decode it is "11", "10". The last character is '10' which is a two-bit character.
 *
 * Constraints:
 * 1 <= bits.length <= 1000
 * bits[i] is always 0 or 1.
 *
 * @param bits The binary array representing the encoded characters.
 * @return True if the last character must be a one-bit character, false otherwise.
 */
private fun isOneBitCharacter1(bits: IntArray): Boolean {
    var unDecodedBit = -1
    for (i in 0 until bits.size - 1) {
        if (bits[i] == 0) {
            unDecodedBit = -1
        } else {
            if (unDecodedBit == 1) {
                unDecodedBit = -1
            } else {
                unDecodedBit = bits[i]
            }
        }
    }
    return unDecodedBit == -1
}

private fun isOneBitCharacter2(bits: IntArray): Boolean {
    var i = 0
    while (i < bits.size - 1) {
        i += if (bits[i] == 0) 1 else 2
    }
    return i == bits.size - 1
}

fun main() {
    // Function takes 1 argument (bits), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 0, 0), true),
        Pair(intArrayOf(1, 1, 1, 0), false),

        // Additional edge cases (min 10 total)
        // 1. Minimal case, 1-bit character
        Pair(intArrayOf(0), true),

        // 2. Minimal case, 2-bit character (not possible since it must end in 0)
        // The problem guarantees the input array ends with '0'.
        // Test case: ends with 0, last is 2-bit
        Pair(intArrayOf(1, 0), false), // Decoded as "10"

        // 3. Simple sequence, last is 1-bit
        Pair(intArrayOf(0, 0, 0), true), // Decoded as "0", "0", "0"

        // 4. Long sequence of 2-bit characters, last is 1-bit
        Pair(intArrayOf(1, 1, 0, 1, 0, 0), true), // Decoded as "11", "0", "10", "0"

        // 5. Long sequence, last is 2-bit
        Pair(intArrayOf(1, 1, 0, 1, 0, 1, 0), false), // Decoded as "11", "0", "10", "10"

        // 6. Only 2-bit characters
        Pair(intArrayOf(1, 0, 1, 0, 1, 0), false), // Decoded as "10", "10", "10"

        // 7. Last character is 1-bit, preceded by 2-bit
        Pair(intArrayOf(1, 1, 0), true), // Decoded as "11", "0"

        // 8. Test case with leading 0s
        Pair(intArrayOf(0, 1, 1, 0), true), // Decoded as "0", "11", "0"

        // 9. Sequence with multiple 1s before the final 0
        Pair(intArrayOf(1, 1, 1, 1, 0), true), // Decoded as "11", "11", "0" (or "11", "10", "10", etc. which is wrong)
        // Correct decoding of [1, 1, 1, 1, 0]:
        // Start at 0: '11', remaining: [1, 1, 0]
        // Start at 2: '11', remaining: [0]
        // Start at 4: '0'. Result: "11", "11", "0". Last is 1-bit.
        Pair(intArrayOf(1, 1, 1, 1, 0), true)
    )

    validateSolution(testCases, ::isOneBitCharacter1)
    printDivider()
    validateSolution(testCases, ::isOneBitCharacter2)
}