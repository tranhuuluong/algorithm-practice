/**
 * 2211: Count Collisions on a Road
 * Medium
 * https://leetcode.com/problems/count-collisions-on-a-road/
 *
 * There are n cars on an infinitely long road. The cars are numbered from 0 to n - 1 from left to right
 * and each car is present at a unique point.
 *
 * You are given a 0-indexed string directions of length n. directions[i] can be either 'L', 'R', or 'S'
 * denoting whether the ith car is moving towards the left, towards the right, or staying at its current
 * point respectively. Each moving car has the same speed.
 *
 * The number of collisions can be calculated as follows:
 *
 * - When two cars moving in opposite directions collide with each other, the number of collisions
 *   increases by 2.
 * - When a moving car collides with a stationary car, the number of collisions increases by 1.
 *
 * After a collision, the cars involved can no longer move and will stay at the point where they
 * collided. Other than that, cars cannot change their state or direction of motion.
 *
 * Return the total number of collisions that will happen on the road.
 *
 * Example 1:
 * Input: directions = "RLRSLL"
 * Output: 5
 *
 * Example 2:
 * Input: directions = "LLRR"
 * Output: 0
 *
 * Constraints:
 * 1 <= directions.length <= 10^5
 * directions[i] is either 'L', 'R', or 'S'.
 *
 * @param directions a string where each character is 'L', 'R', or 'S'
 * @return total number of collisions
 */
private fun countCollisions(directions: String): Int {
    var ans = 0
    var lastCollisionCause = ' '
    var rCountBeforeCollided = 0
    for (d in directions) {
        when (d) {
            'R' -> {
                lastCollisionCause = 'R'
                rCountBeforeCollided++
            }
            'L' -> when (lastCollisionCause) {
                'R' -> {
                    lastCollisionCause = 'S'
                    ans += 2 + rCountBeforeCollided - 1
                    rCountBeforeCollided = 0
                }
                'S' -> {
                    ans += 1
                    lastCollisionCause = 'S'
                }
            }
            'S' -> {
                lastCollisionCause = 'S'
                ans += rCountBeforeCollided
                rCountBeforeCollided = 0
            }
        }
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Pair("RLRSLL", 5),
        Pair("LLRR", 0),
        Pair("L", 0),
        Pair("R", 0),
        Pair("S", 0),
        Pair("RRLL", 4),
        Pair("RLRLRL", 6),
        Pair("LLRSRR", 1),
        Pair("RRSLL", 4),
        Pair("LRRSL", 3)
    )

    validateSolution(testCases, ::countCollisions)
}
