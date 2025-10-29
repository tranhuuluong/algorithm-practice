/**
 * 3354: Make Array Elements Equal to Zero
 * Easy
 * https://leetcode.com/problems/make-array-elements-equal-to-zero/
 *
 * You are given an integer array nums.
 *
 * Start by selecting a starting position curr such that nums[curr] == 0, and choose a movement direction of either left or right.
 *
 * After that, you repeat the following process:
 *
 * • If curr is out of the range [0, n - 1], this process ends.
 * • If nums[curr] == 0, move in the current direction by incrementing curr if you are moving right, or decrementing curr if you are moving left.
 * • Else if nums[curr] > 0:
 *     – Decrement nums[curr] by 1.
 *     – Reverse your movement direction (left becomes right and vice versa).
 *     – Take a step in your new direction.
 *
 * A selection of the initial position curr and movement direction is considered valid if every element in nums becomes 0 by the end of the process.
 *
 * Return the number of possible valid selections.
 *
 * Example 1:
 * Input: nums = [1,0,2,0,3]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [2,3,4,0,4,1,0]
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * There is at least one element i where nums[i] == 0.
 *
 * @param nums the integer array
 * @return the number of possible valid selections
 */
private fun countValidSelections(nums: IntArray): Int {
    val sum = nums.sum()
    var leftSum = 0
    var rightSum = sum
    var result = 0
    for (num in nums) {
        if (num == 0) {
            if (rightSum - leftSum in 0..1) result++
            if (leftSum - rightSum in 0..1) result++
        }
        leftSum += num
        rightSum -= num
    }
    return result
}

private fun countValidSelections2(nums: IntArray): Int {
    fun performOperation(index: Int, moveLeft: Boolean): Boolean {
        var index = index
        var moveLeft = moveLeft
        val clone = nums.copyOf()
        while (index in nums.indices) {
            if (clone[index] > 0) {
                clone[index]--
                moveLeft = !moveLeft
            }
            if (moveLeft) index++ else index--
        }
        return clone.sum() == 0
    }

    var result = 0
    for (i in nums.indices) {
        if (nums[i] == 0) {
            if (performOperation(i, true)) result++
            if (performOperation(i, false)) result++
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Pair(intArrayOf(1,0,2,0,3), 2),            // Example 1
        Pair(intArrayOf(2,3,4,0,4,1,0), 0),        // Example 2
        Pair(intArrayOf(0), 2),                    // trivial one element zero: two directions both valid
        Pair(intArrayOf(0,0), 4),                  // two zeros: each zero gives two directions => 4
        Pair(intArrayOf(0,1,0), 2),                // sums: left zero at idx0: left0 vs right1 => |0-1|=1 =>1; idx2: left1 vs right0 => |1-0|=1 =>1 => total 2
        Pair(intArrayOf(1,0,0,1), 4),              // check
        Pair(intArrayOf(3,0,1), 0),
        Pair(intArrayOf(1,2,0,1), 0),
        Pair(intArrayOf(1,1,0,1,1), 2),
        Pair(intArrayOf(0,2,0,2,0), 2)
    )

    validateSolution(testCases, ::countValidSelections)
    printDivider()
    validateSolution(testCases, ::countValidSelections2)
}
