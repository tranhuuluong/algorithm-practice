/**
 * 840: Magic Squares In Grid
 * Medium
 * https://leetcode.com/problems/magic-squares-in-grid/
 *
 * A 3 x 3 magic square is a 3 x 3 grid of distinct numbers from 1 to 9 such that each row, each column, and both diagonals all add up to the same sum.
 *
 * Given a `grid` of integers, find the number of 3 x 3 magic square subgrids (contiguous 3 x 3 square blocks) present in the grid.
 *
 * Example 1:
 * Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
 * Output: 1
 * Explanation: The first 3 x 3 subgrid is:
 * 4 3 8
 * 9 5 1
 * 2 7 6
 * This is a magic square since:
 * - Row sums: 4+3+8=15, 9+5+1=15, 2+7+6=15
 * - Column sums: 4+9+2=15, 3+5+7=15, 8+1+6=15
 * - Diagonal sums: 4+5+6=15, 8+5+2=15
 * The second 3 x 3 subgrid is:
 * 3 8 4
 * 5 1 9
 * 7 6 2
 * This is NOT a magic square. Diagonal sums: 3+1+2=6, which is not equal to 15.
 *
 * Example 2:
 * Input: grid = [[8]]
 * Output: 0
 *
 * Constraints:
 * R = grid.length
 * C = grid[0].length
 * 1 <= R, C <= 10
 * 0 <= grid[i][j] <= 15
 *
 * @param grid The input grid of integers.
 * @return The number of 3 x 3 magic square subgrids.
 */
private fun numMagicSquaresInside(grid: Array<IntArray>): Int {
    fun isValid(row: Int, col: Int): Boolean {
        val seen = mutableSetOf<Int>()
        for (i in row..row + 2) {
            for (j in col..col + 2) {
                val num = grid[i][j]
                if (num !in 1..9 || !seen.add(num)) {
                    return false
                }
            }
        }

        val row1 = grid[row][col] + grid[row][col + 1] + grid[row][col + 2]
        val row2 = grid[row + 1][col] + grid[row + 1][col + 1] + grid[row + 1][col + 2]
        val row3 = grid[row + 2][col] + grid[row + 2][col + 1] + grid[row + 2][col + 2]
        val col1 = grid[row][col] + grid[row + 1][col] + grid[row + 2][col]
        val col2 = grid[row][col + 1] + grid[row + 1][col + 1] + grid[row + 2][col + 1]
        val col3 = grid[row][col + 2] + grid[row + 1][col + 2] + grid[row + 2][col + 2]
        val diagonal1 = grid[row][col] + grid[row + 1][col + 1] + grid[row + 2][col + 2]
        val diagonal2 = grid[row][col + 2] + grid[row + 1][col + 1] + grid[row + 2][col]
        return row1 == row2 && row2 == row3 && col1 == col2 && col2 == col3 && diagonal1 == diagonal2
    }

    var ans = 0
    for (i in 0..grid.size - 3) {
        for (j in 0..grid[i].size - 3) {
            if (isValid(i, j)) ans++
        }
    }
    return ans
}

fun main() {
    // Function takes 1 argument (grid), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official example 1
        Pair(
            arrayOf(
                intArrayOf(4, 3, 8, 4),
                intArrayOf(9, 5, 1, 9),
                intArrayOf(2, 7, 6, 2)
            ),
            1
        ),
        // Official example 2
        Pair(arrayOf(intArrayOf(8)), 0),

        // Additional edge cases (min 10 total)
        // 1. Grid exactly 3x3, is a magic square
        Pair(
            arrayOf(
                intArrayOf(4, 3, 8),
                intArrayOf(9, 5, 1),
                intArrayOf(2, 7, 6)
            ),
            1
        ),

        // 2. Grid exactly 3x3, not a magic square (duplicate numbers)
        Pair(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)
            ),
            0
        ),

        // 3. Grid exactly 3x3, not a magic square (wrong sum)
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            0
        ),

        // 4. Large grid with no magic squares
        Pair(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12),
                intArrayOf(13, 14, 15, 16)
            ),
            0
        ),

        // 5. Minimal grid size (1x1 or 2x2), should return 0
        Pair(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4)
            ),
            0
        ),

        // 6. Grid contains multiple non-overlapping magic squares
        Pair(
            arrayOf(
                intArrayOf(4, 3, 8, 1, 1),
                intArrayOf(9, 5, 1, 1, 1),
                intArrayOf(2, 7, 6, 1, 1),
                intArrayOf(1, 1, 1, 1, 1)
            ),
            1
        ),

        // 7. Grid contains two overlapping magic squares (Example: LC discussion)
        Pair(
            arrayOf(
                intArrayOf(10, 3, 5),
                intArrayOf(1, 10, 1),
                intArrayOf(2, 7, 6)
            ),
            0 // Numbers are outside 1-9 range or not distinct
        ),

        // 8. Test the required number range (must be 1-9)
        Pair(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 5, 1),
                intArrayOf(1, 1, 1)
            ),
            0 // Missing numbers 2, 3, 4, 6, 7, 8, 9
        )
    )

    validateSolution(testCases, ::numMagicSquaresInside)
}