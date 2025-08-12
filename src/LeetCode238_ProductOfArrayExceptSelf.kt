/**
 * 238: Product of Array Except Self
 * Medium
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * @param nums the input array of integers
 * @return an array of integers where each element is the product of all other elements in nums
 */

// [1, 2, 3, 4] -> [1, 1, 2, 6]
// leftPass[i] is the product of all elements before i

// [1, 2, 3, 4] -> [24, 12, 4, 1]
// rightPass[i] is the product of all elements after i

private fun productExceptSelf(nums: IntArray): IntArray {
    val leftPass = IntArray(nums.size)
    var left = 1
    for (i in 0..nums.lastIndex) {
        leftPass[i] = left
        left *= nums[i]
    }

    val rightPass = IntArray(nums.size)
    var right = 1
    for (i in nums.lastIndex downTo 0) {
        rightPass[i] = right
        right *= nums[i]
    }

    val answer = IntArray(nums.size)
    for (i in 0..nums.lastIndex) {
        answer[i] = leftPass[i] * rightPass[i]
    }

    return answer
}

private fun productExceptSelf2(nums: IntArray): IntArray {
    val answer = IntArray(nums.size)
    var left = 1
    for (i in 0..nums.lastIndex) {
        answer[i] = left
        left *= nums[i]
    }

    var right = 1
    for (i in nums.lastIndex downTo 0) {
        answer[i] *= right
        right *= nums[i]
    }

    return answer
}

private fun productExceptSelfBruteForce(nums: IntArray): IntArray {
    val answer = IntArray(nums.size)
    for (i in 0..nums.lastIndex) {
        var product = 1
        for (j in 0..nums.lastIndex) {
            if (i != j) {
                product *= nums[j]
            }
        }
        answer[i] = product
    }
    return answer
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(intArrayOf(1, 2, 3, 4), intArrayOf(24, 12, 8, 6)),
        Pair(intArrayOf(-1, 1, 0, -3, 3), intArrayOf(0, 0, 9, 0, 0)),
        // Additional realistic test cases
        Pair(intArrayOf(2, 3), intArrayOf(3, 2)),
        Pair(intArrayOf(5, 0, 2), intArrayOf(0, 10, 0)),
        Pair(intArrayOf(0, 0, 1), intArrayOf(0, 0, 0)),
        Pair(intArrayOf(4, -2, -2), intArrayOf(4, -8, -8)),
        Pair(intArrayOf(10, 5, 2), intArrayOf(10, 20, 50)),
        Pair(intArrayOf(3, 3, 3), intArrayOf(9, 9, 9)),
        Pair(intArrayOf(-1, -1, -1, -1), intArrayOf(-1, -1, -1, -1)),
        Pair(intArrayOf(1, 2, 3, 0, 4), intArrayOf(0, 0, 0, 24, 0))
    )

    validateSolution(testCases, ::productExceptSelfBruteForce)
    printDivider()
    validateSolution(testCases, ::productExceptSelf)
    printDivider()
    validateSolution(testCases, ::productExceptSelf2)
}
