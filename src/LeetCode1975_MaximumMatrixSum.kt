import kotlin.math.abs
import kotlin.math.min

/**
 * 1975: Maximum Matrix Sum
 * Medium
 * https://leetcode.com/problems/maximum-matrix-sum/
 *
 * You are given an n x n integer matrix. You can do the following operation any number of times:
 * - Choose any two adjacent elements of matrix and multiply each of them by -1.
 *
 * Adjacent elements share a common edge (not just a corner).
 *
 * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements.
 *
 * Insight:
 * Since we can flip the signs of any adjacent pair, we can effectively move a negative sign from one cell to any adjacent cell.
 * By chaining these moves, we can pair up any two negative numbers in the matrix and flip them both to positive.
 * - If there is an even number of negative signs, we can eliminate all of them.
 * - If there is an odd number of negative signs, one negative sign will remain. To maximize the sum, we should ensure this remaining negative sign is assigned to the element with the smallest absolute value in the entire matrix.
 *
 * Example 1:
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 *
 * Example 2:
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can multiply the last 2 elements in the second row by -1.
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 *
 * @param matrix The n x n integer matrix.
 * @return The maximum sum of the matrix's elements.
 */
private fun maxMatrixSum(matrix: Array<IntArray>): Long {
    var sum = 0L
    var minAbs = Int.MAX_VALUE
    var negativeCount = 0
    for (row in matrix) {
        for (num in row) {
            if (num < 0) negativeCount++
            val abs = abs(num)
            sum += abs
            minAbs = min(minAbs, abs)
        }
    }
    if (negativeCount % 2 != 0) {
        sum -= 2 * minAbs
    }
    return sum
}

fun main() {
    // Function takes 1 argument (matrix), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(
            arrayOf(
                intArrayOf(1, -1),
                intArrayOf(-1, 1)
            ),
            4L
        ),
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(-1, -2, -3),
                intArrayOf(1, 2, 3)
            ),
            16L
        ),

        // Additional edge cases
        // 1. All positive
        Pair(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            ),
            10L
        ),

        // 2. All negative (even count: 4 negatives -> flip all to positive)
        Pair(
            arrayOf(
                intArrayOf(-1, -2),
                intArrayOf(-3, -4)
            ),
            10L
        ),

        // 3. All negative (odd count: 9 negatives -> 1 remains negative, smallest abs is 1)
        // Sum of abs: 1+2+3+4+5+6+7+8+9 = 45.
        // Result: 45 - 2*1 = 43.
        Pair(
            arrayOf(
                intArrayOf(-1, -2, -3),
                intArrayOf(-4, -5, -6),
                intArrayOf(-7, -8, -9)
            ),
            43L
        ),

        // 4. One zero exists (counts as even or odd adjuster, effectively absorbing the negative sign)
        // Negatives: 1 (odd). Smallest abs is 0. Result: sum of abs.
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(-5, 0, 10),
                intArrayOf(2, 2, 2)
            ),
            27L // 1+2+3+5+0+10+2+2+2
        ),

        // 5. Smallest element is positive, but total negatives is odd
        // Negatives: -5 (1 count). Smallest abs in matrix is 1.
        // Sum abs: 10 + 5 + 1 + 20 = 36.
        // Result: 36 - 2*1 = 34.
        Pair(
            arrayOf(
                intArrayOf(10, -5),
                intArrayOf(1, 20)
            ),
            34L
        ),

        // 6. Large values
        Pair(
            arrayOf(
                intArrayOf(100000, -100000),
                intArrayOf(-100000, -100000)
            ),
            200000L // 3 negatives (odd). Min abs 100000. Sum abs 400000. Res 200000. Wait:
            // Abs sum: 400,000. Odd negatives (3). Smallest abs: 100,000.
            // Subtract 2 * 100,000 -> 200,000.
        )
    )

    validateSolution(testCases, ::maxMatrixSum)
}