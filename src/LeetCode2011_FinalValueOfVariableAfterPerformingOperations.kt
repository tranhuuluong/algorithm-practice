/**
 * 2011: Final Value of Variable After Performing Operations
 * Easy
 * https://leetcode.com/problems/final-value-of-variable-after-performing-operations/
 *
 * You are given an array of strings operations containing a list of operations.
 * Each operation is one of the following: "++X", "X++", "--X", or "X--".
 * The variable X starts at 0.
 * For each operation:
 *   - "++X" and "X++" both increment X by 1.
 *   - "--X" and "X--" both decrement X by 1.
 * Return the final value of X after performing all the operations.
 *
 * Example 1:
 * Input: operations = ["--X","X++","X++"]
 * Output: 1
 * Explanation:
 *   Start: X = 0
 *   --X → X = -1
 *   X++ → X = 0
 *   X++ → X = 1
 *
 * Example 2:
 * Input: operations = ["++X","++X","X++"]
 * Output: 3
 * Explanation:
 *   Start: X = 0
 *   ++X → X = 1
 *   ++X → X = 2
 *   X++ → X = 3
 *
 * Constraints:
 * - 1 <= operations.length <= 100
 * - operations[i] is one of: "++X", "X++", "--X", "X--"
 *
 * @param operations the list of increment/decrement operations on X
 * @return Int the final value of X after all operations
 */
private fun finalValueAfterOperations(operations: Array<String>): Int {
    var result = 0
    for (operation in operations) {
        when (operation) {
            "++X", "X++" -> result++
            "--X", "X--" -> result--
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(arrayOf("--X","X++","X++"), 1),
        Pair(arrayOf("++X","++X","X++"), 3),
        // Additional cases
        Pair(arrayOf("X++","--X","X--","++X"), 0),
        Pair(arrayOf("X++"), 1),
        Pair(arrayOf("X--"), -1),
        Pair(arrayOf("--X","--X","--X"), -3),
        Pair(arrayOf("++X","X++","++X","X++"), 4),
        Pair(arrayOf("X--","X--","++X","X++"), 0),
        Pair(arrayOf("++X","--X","X--","X++","X++"), 1),
        Pair(arrayOf("X++","X++","X++","X++","X++"), 5)
    )

    validateSolution(testCases, ::finalValueAfterOperations)
}
