/**
 * 961: N-Repeated Element in Size 2N Array
 * Easy
 * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
 *
 * You are given an integer array `nums` with the following properties:
 * - `nums.length == 2 * n`.
 * - `nums` contains `n + 1` unique elements.
 * - Exactly one element of `nums` is repeated `n` times.
 *
 * Return the element that is repeated `n` times.
 *
 * Example 1:
 * Input: nums = [1,2,3,3]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [2,1,2,5,3,2]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [5,1,5,2,5,3,5,4]
 * Output: 5
 *
 * Constraints:
 * 2 <= n <= 5000
 * nums.length == 2 * n
 * 0 <= nums[i] <= 10^4
 * nums contains n + 1 unique elements and one of them is repeated exactly n times.
 *
 * @param nums The input array of size 2n.
 * @return The element that is repeated n times.
 */
private fun repeatedNTimes1(nums: IntArray): Int {
    val seen = mutableSetOf<Int>()
    for (num in nums) {
        if (!seen.add(num)) {
            return num
        }
    }
    throw Exception("no answer found!")
}

private fun repeatedNTimes2(nums: IntArray): Int {
    val n = nums.size
    for (i in 0..n - 3) {
        if (nums[i] == nums[i + 1] || nums[i] == nums[i + 2]) {
            return nums[i]
        }
    }
    return nums[n - 1]
}

fun main() {
    // Function takes 1 argument (nums), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 2, 3, 3), 3),
        Pair(intArrayOf(2, 1, 2, 5, 3, 2), 2),
        Pair(intArrayOf(5, 1, 5, 2, 5, 3, 5, 4), 5),

        // Additional edge cases
        // 1. Minimum size (n=2, length=4)
        Pair(intArrayOf(4, 5, 6, 6), 6),

        // 2. Repeated element is 0
        Pair(intArrayOf(0, 0, 1, 2), 0),

        // 3. Repeated element appears as a block at the start
        // n=3, length=6. Unique: 1, 2, 3, 9. Repeated 9 three times.
        Pair(intArrayOf(9, 9, 9, 1, 2, 3), 9),

        // 4. Repeated element appears as a block at the end
        Pair(intArrayOf(1, 2, 3, 8, 8, 8), 8),

        // 5. Perfectly alternating
        Pair(intArrayOf(1, 7, 2, 7, 3, 7), 7),

        // 6. Scattered positions (random-ish)
        Pair(intArrayOf(3, 1, 2, 3, 4, 3), 3),

        // 7. Larger inputs (n=5, length=10)
        Pair(intArrayOf(10, 1, 10, 2, 10, 3, 10, 4, 10, 5), 10),

        // 8. Element appears separated by distance 2 or 3
        Pair(intArrayOf(1, 2, 3, 1), 1)
    )

    validateSolution(testCases, ::repeatedNTimes1)
    printDivider()
    validateSolution(testCases, ::repeatedNTimes2)
}