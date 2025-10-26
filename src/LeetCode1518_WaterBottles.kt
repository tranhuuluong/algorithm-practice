/**
 * 1518: Water Bottles
 * Easy
 * https://leetcode.com/problems/water-bottles/
 *
 * Given numBottles full water bottles, you can exchange numExchange empty water bottles for one full water bottle.
 * The operation of drinking a full water bottle turns it into an empty bottle.
 * Return the maximum number of water bottles you can drink. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: numBottles = 9, numExchange = 3
 * Output: 13
 * Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 9 + 3 + 1 = 13.
 *
 * Example 2:
 * Input: numBottles = 15, numExchange = 4
 * Output: 19
 * Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 15 + 3 + 1 = 19.
 *
 * Example 3:
 * Input: numBottles = 5, numExchange = 5
 * Output: 6
 *
 * Example 4:
 * Input: numBottles = 2, numExchange = 3
 * Output: 2
 *
 * Constraints:
 * 1 <= numBottles <= 100
 * 2 <= numExchange <= 100
 *
 * @param numBottles the initial number of full water bottles
 * @param numExchange the number of empty bottles required to exchange for one full bottle
 * @return the maximum number of water bottles you can drink
 */
private fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
    var result = numBottles
    var numBottles = numBottles
    while (numBottles >= numExchange) {
        val exchangedBottles = numBottles / numExchange
        numBottles = exchangedBottles + numBottles % numExchange
        result += exchangedBottles
    }
    return result
}

fun main() {
    val testCases = listOf(
        Triple(9, 3, 13),     // Example 1
        Triple(15, 4, 19),    // Example 2
        Triple(5, 5, 6),      // Example 3
        Triple(2, 3, 2),      // Example 4
        Triple(1, 2, 1),
        Triple(3, 3, 4),
        Triple(10, 3, 14),
        Triple(100, 5, 124),
        Triple(99, 2, 197),
        Triple(6, 3, 8)
    )

    validateSolution(testCases, ::numWaterBottles)
}
