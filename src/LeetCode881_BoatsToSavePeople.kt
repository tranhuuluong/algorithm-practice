/**
 * 881: Boats to Save People
 * Medium
 * https://leetcode.com/problems/boats-to-save-people/
 *
 * You are given an array `people` where `people[i]` is the weight of the i-th person, and an infinite number of boats available,
 * each boat can carry at most two people at the same time, provided the sum of the weight of those people is at most `limit`.
 * Return the minimum number of boats needed to carry every given person.
 *
 * Example:
 * Input: people = [3,2,2,1], limit = 3
 * Output: 3
 *
 * Explanation: Use three boats: (1,2), (2), and (3) as each boat can hold at most two people and the total weight per boat must not exceed `limit`.
 *
 * Constraints:
 * - 1 <= people.length <= 5 * 10^4
 * - 1 <= people[i] <= limit <= 3 * 10^4
 *
 * @param people the array of weights of each person
 * @param limit the maximum weight capacity of each boat
 * @return Int the minimum number of boats required to carry all people
 */
private fun numRescueBoats(people: IntArray, limit: Int): Int {
    people.sort()

    var left = 0
    var right = people.lastIndex
    var result = 0
    while (left <= right) {
        if (people[left] + people[right] <= limit) {
            result++
            left++
            right--
        } else {
            result++
            right--
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1, 2), 3, 1),
        Triple(intArrayOf(3, 2, 2, 1), 3, 3),
        Triple(intArrayOf(3, 5, 3, 4), 5, 4),
        // Additional valid cases
        Triple(intArrayOf(1), 1, 1),
        Triple(intArrayOf(1, 1), 2, 1),
        Triple(intArrayOf(2, 3), 3, 2),
        Triple(intArrayOf(2, 3), 5, 1),
        Triple(intArrayOf(5, 1, 4, 2), 6, 2),
        Triple(intArrayOf(5, 1, 4, 2), 5, 3),
        Triple(intArrayOf(3, 3, 2, 2, 1, 1), 3, 4)
    )

    validateSolution(testCases, ::numRescueBoats)
}
