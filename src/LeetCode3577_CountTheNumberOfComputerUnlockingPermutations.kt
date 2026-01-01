/**
 * 3577: Count the Number of Computer Unlocking Permutations
 * Medium (Based on typical contest difficulty for this type of problem)
 *
 * You are given an array `complexity` of length $n$. There are $n$ locked computers in a room with labels from $0$ to $n-1$, each with its own unique password.
 * The password for the computer labeled $0$ is already decrypted and serves as the root.
 *
 * The rules for decrypting passwords are:
 * 1. To decrypt the password for computer $i$, you can use the password for computer $j$, where $j$ is **any** integer less than $i$ with a lower complexity:
 * i.e., $j < i$ AND complexity[j] < complexity[i].
 * 2. To decrypt the password for computer $j$, you must have already unlocked a computer $i$ such that:
 * i.e., $i < j$ AND complexity[i] < complexity[j].
 *
 * Find the number of permutations of $[0, 1, 2, ..., n-1]$ that represent a valid order in which the computers can be unlocked, starting from computer 0 as the initially unlocked one.
 *
 * Since the answer may be large, return it modulo $10^9 + 7$.
 *
 * Note that the password for the computer with label 0 is decrypted, and not the computer with the first position in the permutation.
 *
 * Example 1:
 * Input: complexity = [1, 2, 3]
 * Output: 2
 * Explanation: The valid permutations are:
 * - [0, 1, 2]: Unlock 0 (root), then 1 (using 0: $0 < 1, complexity[0] < complexity[1]$), then 2 (using 1: $1 < 2, complexity[1] < complexity[2]$).
 * - [0, 2, 1]: Unlock 0 (root), then 2 (using 0: $0 < 2, complexity[0] < complexity[2]$), then 1 (using 0: $0 < 1, complexity[0] < complexity[1]$).
 *
 * Example 2:
 * Input: complexity = [3, 3, 4, 4]
 * Output: 0
 * Explanation: No possible permutations which can unlock all computers. (e.g., cannot unlock 0 first as its complexity is not the smallest.)
 *
 * Constraints:
 * 2 <= complexity.length <= 10^5
 * 1 <= complexity[i] <= 10^9
 *
 * @param complexity An array defining the complexity of each computer's password.
 * @return The number of valid unlocking permutations modulo $10^9 + 7$.
 */
private fun countUnlockingPermutations(complexity: IntArray): Int {
    val n = complexity.size
    for (i in 1 until n) {
        if (complexity[i] <= complexity[0]) {
            return 0
        }
    }

    var ans = 1L
    val mod = 1000000007
    for (i in 2 until n) {
        ans = ans * i % mod
    }
    return ans.toInt()
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 2, 3), 2),
        Pair(intArrayOf(3, 3, 4, 4), 0),

        // Edge Cases

        // 1. Min size, Valid: 0 unlocks 1
        Pair(intArrayOf(1, 2), 1),

        // 2. Min size, Invalid: 0 cannot unlock 1 (2 not < 1)
        Pair(intArrayOf(2, 1), 0),

        // 3. 0 unlocks all (Min complexity at 0). Permutations of {1,2,3} = 3! = 6
        Pair(intArrayOf(1, 10, 10, 10), 6),

        // 4. Increasing complexity. 0 unlocks 1, 2, and 3. All permutations valid.
        // Corrected from previous error (was 1, should be 6).
        Pair(intArrayOf(10, 20, 30, 40), 6),

        // 5. Mixed complexity but 0 is min. 0 unlocks 1, 2, and 3. All perms valid.
        Pair(intArrayOf(1, 3, 2, 4), 6),

        // 6. Strict inequality check. 0 (c=1) cannot unlock 1 (c=1).
        Pair(intArrayOf(1, 1, 2, 2), 0),

        // 7. 0 unlocks 1 and 2. Both perms [0,1,2] and [0,2,1] valid.
        Pair(intArrayOf(1, 2, 2), 2),

        // 8. Blocking case.
        // 1 needs j<1, c<10. 0(c=5) works. 1 is unlockable.
        // 2 needs j<2, c<1. 0(c=5) fails. 1(c=10) fails. 2 is locked.
        // Result 0.
        Pair(intArrayOf(5, 10, 1, 15), 0)
    )

    validateSolution(testCases, ::countUnlockingPermutations)
}