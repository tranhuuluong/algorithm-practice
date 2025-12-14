import kotlin.math.max
import kotlin.math.min

/**
 * 3531: Count Covered Buildings
 * Medium
 * https://leetcode.com/problems/count-covered-buildings/
 *
 * You are given an integer n representing an n x n grid city and an array buildings
 * where each buildings[i] = [x, y] gives the coordinates of a building.
 *
 * A building at (x, y) is considered covered if all four of the following exist:
 *   • A building directly left  → same y, smaller x
 *   • A building directly right → same y, larger x
 *   • A building directly below → same x, smaller y
 *   • A building directly above → same x, larger y
 *
 * Return the number of covered buildings.
 *
 * Example 1:
 * n = 3
 * buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
 * Output = 1
 *
 * Example 2:
 * n = 3
 * buildings = [[1,1],[1,2],[2,1],[2,2]]
 * Output = 0
 *
 * Constraints:
 * 2 <= n <= 100000
 * 1 <= buildings.length <= 100000
 * buildings[i] has 2 integers: x and y
 * All coordinates are unique
 *
 * @param n grid size
 * @param buildings array of coordinates [x, y]
 * @return number of covered buildings
 */
private fun countCoveredBuildings1(n: Int, buildings: Array<IntArray>): Int {
    val leftPass = Array(n + 1) { IntArray(n + 1) }
    val rightPass = Array(n + 1) { IntArray(n + 1) }
    val topPass = Array(n + 1) { IntArray(n + 1) }
    val bottomPass = Array(n + 1) { IntArray(n + 1) }
    val buildingPass = Array(n + 1) { IntArray(n + 1) }
    for ((row, col) in buildings) {
        buildingPass[row][col]++
    }
    for (row in 1..n) {
        var count = 0
        for (col in 1..n) {
            rightPass[row][col] = count
            count += buildingPass[row][col]
        }
    }

    for (row in 1..n) {
        var count = 0
        for (col in n downTo 1) {
            leftPass[row][col] = count
            count += buildingPass[row][col]
        }
    }

    for (col in 1..n) {
        var count = 0
        for (row in 1..n) {
            topPass[row][col] = count
            count += buildingPass[row][col]
        }
    }

    for (col in 1..n) {
        var count = 0
        for (row in n downTo 1) {
            bottomPass[row][col] = count
            count += buildingPass[row][col]
        }
    }

    var ans = 0
    for ((row, col) in buildings) {
        if (topPass[row][col] > 0 && bottomPass[row][col] > 0 && leftPass[row][col] > 0 && rightPass[row][col] > 0) {
            ans++
        }
    }
    return ans
}

private fun countCoveredBuildings2(n: Int, buildings: Array<IntArray>): Int {
    val minRow = IntArray(n + 1) { n + 1 }
    val maxRow = IntArray(n + 1)
    val minCol = IntArray(n + 1) { n + 1 }
    val maxCol = IntArray(n + 1)
    for ((row, col) in buildings) {
        minRow[col] = min(minRow[col], row)
        maxRow[col] = max(maxRow[col], row)
        minCol[row] = min(minCol[row], col)
        maxCol[row] = max(maxCol[row], col)
    }

    var ans = 0
    for ((row, col) in buildings) {
        if (row > minRow[col] && row < maxRow[col] && col > minCol[row] && col < maxCol[row]) {
            ans++
        }
    }
    return ans
}

fun main() {
    val testCases = listOf(
        // Example 1 (from problem)
        Triple(
            3,
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 2),
                intArrayOf(3, 2),
                intArrayOf(2, 1),
                intArrayOf(2, 3)
            ),
            1 // only (2,2)
        ),

        // Example 2
        Triple(
            3,
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 1),
                intArrayOf(2, 2)
            ),
            0
        ),

        // A plus shape centered at (3,3)
        Triple(
            5,
            arrayOf(
                intArrayOf(3, 3),
                intArrayOf(2, 3),
                intArrayOf(4, 3),
                intArrayOf(3, 2),
                intArrayOf(3, 4)
            ),
            1
        ),

        // Two plus shapes, both covered
        Triple(
            7,
            arrayOf(
                intArrayOf(3, 3), intArrayOf(2, 3), intArrayOf(4, 3), intArrayOf(3, 2), intArrayOf(3, 4),
                intArrayOf(5, 5), intArrayOf(4, 5), intArrayOf(6, 5), intArrayOf(5, 4), intArrayOf(5, 6)
            ),
            2
        ),

        // A line — no one is covered
        Triple(
            4,
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 2),
                intArrayOf(3, 2),
                intArrayOf(4, 2)
            ),
            0
        ),

        // Square ring, only the center is covered
        Triple(
            5,
            arrayOf(
                intArrayOf(2, 2),
                intArrayOf(2, 3),
                intArrayOf(2, 4),
                intArrayOf(3, 2),
                intArrayOf(3, 4),
                intArrayOf(4, 2),
                intArrayOf(4, 3),
                intArrayOf(4, 4),
                intArrayOf(3, 3) // center
            ),
            1
        ),

        // No building has all 4 neighbors
        Triple(
            6,
            arrayOf(
                intArrayOf(1, 1),
                intArrayOf(2, 2),
                intArrayOf(3, 3),
                intArrayOf(4, 4)
            ),
            0
        ),

        // Several candidates but only one has complete neighbors
        Triple(
            6,
            arrayOf(
                intArrayOf(3, 3),
                intArrayOf(3, 4),
                intArrayOf(3, 2),
                intArrayOf(2, 3),
                intArrayOf(4, 3),
                intArrayOf(2, 2),
                intArrayOf(4, 4)
            ),
            1 // only (3,3)
        ),

        // Large n but sparse — no coverage
        Triple(
            10,
            arrayOf(
                intArrayOf(2, 5),
                intArrayOf(7, 8),
                intArrayOf(9, 1),
                intArrayOf(4, 9)
            ),
            0
        ),

        // Two buildings meet left/right but missing up/down
        Triple(
            3,
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 2),
                intArrayOf(3, 2),
                intArrayOf(2, 3)
            ),
            0
        )
    )

    validateSolution(testCases, ::countCoveredBuildings1)
    printDivider()
    validateSolution(testCases, ::countCoveredBuildings2)
}
