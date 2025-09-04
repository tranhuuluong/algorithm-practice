/**
 * 18: 4Sum
 * Medium
 * https://leetcode.com/problems/4sum/
 *
 * You are given an array nums containing n integers and an integer target.
 * Return all unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *  - 0 <= a, b, c, d < n
 *  - a, b, c, and d are distinct.
 *  - nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * Example:
 * Input: nums = [1, 0, -1, 0, -2, 2], target = 0
 * Output: [[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]
 *
 * Constraints:
 * - 0 ≤ nums.length ≤ 200
 * - -10^9 ≤ nums[i] ≤ 10^9
 * - -10^9 ≤ target ≤ 10^9
 *
 * @param nums the input array of integers
 * @param target the target sum for quadruplets
 * @return List<List<Int>> containing all unique quadruplets that sum to target
 */
fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
    nums.sort()
    val result = mutableListOf<List<Int>>()
    val lastIndex = nums.lastIndex

    for (i in nums.indices) {
        if (i > 0 && nums[i] == nums[i - 1]) continue
        val a = nums[i]

        for (j in (i + 1)..lastIndex) {
            if (j > i + 1 && nums[j] == nums[j - 1]) continue
            val b = nums[j]

            var left = j + 1
            var right = lastIndex
            while (left < right) {
                val c = nums[left]
                val d = nums[right]
                val sum = a.toLong() + b + c + d
                when {
                    sum > target -> right--
                    sum < target -> left++
                    else -> {
                        result.add(listOf(a, b, c, d))
                        while (nums[left] == c && left < lastIndex) left++
                        while (nums[right] == d && right > 0) right--
                    }
                }
            }
        }
    }

    return result
}

fun main() {
    val testCases = listOf(
        // Official example
        Triple(
            intArrayOf(1, 0, -1, 0, -2, 2),
            0,
            listOf(listOf(-2, -1, 1, 2), listOf(-2, 0, 0, 2), listOf(-1, 0, 0, 1))
        ),
        // Additional valid cases
        Triple(intArrayOf(), 0, emptyList()),
        Triple(intArrayOf(2, 2, 2, 2), 8, listOf(listOf(2, 2, 2, 2))),
        Triple(intArrayOf(2, 2, 2, 2, 2), 8, listOf(listOf(2, 2, 2, 2))),
        Triple(intArrayOf(0, 0, 0, 0), 0, listOf(listOf(0, 0, 0, 0))),
        Triple(intArrayOf(1, 2, 3), 6, emptyList()),
        Triple(
            intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3), 0, listOf(
                listOf(-3, -2, 2, 3),
                listOf(-3, -1, 1, 3),
                listOf(-3, 0, 0, 3),
                listOf(-3, 0, 1, 2),
                listOf(-2, -1, 0, 3),
                listOf(-2, -1, 1, 2),
                listOf(-2, 0, 0, 2),
                listOf(-1, 0, 0, 1)
            )
        ),
        Triple(intArrayOf(1, 1, 1, 1), 4, listOf(listOf(1, 1, 1, 1))),
        Triple(intArrayOf(-2, -1, 0, 1, 2), 0, listOf(listOf(-2, -1, 1, 2))),
        Triple(
            intArrayOf(-2, -2, -1, 0, 0, 1, 1, 2, 2), 0, listOf(
                listOf(-2, -2, 2, 2),
                listOf(-2, -1, 1, 2),
                listOf(-2, 0, 0, 2),
                listOf(-2, 0, 1, 1),
                listOf(-1, 0, 0, 1)
            )
        ),
        Triple(
            intArrayOf(100000000, 100000000, 100000000, 100000000),
            400000000,
            listOf(listOf(100000000, 100000000, 100000000, 100000000))
        )
    )

    validateSolution(testCases, ::fourSum)
}
