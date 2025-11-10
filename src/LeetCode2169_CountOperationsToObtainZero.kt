/**
 * 2169: Count Operations to Obtain Zero
 * Easy
 * https://leetcode.com/problems/count-operations-to-obtain-zero/
 *
 * You are given two non-negative integers num1 and num2.
 * In one operation, if num1 >= num2, you must subtract num2 from num1,
 * otherwise subtract num1 from num2.
 *
 * Return the number of operations required to make either num1 = 0 or num2 = 0.
 *
 * Example 1:
 * Input: num1 = 2, num2 = 3
 * Output: 3
 * Explanation:
 *  - Operation 1: num1 = 2, num2 = 3 → num1 < num2 → num2 = 3-2 = 1
 *  - Operation 2: num1 = 2, num2 = 1 → num1 >= num2 → num1 = 2-1 = 1
 *  - Operation 3: num1 = 1, num2 = 1 → num1 >= num2 → num1 = 1-1 = 0
 *  Now num1 = 0, so stop.
 *
 * Example 2:
 * Input: num1 = 10, num2 = 10
 * Output: 1
 * Explanation:
 *  - Operation 1: num1 = num2 = 10 → subtract num2 from num1 → num1 = 10-10 = 0
 *  Stop because num1 = 0.
 *
 * Constraints:
 * 0 <= num1, num2 <= 10^5
 *
 * @param num1 first non-negative integer
 * @param num2 second non-negative integer
 * @return the number of operations to make either num1 = 0 or num2 = 0
 */
private fun countOperations(num1: Int, num2: Int): Int {
    var operation = 0
    var num1 = num1
    var num2 = num2
    while (num1 != 0 && num2 != 0) {
        if (num1 > num2) {
            num1 -= num2
        } else {
            num2 -= num1
        }
        operation++
    }
    return operation
}

fun main() {
    val testCases = listOf(
        Triple(2, 3, 3),      // Example 1
        Triple(10, 10, 1),    // Example 2
        Triple(0, 5, 0),
        Triple(5, 0, 0),
        Triple(1, 1, 1),
        Triple(4, 7, 5),
        Triple(5, 15, 3),
        Triple(100, 5, 20),
        Triple(99, 33, 3),
        Triple(12, 34, 8)
    )

    validateSolution(testCases, :: countOperations)
}
