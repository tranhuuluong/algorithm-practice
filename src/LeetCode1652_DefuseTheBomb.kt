import kotlin.math.abs

/**
 * 1652: Defuse the Bomb
 * Easy
 * https://leetcode.com/problems/defuse-the-bomb/
 *
 * You have a bomb to defuse, and your time is running out! You are given a circular array `code` of length n and an integer key `k`.
 *
 * To decrypt the code, you must replace every number simultaneously based on the following rules:
 *   • If k > 0: Replace the i-th number with the sum of the next k numbers (wrapping around).
 *   • If k < 0: Replace the i-th number with the sum of the previous |k| numbers (wrapping around).
 *   • If k == 0: Replace the i-th number with 0.
 *
 * Return the decrypted array.
 *
 * Example 1:
 * Input: code = [5,7,1,4], k = 3
 * Output: [12,10,16,13]
 * Explanation: For each index i, sum the next 3 numbers (with wrap-around).
 *
 * Example 2:
 * Input: code = [1,2,3,4], k = 0
 * Output: [0,0,0,0]
 *
 * Example 3:
 * Input: code = [2,4,9,3], k = -2
 * Output: [12,5,6,13]
 * Explanation: For each index i, sum the previous 2 numbers (with wrap-around).
 *
 * Constraints:
 *   n == code.size
 *   1 <= n <= 100
 *   1 <= code[i] <= 100
 *   -(n - 1) <= k <= n - 1
 *
 * @param code the circular input array
 * @param k the key determining how many neighbors to sum (positive for next, negative for previous, zero for reset)
 * @return the decrypted array after applying the replacement to all elements simultaneously
 */
private fun decryptBruteForce(code: IntArray, k: Int): IntArray {
    val n = code.size
    val ans = IntArray(n)
    fun decryptPositive() : IntArray {
        for (i in 0 until n) {
            var sum = 0
            for (j in (i + 1)..(i + k)) {
                sum += code[j % n]
            }
            ans[i] = sum
        }
        return ans
    }
    fun decryptNegative(): IntArray {
        for (i in 0 until n) {
            var sum = 0
            for (j in i - 1 downTo i - abs(k)) {
                val index = if (j < 0) n + j else j
                sum += code[index]
            }
            ans[i] = sum
        }
        return ans
    }
    return when {
        k == 0 -> ans
        k > 0 -> decryptPositive()
        else -> decryptNegative()
    }
}

private fun decryptOptimized(code: IntArray, k: Int): IntArray {
    val n = code.size
    val ans = IntArray(n)
    if (k == 0) return ans

    var sum = 0
    var start: Int
    var end: Int
    if (k > 0) {
        start = 1
        end = k
    } else {
        start = n + k
        end = n - 1
    }
    for (i in start..end) sum += code[i]
    for (i in 0 until n) {
        ans[i] = sum
        sum -= code[(start) % n]
        sum += code[(end + 1) % n]
        start++
        end++
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(5,7,1,4), 3, intArrayOf(12,10,16,13)),     // Example 1
        Triple(intArrayOf(1,2,3,4), 0, intArrayOf(0,0,0,0)),         // Example 2
        Triple(intArrayOf(2,4,9,3), -2, intArrayOf(12,5,6,13)),      // Example 3
        Triple(intArrayOf(10), 0, intArrayOf(0)),                    // single element, k=0
        Triple(intArrayOf(1,1,1,1), 2, intArrayOf(2,2,2,2)),          // all same values
        Triple(intArrayOf(1,2,3,4,5), 4, intArrayOf(14,13,12,11,10)),  // wrap sum
        Triple(intArrayOf(1,2,3,4,5), -1, intArrayOf(5,1,2,3,4)),     // previous one
        Triple(intArrayOf(5,6,7,8,9), -3, intArrayOf(24,22,20,18,21)),// larger negative k
        Triple(intArrayOf(3,8,7,4,2), 4, intArrayOf(21, 16, 17, 20, 22))  // k equals list length
    )

    validateSolution(testCases, ::decryptBruteForce)
    printDivider()
    validateSolution(testCases, ::decryptOptimized)
}
