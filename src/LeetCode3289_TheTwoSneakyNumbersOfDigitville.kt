import kotlin.math.abs

/**
 * 3289: The Two Sneaky Numbers of Digitville
 * Easy
 * https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/
 *
 * In the town of Digitville, there was a list of numbers called `nums` containing integers from `0` to `n - 1`.
 * Each number was supposed to appear exactly once in the list, however, two mischievous numbers sneaked in an additional time, making the list longer than usual.
 *
 * As the town detective, your task is to find these two sneaky numbers. Return an array of size two containing the two numbers (in any order), so peace can return to Digitville.
 *
 * Example 1:
 * Input: nums = [0,1,1,0]
 * Output: [0,1]
 *
 * Example 2:
 * Input: nums = [0,3,2,1,3,2]
 * Output: [2,3]
 *
 * Example 3:
 * Input: nums = [7,1,5,4,3,4,6,0,9,5,8,2]
 * Output: [4,5]
 *
 * Constraints:
 * 2 <= n <= 100
 * nums.length == n + 2
 * 0 <= nums[i] < n
 *
 * @param nums the input array of length n + 2 containing integers in [0, n-1] where exactly two numbers appear twice
 * @return an IntArray of size two containing the two numbers that appear twice (order may be arbitrary)
 */

private fun getSneakyNumbers1(nums: IntArray): IntArray {
    val set = mutableSetOf<Int>()
    var firstNum = -1
    for (num in nums) {
        if (!set.add(num)) {
            if (firstNum == -1) firstNum = num else return intArrayOf(firstNum, num)
        }
    }
    return intArrayOf()
}

private fun getSneakyNumbers2(nums: IntArray): IntArray {
    val n = nums.size
    for (i in nums.indices) {
        if (nums[i] == 0) {
            nums[i] = n
        }
    }

    for (num in nums) {
        val index = abs(num)
        if (index <= nums.lastIndex) {
            nums[index] *= -1
        } else {
            nums[0] *= -1
        }
    }

    var firstNum = -1
    for (i in nums.indices) {
        if (nums[i] > 0) {
            if (firstNum == -1) {
                firstNum = i
            } else {
                return intArrayOf(firstNum, i)
            }
        }
    }
    return intArrayOf()
}

fun main() {
    val testCases = listOf(
        // Official examples (valid inputs)
        Pair(intArrayOf(0,1,1,0), intArrayOf(0,1)),                     // Example 1
        Pair(intArrayOf(0,3,2,1,3,2), intArrayOf(2,3)),                 // Example 2
        Pair(intArrayOf(7,1,5,4,3,4,6,0,9,5,8,2), intArrayOf(4,5)),     // Example 3

        // Additional valid test cases (exactly two distinct duplicates)
        Pair(intArrayOf(0,1,2,3,2,4,5,0), intArrayOf(0,2)),
        Pair(intArrayOf(0,1,2,3,4,5,6,1,7,5), intArrayOf(1,5)),
        Pair(intArrayOf(0,1,2,3,4,3,5,2), intArrayOf(2,3)),
        Pair(intArrayOf(0,1,2,3,4,5,6,4,7,6), intArrayOf(4,6)),
        Pair(intArrayOf(0,1,2,3,4,5,6,7,8,7,9,8), intArrayOf(7,8)),
        Pair(intArrayOf(0,1,2,3,4,5,5,1), intArrayOf(1,5)),
        Pair(intArrayOf(0,1,2,3,0,4,2,5), intArrayOf(0,2))
    )

    val comparator : (IntArray, IntArray) -> Boolean = { expected, actual ->
        expected.sorted() == actual.sorted()
    }
    validateSolution(testCases, ::getSneakyNumbers1, comparator)
    printDivider()
    validateSolution(testCases, ::getSneakyNumbers2, comparator)
}
