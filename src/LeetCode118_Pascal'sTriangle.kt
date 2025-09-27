/**
 * 118: Pascal's Triangle
 * Easy
 * https://leetcode.com/problems/pascals-triangle/
 *
 * Given an integer numRows, return *the first numRows of Pascal’s triangle*.
 * In Pascal’s triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Constraints:
 * - 1 <= numRows <= 30 :contentReference[oaicite:0]{index=0}
 *
 * @param numRows the number of rows of Pascal’s triangle to generate
 * @return List<List<Int>> representing the first numRows of Pascal’s triangle
 */
private fun generate(numRows: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    repeat(numRows) { i ->
        val row = mutableListOf<Int>()
        for (j in 0..i) {
            if (j == 0 || j == i) {
                row.add(1)
                continue
            }

            val previousRow = result[i - 1]
            row.add(previousRow[j] + previousRow[j - 1])
        }
        result.add(row)
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(1, listOf(listOf(1))),
        Pair(2, listOf(listOf(1), listOf(1,1))),
        Pair(3, listOf(listOf(1), listOf(1,1), listOf(1,2,1))),
        Pair(4, listOf(listOf(1), listOf(1,1), listOf(1,2,1), listOf(1,3,3,1))),
        Pair(5, listOf(listOf(1), listOf(1,1), listOf(1,2,1), listOf(1,3,3,1), listOf(1,4,6,4,1))),
        Pair(6, listOf(listOf(1), listOf(1,1), listOf(1,2,1), listOf(1,3,3,1), listOf(1,4,6,4,1), listOf(1,5,10,10,5,1))),
        Pair(10, listOf(
            listOf(1),
            listOf(1,1),
            listOf(1,2,1),
            listOf(1,3,3,1),
            listOf(1,4,6,4,1),
            listOf(1,5,10,10,5,1),
            listOf(1,6,15,20,15,6,1),
            listOf(1,7,21,35,35,21,7,1),
            listOf(1,8,28,56,70,56,28,8,1),
            listOf(1,9,36,84,126,126,84,36,9,1)
        )),
    )

    validateSolution(testCases, ::generate)
}