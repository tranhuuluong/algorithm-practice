/**
 * 304: Range Sum Query 2D - Immutable
 * Medium
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 *
 * Design a NumMatrix class that is initialized with a 2D matrix and supports sumRegion queries.
 * sumRegion(row1, col1, row2, col2) returns the sum of the elements within the rectangle
 * defined by its upper-left corner (row1, col1) and lower-right corner (row2, col2).
 *
 * Example:
 * Input:
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
 * Output:
 * [null, 8, 11, 12]
 *
 * Constraints:
 *  - 1 <= m, n <= 200, where m = number of rows, n = number of columns
 *  - -10^4 <= matrix[i][j] <= 10^4
 *  - 0 <= row1 <= row2 < m
 *  - 0 <= col1 <= col2 < n
 *  - At most 10^4 calls to sumRegion will be made
 *
 * @param matrix the 2D integer matrix (immutable)
 * @return not applicable in constructor context, functionality provided via sumRegion method
 */
class NumMatrix(matrix: Array<IntArray>) {
    private val prefix: Array<IntArray>

    init {
        prefix = Array<IntArray>(matrix.size) {
            IntArray(matrix[0].size)
        }

        for (i in 0..matrix.lastIndex) {
            var sum = 0
            for (j in 0..matrix[i].lastIndex) {
                prefix[i][j] = matrix[i][j] + sum
                sum += matrix[i][j]
            }
        }
    }

    private val prefix2: Array<IntArray>
    init {
        prefix2 = Array<IntArray>(matrix.size + 1) {
            IntArray(matrix[0].size + 1)
        }

        for (i in 0..matrix.lastIndex) {
            for (j in 0..matrix[i].lastIndex) {
                prefix2[i + 1][j + 1] = matrix[i][j] + prefix2[i][j + 1] + prefix2[i + 1][j] - prefix2[i][j]
            }
        }
    }

    /**
     * Returns the sum of the submatrix defined by (row1, col1) as the upper-left corner
     * and (row2, col2) as the lower-right corner.
     *
     * @param row1 the starting row index (inclusive)
     * @param col1 the starting column index (inclusive)
     * @param row2 the ending row index (inclusive)
     * @param col2 the ending column index (inclusive)
     * @return the sum of all elements in the specified submatrix
     */
    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        var sum = 0
        for (i in row1..row2) {
            sum += prefix[i][col2] - if (col1 > 0) prefix[i][col1 - 1] else 0
        }
        return sum
    }

    fun sumRegion2(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        return prefix2[row2 + 1][col2 + 1] - prefix2[row1][col2 + 1] - prefix2[row2 + 1][col1] + prefix2[row1][col1]
    }
}

/**
 * Helper data class to represent a test case for NumMatrix.
 */
data class RegionQuery(
    val row1: Int,
    val col1: Int,
    val row2: Int,
    val col2: Int,
    val expected: Int
)

fun main() {
    val matrix = arrayOf(
        intArrayOf(3, 0, 1, 4, 2),
        intArrayOf(5, 6, 3, 2, 1),
        intArrayOf(1, 2, 0, 1, 5),
        intArrayOf(4, 1, 0, 1, 7),
        intArrayOf(1, 0, 3, 0, 5)
    )
    val numMatrix = NumMatrix(matrix)

    val testCases = listOf(
        // Official examples
        RegionQuery(2, 1, 4, 3, 8),
        RegionQuery(1, 1, 2, 2, 11),
        RegionQuery(1, 2, 2, 4, 12),
        // Additional realistic test cases
        RegionQuery(0, 0, 4, 4, 58), // full matrix sum
        RegionQuery(0, 0, 0, 0, 3),  // single element top-left
        RegionQuery(4, 4, 4, 4, 5),  // single element bottom-right
        RegionQuery(1, 2, 3, 3, 7),  // small submatrix in middle
        RegionQuery(3, 0, 4, 0, 5),  // first column bottom region
        RegionQuery(0, 3, 2, 4, 15), // right region upper rows
        RegionQuery(2, 2, 4, 2, 3)   // vertical strip in middle
    )

    for (tc in testCases) {
        val result = numMatrix.sumRegion(tc.row1, tc.col1, tc.row2, tc.col2)
        val expected = tc.expected
        println("sumRegion(${tc.row1}, ${tc.col1}, ${tc.row2}, ${tc.col2})) Expected:$expected, Actual:$result ${if (expected == result) "✅ Passed" else "❌ Failed"}")
    }
    printDivider()
    for (tc in testCases) {
        val result = numMatrix.sumRegion2(tc.row1, tc.col1, tc.row2, tc.col2)
        val expected = tc.expected
        println("sumRegion2(${tc.row1}, ${tc.col1}, ${tc.row2}, ${tc.col2})) Expected:$expected, Actual:$result ${if (expected == result) "✅ Passed" else "❌ Failed"}")
    }
}
