/**
 * 1351: Count Negative Numbers in a Sorted Matrix
 * Easy
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 *
 * Given an m x n matrix `grid` which is sorted in non-increasing order both row-wise and column-wise.
 *
 * Return the number of negative numbers in `grid`.
 *
 * Example 1:
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negative numbers in the matrix.
 *
 * Example 2:
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 *
 * @param grid A matrix sorted in non-increasing order both row-wise and column-wise.
 * @return The number of negative numbers in the matrix.
 */
private fun countNegatives(grid: Array<IntArray>): Int {
    val m = grid.size
    val n = grid[0].size
    var i = 0
    var j = m - 1
    var ans = 0
    while (i < n && j >= 0) {
        if (grid[j][i] < 0) {
            ans += n - i
            j--
        } else {
            i++
        }
    }
    return ans
}

fun main() {
    // Function takes 1 argument (grid), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(
            arrayOf(
                intArrayOf(4, 3, 2, -1),
                intArrayOf(3, 2, 1, -1),
                intArrayOf(1, 1, -1, -2),
                intArrayOf(-1, -1, -2, -3)
            ),
            8
        ),
        Pair(arrayOf(intArrayOf(3, 2), intArrayOf(1, 0)), 0),

        // Additional edge cases (min 10 total)
        // 1. Minimum size (1x1)
        Pair(arrayOf(intArrayOf(-5)), 1),
        Pair(arrayOf(intArrayOf(5)), 0),

        // 2. All negative
        Pair(arrayOf(intArrayOf(-1, -2), intArrayOf(-3, -4)), 4),

        // 3. All positive/zero
        Pair(arrayOf(intArrayOf(5, 4), intArrayOf(2, 1)), 0),

        // 4. Square matrix with negative corner
        Pair(arrayOf(intArrayOf(1, -1), intArrayOf(-1, -1)), 3),

        // 5. Rectangular matrix (2x3)
        Pair(arrayOf(intArrayOf(1, 0, -1), intArrayOf(0, -1, -2)), 3),

        // 6. Rectangular matrix (3x2)
        Pair(arrayOf(intArrayOf(5, 1), intArrayOf(1, -1), intArrayOf(-1, -2)), 3),

        // 7. All zeros (should be 0)
        Pair(arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0)), 0),

        // 8. Large matrix with a clear boundary
        Pair(
            arrayOf(
                intArrayOf(10, 8, 5, 0),
                intArrayOf(6, 4, 3, 0),
                intArrayOf(0, 0, 0, -5),
                intArrayOf(-1, -2, -3, -10)
            ),
            5
        )
    )

    validateSolution(testCases, ::countNegatives)
}