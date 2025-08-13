/**
 * 2133: Check if Every Row and Column Contains All Numbers
 * Easy
 * https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/
 *
 * Problem (paraphrased from the official statement):
 * An n x n matrix is considered valid if every row and every column contains all the integers from 1 to n (inclusive).
 * Given an n x n integer matrix `matrix`, return true if the matrix is valid; otherwise, return false.
 *
 * Example 1 (from the statement):
 * Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
 * Output: true
 * Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
 * Hence, we return true.
 *
 * Example 2 (from the statement):
 * Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
 * Output: false
 * Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
 * Hence, we return false.
 *
 * Constraints (from the statement):
 * - n == matrix.length == matrix[i].length
 * - 1 <= n <= 100
 * - 1 <= matrix[i][j] <= n
 *
 * @param matrix the square matrix of size n x n with entries in the range [1, n]
 * @return true if every row and column contains all numbers from 1 to n; otherwise, false
 */

// 00 01 02 03 04 05
// 10 11 12 13 14 15
// 20 21 22 23 24 25
// 30 31 32 33 34 35
// 40 41 42 43 44 45
// 50 51 52 53 54 55

private fun checkValid(matrix: Array<IntArray>): Boolean {
    val n = matrix.size
    val rows = Array(n) { BooleanArray(n) }
    val columns = Array(n) { BooleanArray(n) }
    for (i in 0..matrix.lastIndex) {
        for (j in 0..matrix[i].lastIndex) {
            val num = matrix[i][j] - 1
            if (rows[i][num]) return false
            rows[i][num] = true

            if (columns[j][num]) return false
            columns[j][num] = true
        }
    }
    return true
}

fun main() {
    // Test cases as Pair<input, expected>, including the official examples.
    val testCases: List<Pair<Array<IntArray>, Boolean>> = listOf(
        // Official Example 1: true
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(3, 1, 2),
                intArrayOf(2, 3, 1)
            ),
            true
        ),
        // Official Example 2: false
        Pair(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 2, 3),
                intArrayOf(1, 2, 3)
            ),
            false
        ),
        // n = 1, valid
        Pair(
            arrayOf(
                intArrayOf(1)
            ),
            true
        ),
        // n = 2, valid Latin square
        Pair(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 1)
            ),
            true
        ),
        // n = 2, invalid (row duplicates)
        Pair(
            arrayOf(
                intArrayOf(2, 2),
                intArrayOf(1, 1)
            ),
            false
        ),
        // n = 3, invalid (column duplicates)
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(1, 3, 2),
                intArrayOf(1, 2, 3)
            ),
            false
        ),
        // n = 3, invalid (row missing some numbers)
        Pair(
            arrayOf(
                intArrayOf(2, 2, 2),
                intArrayOf(1, 3, 2),
                intArrayOf(3, 1, 2)
            ),
            false
        ),
        // n = 4, valid Latin square
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 3, 4, 1),
                intArrayOf(3, 4, 1, 2),
                intArrayOf(4, 1, 2, 3)
            ),
            true
        ),
        // n = 4, invalid (column has duplicate 4 and missing 3)
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(2, 3, 4, 1),
                intArrayOf(3, 1, 2, 4),
                intArrayOf(4, 4, 1, 2)
            ),
            false
        ),
        // n = 5, valid shifted rows
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(2, 3, 4, 5, 1),
                intArrayOf(3, 4, 5, 1, 2),
                intArrayOf(4, 5, 1, 2, 3),
                intArrayOf(5, 1, 2, 3, 4)
            ),
            true
        ),
        // n = 5, invalid (last row duplicates 5, missing 4 in last column)
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(2, 3, 4, 5, 1),
                intArrayOf(3, 4, 5, 1, 2),
                intArrayOf(4, 5, 1, 2, 3),
                intArrayOf(5, 1, 2, 3, 5)
            ),
            false
        )
    )

    validateSolution(testCases, ::checkValid)
}
