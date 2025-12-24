/**
 * 3074: Apple Redistribution into Boxes
 * Easy
 * https://leetcode.com/problems/apple-redistribution-into-boxes/
 *
 * You are given an array `apple` where `apple[i]` is the number of apples in the $i$-th bag. You are also given an array `capacity` where `capacity[j]` is the maximum number of apples the $j$-th box can hold.
 *
 * All of the apples inside the bags are to be put into the boxes. Each box can have any number of apples, as long as it does not exceed its capacity.
 *
 * Return the minimum number of boxes needed to redistribute all the apples from the bags.
 *
 * Note: It is guaranteed that there are enough boxes to redistribute all the apples.
 *
 * Example 1:
 * Input: apple = [1,3,2], capacity = [4,3,1,5]
 * Output: 2
 * Explanation: We have a total of $1 + 3 + 2 = 6$ apples.
 * We can use the box with capacity 5 and the box with capacity 4. Total capacity is $5 + 4 = 9$, which is $\ge 6$.
 * No other combination of two boxes has a capacity $\ge 6$.
 * Note that there are 4 boxes and 3 bags.
 *
 * Example 2:
 * Input: apple = [5,5,5], capacity = [2,4,2,7]
 * Output: 2
 * Explanation: We have a total of $5 + 5 + 5 = 15$ apples.
 * We can use the box with capacity 7 and the box with capacity 4. Total capacity is $7 + 4 = 11$, which is $< 15$.
 * If we use the box with capacity 7 and the box with capacity 2 (either one), total capacity is $7 + 2 = 9$, which is $< 15$.
 * If we use the box with capacity 7, 4, and 2, total capacity is $7 + 4 + 2 = 13$, which is $< 15$.
 * Note: The problem statement guarantees there are enough boxes. Let's re-read the example explanation, as it seems contradictory to the guarantee.
 *
 * Re-evaluating Example 2 based on the requirement of finding the *minimum* number of boxes:
 * Total apples: $5 + 5 + 5 = 15$.
 * Box capacities sorted descending: [7, 4, 2, 2].
 * - 1 box (7): Capacity 7. Needs 15. Too small.
 * - 2 boxes (7+4): Capacity 11. Needs 15. Too small.
 * - 3 boxes (7+4+2): Capacity 13. Needs 15. Too small.
 * - 4 boxes (7+4+2+2): Capacity 15. Needs 15. Just enough.
 * Output should be 4.
 *
 * Re-checking Example 2's *official* output based on a common version of this problem: Output is 2. This implies the total number of apples for Example 2 might be incorrect in the prompt's summary, or the constraints were different.
 * Assuming the output '2' for Example 2 is correct, and the total apples calculation is $5+5+5=15$: This is only possible if the array was smaller.
 * Let's trust the official LeetCode problem constraints and examples.
 *
 * *Corrected Example 2 based on official LeetCode problem:*
 * Input: apple = [5,5,5], capacity = [2,4,2,7]. Total apples = 15.
 * Output: 4 (Must use all boxes to get capacity 15, which meets the total apple count.)
 * *We will proceed with the calculated output of 4 for Example 2, as the provided explanation text seems to contradict the guarantee and the actual requirement.*
 *
 * Constraints:
 * $1 \le apple.length \le 50$
 * $1 \le capacity.length \le 50$
 * $1 \le apple[i], capacity[i] \le 1000$
 * It is guaranteed that the sum of `capacity` is greater than or equal to the sum of `apple`.
 *
 * @param apple An array where $apple[i]$ is the number of apples in the $i$-th bag.
 * @param capacity An array where $capacity[j]$ is the capacity of the $j$-th box.
 * @return The minimum number of boxes needed.
 */
private fun minimumBoxes(apple: IntArray, capacity: IntArray): Int {
    capacity.sort()
    var totalCapacity = 0
    val totalApple = apple.sum()
    for (i in capacity.size - 1 downTo 0) {
        totalCapacity += capacity[i]
        if (totalCapacity >= totalApple) {
            return capacity.size - i
        }
    }
    return 0
}

fun main() {
    // Function takes 2 arguments (apple, capacity), so we must use Triple for (arg1, arg2, expected)
    val testCases = listOf(
        // Official Example 1
        Triple(intArrayOf(1, 3, 2), intArrayOf(4, 3, 1, 5), 2), // Total apples: 6. Boxes needed: 5+4=9. Min 2 boxes.

        // Official Example 2 (Corrected based on constraints: Total apples 15, needs 15 capacity, must use all 4 boxes)
        Triple(intArrayOf(5, 5, 5), intArrayOf(2, 4, 2, 7), 4), // Total apples: 15. Boxes sorted: 7, 4, 2, 2. 7+4+2+2 = 15. Min 4 boxes.

        // Additional edge cases (min 10 total)
        // 1. Single bag, single box (just enough)
        Triple(intArrayOf(10), intArrayOf(10), 1),

        // 2. Single bag, single box (more capacity)
        Triple(intArrayOf(5), intArrayOf(20), 1),

        // 3. Max capacity box is enough (multiple bags)
        Triple(intArrayOf(1, 1, 1), intArrayOf(10, 1, 1), 1), // Total apples: 3. Box 10 is enough.

        // 4. Multiple boxes needed, large capacity gap
        Triple(intArrayOf(100), intArrayOf(50, 40, 10, 5), 3), // Total apples: 100. Boxes sorted: 50, 40, 10, 5. Needs 50+40+10 = 100. Min 3 boxes.

        // 5. All boxes needed, capacity equal to total apples
        Triple(intArrayOf(1, 1, 1, 1), intArrayOf(1, 1, 1, 1), 4), // Total apples: 4.

        // 6. Many small capacity boxes
        Triple(intArrayOf(10), intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1), 10), // Total apples: 10. Needs all 10 boxes.

        // 7. Large input values (near 1000)
        Triple(intArrayOf(500, 500), intArrayOf(1000, 100, 100), 1), // Total apples: 1000. Box 1000 is enough.

        // 8. Longest possible arrays (n=50) - Simplified test case representation
        Triple(intArrayOf(20, 10), intArrayOf(15, 10, 5, 1), 3), // Total apples: 30. Needs 15+10 = 25 (too small). 15+10+5 = 30. Min 3 boxes.
        // Re-check: [20, 10], [15, 10, 5, 1]. Total 30. Sorted capacity: 15, 10, 5, 1.
        // 1 box (15) < 30. 2 boxes (15+10=25) < 30. 3 boxes (15+10+5=30) >= 30. Output: 3.
        Triple(intArrayOf(20, 10), intArrayOf(15, 10, 5, 1), 3),

        // 9. Edge case where two largest boxes are enough
        Triple(intArrayOf(1, 1, 1, 1), intArrayOf(2, 2, 1, 1), 2) // Total apples: 4. Needs 2+2=4. Min 2 boxes.
    )

    validateSolution(testCases, ::minimumBoxes)
}