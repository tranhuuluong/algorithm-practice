/**
 * 2410: Maximum Matching of Players With Trainers
 * Medium
 * https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
 *
 * You are given a 0-indexed integer array players, where players[i] represents the ability of the i-th player.
 * You are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the j-th trainer.
 *
 * The i-th player can match with the j-th trainer if the player's ability is less than or equal to the trainer's training capacity.
 * Additionally, each player can be matched with at most one trainer, and each trainer can be matched with at most one player.
 *
 * Return the maximum number of matchings between players and trainers that satisfy these conditions.
 *
 * Example 1:
 * Input: players = [4,7,9], trainers = [8,2,5,8]
 * Output: 2
 * Explanation: One of the ways we can form two matchings is:
 *   - players[0] (ability=4) matched with trainers[0] (capacity=8)
 *   - players[1] (ability=7) matched with trainers[3] (capacity=8)
 *
 * Example 2:
 * Input: players = [1,1,1], trainers = [10]
 * Output: 1
 * Explanation: The trainer can be matched with any one of the three players.
 *
 * Constraints:
 * 1 <= players.length, trainers.length <= 10^5
 * 1 <= players[i], trainers[j] <= 10^9
 *
 * @param players the abilities of players
 * @param trainers the training capacities of trainers
 * @return the maximum number of valid matchings
 */
private fun matchPlayersAndTrainers(players: IntArray, trainers: IntArray): Int {
    players.sort()
    trainers.sort()
    var p = 0
    var t = 0
    var result = 0
    while (p <= players.lastIndex && t <= trainers.lastIndex) {
        if (players[p] <= trainers[t]) {
            p++
            t++
            result++
        } else {
            t++
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(4,7,9), intArrayOf(8,2,5,8), 2),     // Example 1
        Triple(intArrayOf(1,1,1), intArrayOf(10), 1),          // Example 2
        Triple(intArrayOf(5,1,4), intArrayOf(3,8,6,1), 3),
        Triple(intArrayOf(10,20,30), intArrayOf(5,15,25,35), 3),
        Triple(intArrayOf(1,2,3), intArrayOf(1,1,1), 1),
        Triple(intArrayOf(2,2,2), intArrayOf(2,2), 2),
        Triple(intArrayOf(7,3,5), intArrayOf(4,8,6), 3),
        Triple(intArrayOf(1000000000), intArrayOf(1000000000), 1),
        Triple(intArrayOf(1,2,3,4,5), intArrayOf(5,4,3,2,1), 5),
        Triple(intArrayOf(2,9,5,1), intArrayOf(3,5,7,9,11), 4)
    )

    validateSolution(testCases, ::matchPlayersAndTrainers)
}
