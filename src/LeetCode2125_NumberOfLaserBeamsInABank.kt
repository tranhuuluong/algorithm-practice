/**
 * 2125: Number of Laser Beams in a Bank
 * Medium
 * https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
 *
 * Anti-theft security devices are activated inside a bank. You are given a 0-indexed binary string array `bank` representing the floor plan of the bank, which is an m × n 2D matrix. `bank[i]` represents the i-th row, consisting of '0's and '1's. '0' means the cell is empty, while '1' means the cell has a security device.
 *
 * There is one laser beam between any two security devices if both conditions are met:
 *   • The two devices are located on two different rows: r1 and r2, where r1 < r2.
 *   • For each row i where r1 < i < r2, there are no security devices in the i-th row.
 *
 * Laser beams are independent, i.e., one beam does not interfere nor join with another.
 *
 * Return the total number of laser beams in the bank.
 *
 * Example 1:
 * Input: bank = ["011001","000000","010100","001000"]
 * Output: 8
 * Explanation:
 * Between each of the following device pairs, there is one beam. In total, there are 8 beams:
 *   bank[0][1] -- bank[2][1]
 *   bank[0][1] -- bank[2][3]
 *   bank[0][2] -- bank[2][1]
 *   bank[0][2] -- bank[2][3]
 *   bank[0][5] -- bank[2][1]
 *   bank[0][5] -- bank[2][3]
 *   bank[2][1] -- bank[3][2]
 *   bank[2][3] -- bank[3][2]
 * Note that there is no beam between any device on the 0-th row with any on the 3-rd row. This is because the 2nd row contains security devices, which breaks the second condition. :contentReference[oaicite:0]{index=0}
 *
 * Example 2:
 * Input: bank = ["000","111","000"]
 * Output: 0
 * Explanation:
 * There does not exist two devices located on two different rows. :contentReference[oaicite:1]{index=1}
 *
 * Constraints:
 *   m == bank.length
 *   n == bank[i].length
 *   1 <= m, n <= 500
 *   bank[i][j] is either '0' or '1'. :contentReference[oaicite:2]{index=2}
 *
 * @param bank the binary string array representing rows of the bank
 * @return total number of laser beams in the bank
 */
private fun numberOfBeams(bank: Array<String>): Int {
    var cur = 0
    var result = 0
    for (row in bank) {
        val devices = row.count { it == '1' }
        if (devices > 0) {
            if (cur == 0) {
                cur = devices
            } else {
                result += devices * cur
                cur = devices
            }
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(arrayOf("011001","000000","010100","001000"), 8),    // Example 1
        Pair(arrayOf("000","111","000"), 0),                     // Example 2
        Pair(arrayOf("1","0","1"), 1),
        Pair(arrayOf("101","000","101"), 4),
        Pair(arrayOf("0001","0000","1000","0010"), 2),
        Pair(arrayOf("11","11"), 4),
        Pair(arrayOf("10","01","10","01"), 3),
        Pair(arrayOf("100","010","001"), 2),
        Pair(arrayOf("1110","0000","0111","0001"), 12),
        Pair(arrayOf("00000","00000","00001","10000","00000","01000"), 2)
    )

    validateSolution(testCases, ::numberOfBeams)
}
