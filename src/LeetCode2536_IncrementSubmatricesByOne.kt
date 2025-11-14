/**
 * 2536: Increment Submatrices by One
 * Medium
 * https://leetcode.com/problems/increment-submatrices-by-one/
 *
 * You are given a positive integer n, indicating that we initially have an n × n 0‑indexed integer matrix `mat` filled with zeroes.
 * You are also given a 2D integer array `queries`, where each `queries[i] = [row1, col1, row2, col2]`.
 * For each query, add 1 to every element in the submatrix defined by its top-left corner (row1, col1) and bottom-right corner (row2, col2).
 * Return the matrix `mat` after performing all the queries.
 *
 * Example 1:
 * Input: n = 3, queries = [[1,1,2,2], [0,0,1,1]]
 * Output: [[1,1,0], [1,2,1], [0,1,1]]
 *
 * Example 2:
 * Input: n = 2, queries = [[0,0,1,1]]
 * Output: [[1,1], [1,1]]
 *
 * Constraints:
 * 1 <= n <= 500
 * 1 <= queries.length <= 10^4
 * 0 <= row1 <= row2 < n
 * 0 <= col1 <= col2 < n
 *
 * @param n the dimension of the square matrix
 * @param queries the list of submatrix increment operations
 * @return the resulting matrix after applying all increments
 */
private fun rangeAddQueries(n: Int, queries: Array<IntArray>): Array<IntArray> {
    val ans = Array(n) { IntArray (n) }
    for (query in queries) {
        val (row1, col1, row2, col2) = query
        for (i in row1..row2) {
            for (j in col1..col2) {
                ans[i][j]++
            }
        }
    }
    return ans
}

fun main() {
    fun printMat(mat: Array<IntArray>) = mat.forEach { println(it.joinToString(prefix = "[", postfix = "]")) }

    val testCases = listOf(
        // Example 1
        Triple(3, arrayOf(intArrayOf(1,1,2,2), intArrayOf(0,0,1,1)), arrayOf(
            intArrayOf(1,1,0),
            intArrayOf(1,2,1),
            intArrayOf(0,1,1)
        )),
        // Example 2
        Triple(2, arrayOf(intArrayOf(0,0,1,1)), arrayOf(
            intArrayOf(1,1),
            intArrayOf(1,1)
        )),
        // Additional test cases
        Triple(4, arrayOf(intArrayOf(0,0,3,3)), arrayOf(
            intArrayOf(1,1,1,1),
            intArrayOf(1,1,1,1),
            intArrayOf(1,1,1,1),
            intArrayOf(1,1,1,1)
        )),
        Triple(3, arrayOf(intArrayOf(0,0,0,0), intArrayOf(1,1,2,2)), arrayOf(
            intArrayOf(1,0,0),
            intArrayOf(0,1,1),
            intArrayOf(0,1,1)
        )),
        Triple(3, arrayOf(intArrayOf(0,1,1,2), intArrayOf(1,0,2,1)), arrayOf(
            intArrayOf(0,1,1),
            intArrayOf(1,2,1),
            intArrayOf(1,1,0)
        )),
        Triple(5, arrayOf(intArrayOf(1,1,3,3), intArrayOf(0,0,4,4)), arrayOf(
            intArrayOf(1,1,1,1,1),
            intArrayOf(1,2,2,2,1),
            intArrayOf(1,2,2,2,1),
            intArrayOf(1,2,2,2,1),
            intArrayOf(1,1,1,1,1)
        )),
        Triple(3, arrayOf(intArrayOf(2,2,2,2)), arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(0,0,0),
            intArrayOf(0,0,1)
        )),
        Triple(1, arrayOf(intArrayOf(0,0,0,0), intArrayOf(0,0,0,0)), arrayOf(
            intArrayOf(2)
        )),
        Triple(3, arrayOf(intArrayOf(0,2,1,2), intArrayOf(1,0,2,1)), arrayOf(
            intArrayOf(0,0,1),
            intArrayOf(1,1,1),
            intArrayOf(1,1,0)
        )),
        Triple(4, arrayOf(intArrayOf(1,1,2,2), intArrayOf(0,3,3,3), intArrayOf(3,0,3,0)), arrayOf(
            intArrayOf(0,0,0,1),
            intArrayOf(0,1,1,1),
            intArrayOf(0,1,1,1),
            intArrayOf(1,0,0,1)
        ))
    )

    validateSolution(testCases, ::rangeAddQueries)
}
