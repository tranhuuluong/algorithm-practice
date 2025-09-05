/**
 * 189: Rotate Array
 * Medium
 * https://leetcode.com/problems/rotate-array/
 *
 * You are given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.
 * Rotate the array in-place, modifying the input array directly without using extra space.
 *
 * Example:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 *
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - 0 <= k <= 10^5
 *
 * @param nums the array of integers to be rotated
 * @param k the number of steps to rotate to the right
 * @return Unit (modifies nums in-place)
 */
private fun rotate1(nums: IntArray, k: Int): IntArray {
    repeat(k) {
        val temp = nums.last()
        for (i in nums.lastIndex - 1 downTo 0) {
            nums[i + 1] = nums[i]
        }
        nums[0] = temp
    }
    return nums
}

private fun rotate2(nums: IntArray, k: Int): IntArray {
    fun reverse(nums: IntArray, l: Int, r: Int) {
        var l = l
        var r = r
        while (l < r) {
            val temp = nums[l]
            nums[l] = nums[r]
            nums[r] = temp
            l++
            r--
        }
    }

    val n = nums.size
    val k = k % n

    reverse(nums, 0, n - 1)
    reverse(nums, 0, k - 1)
    reverse(nums, k, n - 1)

    return nums
}

private fun rotate3(nums: IntArray, k: Int): IntArray {
    val n = nums.size
    val k = k % n
    val arr = IntArray(nums.size)

    var insertIndex = 0
    for (i in (n - k)..n - 1) {
        arr[insertIndex++] = nums[i]
    }

    for (i in 0..(n - k - 1)) {
        arr[insertIndex++] = nums[i]
    }

    for (i in 0..nums.lastIndex) {
        nums[i] = arr[i]
    }
    return nums
}

fun main() {
    validateSolution(testCases(), ::rotate1)
    printDivider()
    validateSolution(testCases(), ::rotate2)
    printDivider()
    validateSolution(testCases(), ::rotate3)
}

private fun testCases() = listOf(
    // Official example
    Triple(intArrayOf(1,2,3,4,5,6,7), 3, intArrayOf(5,6,7,1,2,3,4)),
    // Additional valid cases
    Triple(intArrayOf(-1, -100, 3, 99), 2, intArrayOf(3,99,-1,-100)),
    Triple(intArrayOf(1,2,3), 0, intArrayOf(1,2,3)),
    Triple(intArrayOf(1), 5, intArrayOf(1)),
    Triple(intArrayOf(1,2), 1, intArrayOf(2,1)),
    Triple(intArrayOf(1,2), 2, intArrayOf(1,2)),
    Triple(intArrayOf(1,2), 3, intArrayOf(2,1)),
    Triple(intArrayOf(1,2,3,4,5), 5, intArrayOf(1,2,3,4,5)),
    Triple(intArrayOf(1,2,3,4,5), 7, intArrayOf(4,5,1,2,3)),
    Triple(intArrayOf(1,2,3,4,5,6), 8, intArrayOf(5,6,1,2,3,4))
)
