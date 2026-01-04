/**
 * 1390: Four Divisors
 * Medium
 * https://leetcode.com/problems/four-divisors/
 *
 * Given an integer array `nums`, return the sum of divisors of the integers in that array that have exactly four divisors.
 *
 * If an integer's number of divisors is not exactly four, then its divisors contribute nothing to the sum.
 *
 * Example 1:
 * Input: nums = [21,4,7]
 * Output: 32
 * Explanation:
 * - 21 has 4 divisors: 1, 3, 7, 21. Sum = 32.
 * - 4 has 3 divisors: 1, 2, 4.
 * - 7 has 2 divisors: 1, 7.
 * The answer is the sum of divisors of 21 only.
 *
 * Example 2:
 * Input: nums = [21,21]
 * Output: 64
 *
 * Example 3:
 * Input: nums = [1,2,3,4,5]
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 *
 * @param nums The input array of integers.
 * @return The sum of divisors of integers with exactly four divisors.
 */
private fun sumFourDivisors(nums: IntArray): Int {
    fun findDivisors(num: Int) = buildList {
        var i = 1
        while (i * i <= num) {
            if (num % i == 0) {
                add(i)
                if (i != num / i) {
                    add(num / i)
                }
            }
            i++
        }
    }

    var ans = 0
    for (num in nums) {
        val divisors = findDivisors(num)
        if (divisors.size == 4) {
            ans += divisors.sum()
        }
    }
    return ans
}

fun main() {
    // Function takes 1 argument (nums), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(21, 4, 7), 32),
        Pair(intArrayOf(21, 21), 64),
        Pair(intArrayOf(1, 2, 3, 4, 5), 0),

        // Additional edge cases
        // 1. Numbers with exactly 3 divisors (squares of primes like 4, 9, 25, 49)
        // 4 -> 1, 2, 4 (3 divisors) -> 0
        // 9 -> 1, 3, 9 (3 divisors) -> 0
        // 10 -> 1, 2, 5, 10 (4 divisors) -> 1+2+5+10 = 18
        Pair(intArrayOf(4, 9, 25, 49, 10), 18),

        // 2. Large primes (2 divisors)
        Pair(intArrayOf(99991), 0),

        // 3. Cube of a prime (e.g., 8 = 2^3 -> 1, 2, 4, 8) -> 4 divisors. Sum = 15.
        // 27 = 3^3 -> 1, 3, 9, 27 -> 4 divisors. Sum = 40.
        Pair(intArrayOf(8, 27), 55), // 15 + 40 = 55

        // 4. Product of two distinct primes (p * q) -> 1, p, q, pq (4 divisors)
        // 6 = 2 * 3 -> 1, 2, 3, 6 -> Sum 12
        // 15 = 3 * 5 -> 1, 3, 5, 15 -> Sum 24
        Pair(intArrayOf(6, 15), 36),

        // 5. Number with many divisors (12 -> 1,2,3,4,6,12) -> 6 divisors
        Pair(intArrayOf(12, 18, 20), 0),

        // 6. Max constraints test (Single large number with 4 divisors)
        // 99994 = 2 * 49997 (49997 is prime). Divisors: 1, 2, 49997, 99994.
        // Sum = 1 + 2 + 49997 + 99994 = 149994
        Pair(intArrayOf(99994), 0),

        // 7. Smallest number with 4 divisors (6)
        Pair(intArrayOf(6), 12),

        // 8. Number 1 (1 divisor)
        Pair(intArrayOf(1), 0),

        // 9. Mixed bag
        Pair(intArrayOf(6, 7, 8, 9, 10, 11, 12), 45) // 6(12) + 7(0) + 8(15) + 9(0) + 10(18) + 11(0) + 12(0) = 45
    )

    validateSolution(testCases, ::sumFourDivisors)
}