import kotlin.math.max

/**
 * 3005: Count Elements With Maximum Frequency
 * Easy
 * https://leetcode.com/problems/count-elements-with-maximum-frequency/
 *
 * You are given an array `nums` consisting of positive integers.
 * Return the total frequencies of elements in `nums` such that those elements all have the maximum frequency.
 *
 * The frequency of an element is the number of times it occurs in the array.
 *
 * Example 1:
 * Input: nums = [1,2,2,3,1,4]
 * Output: 4
 * Explanation: The elements with the maximum frequency are 1 and 2. Both have a frequency of 2.
 * The maximum frequency is 2.
 * The total frequencies of elements with maximum frequency is $2 + 2 = 4$.
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5]
 * Output: 5
 * Explanation: All elements have a frequency of 1.
 * The maximum frequency is 1.
 * The total frequencies of elements with maximum frequency is $1 + 1 + 1 + 1 + 1 = 5$.
 *
 * Constraints:
 * $1 \le nums.length \le 100$
 * $1 \le nums[i] \le 100$
 *
 * @param nums The array of positive integers.
 * @return The total frequencies of elements that have the maximum frequency in the array.
 */
private fun maxFrequencyElements(nums: IntArray): Int {
    val freqs = IntArray(101)
    var maxFreq = 0
    for (num in nums) {
        maxFreq = max(maxFreq, ++freqs[num])
    }
    var ans = 0
    for (freq in freqs) {
        if (freq == maxFreq) ans += freq
    }
    return ans
}

fun main() {
    // Function takes 1 argument (nums), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 2, 2, 3, 1, 4), 4),
        Pair(intArrayOf(1, 2, 3, 4, 5), 5),

        // Additional edge cases (min 10 total)
        // Single element
        Pair(intArrayOf(5), 1),
        // All elements are the same (max frequency = array length)
        Pair(intArrayOf(7, 7, 7, 7), 4),
        // Two maximum frequency elements (frequency 3)
        Pair(intArrayOf(10, 20, 10, 30, 20, 10, 20), 6),
        // Maximum frequency is 1, array length 10
        Pair(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 10),
        // Elements with different frequencies (max frequency 4)
        Pair(intArrayOf(1, 1, 1, 1, 2, 2, 3), 4),
        // Max frequency 2, two elements, total 4
        Pair(intArrayOf(5, 5, 6, 6, 7), 4),
        // Two elements with max frequency, other elements with lower frequency
        Pair(intArrayOf(100, 100, 50, 50, 25, 12, 100), 3),
        // Maximum constraint values (100)
        Pair(intArrayOf(100, 100, 1, 1, 1, 50, 50), 3),
        // Array of length 10, max frequency 3
        Pair(intArrayOf(1, 2, 3, 1, 2, 3, 1, 2, 3, 4), 9),
        // Array of length 2, max frequency 1
        Pair(intArrayOf(1, 2), 2)
    )

    validateSolution(testCases, ::maxFrequencyElements)
}