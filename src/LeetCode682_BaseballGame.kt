/**
 * 682: Baseball Game
 * Easy
 * https://leetcode.com/problems/baseball-game/
 *
 * You are keeping score for a baseball game with strange rules. The game consists of several rounds,
 * where the scores of past rounds may affect future rounds' scores. At the beginning of the game, you
 * start with an empty record. You are given a list of strings `ops`, where `ops[i]` is the iᵗʰ operation
 * you must apply to the record and is one of the following:
 *
 * 1. An integer `x` - Record a new score of `x`.
 * 2. `"+"` - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
 * 3. `"D"` - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
 * 4. `"C"` - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.
 *
 * Return the sum of all the scores on the record.
 *
 * Example 1:
 * Input: ops = ["5","2","C","D","+"]
 * Output: 30
 * Explanation:
 * "5" - Add 5 to the record, record is now [5].
 * "2" - Add 2 to the record, record is now [5,2].
 * "C" - Remove the previous score, record is now [5].
 * "D" - Double the previous score 5 → 10, record is now [5,10].
 * "+" - Sum of previous two scores 5+10 = 15, record is now [5,10,15]. Total = 30.
 *
 * Example 2:
 * Input: ops = ["5","-2","4","C","D","9","+","+"]
 * Output: 27
 * Explanation:
 * Following the operations step-by-step gives final record [5, -2, -4, 9, 5, 14], sum = 27.
 *
 * Constraints:
 * - 1 <= ops.length <= 1000
 * - ops[i] is "C", "D", "+", or a string representing an integer in the range [-3 * 10⁴, 3 * 10⁴]
 * - For operation "+", there will always be at least two previous scores on the record.
 * - For operations "C" and "D", there will always be at least one previous score on the record.
 *
 * @param ops Array<String> list of operations
 * @return Int sum of all valid scores after processing ops
 */
private fun calPoints(ops: Array<String>): Int {
    val stack = ArrayDeque<Int>()
    for (op in ops) {
        when (op) {
            "C" -> stack.removeLast()
            "D" -> stack.add(stack.last() * 2)
            "+" -> stack.add(stack.takeLast(2).sum())
            else -> stack.add(op.toInt())
        }
    }
    return stack.sum()
}

fun main() {
    val testCases = listOf(
        Pair(arrayOf("5", "2", "C", "D", "+"), 30),
        Pair(arrayOf("5", "-2", "4", "C", "D", "9", "+", "+"), 27),
        Pair(arrayOf("1"), 1),
        Pair(arrayOf("1", "C"), 0),
        Pair(arrayOf("1", "D"), 3),
        Pair(arrayOf("10", "20", "D", "+"), 130),
        Pair(arrayOf("10", "20", "C", "+"), 20),
        Pair(arrayOf("0", "D", "+"), 0),
        Pair(arrayOf("-5", "-10", "D", "+"), -65),
        Pair(arrayOf("1000", "2000", "D", "C", "+", "D"), 12000)
    )

    validateSolution(testCases, ::calPoints)
}
