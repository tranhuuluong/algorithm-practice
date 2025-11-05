import model.Quadruple
import java.util.PriorityQueue

/**
 * 3318: Find X-Sum of All K-Long Subarrays I
 * Easy
 * https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/
 *
 * You are given an array nums of n integers and two integers k and x.
 *
 * The x-sum of an array is calculated by the following procedure:
 *  • Count the occurrences of all elements in the array.
 *  • Keep only the occurrences of the top x most frequent elements.
 *    If two elements have the same number of occurrences, the element
 *    with the bigger value is considered more frequent.
 *  • Calculate the sum of the resulting array.
 *
 * Note: If an array has fewer than x distinct elements, its x-sum
 * is simply the sum of the array.
 *
 * Return an integer array answer of length n − k + 1 where answer[i]
 * is the x-sum of the subarray nums[i..i + k − 1].
 *
 * Example 1:
 * Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
 * Output: [6,10,12]
 * Explanation:
 *   • Subarray [1,1,2,2,3,4] → top 2 frequent elements {1,2} → sum = 6
 *   • Subarray [1,2,2,3,4,2] → top 2 frequent elements {2,4} → sum = 10
 *   • Subarray [2,2,3,4,2,3] → top 2 frequent elements {2,3} → sum = 12
 *
 * Example 2:
 * Input: nums = [3,8,7,8,7,5], k = 2, x = 2
 * Output: [11,15,15,15,12]
 * Explanation: Each window of size 2 has at most 2 distinct elements,
 * so each x-sum is simply the sum of the window.
 *
 * Constraints:
 *  • 1 <= n == nums.length <= 50
 *  • 1 <= nums[i] <= 50
 *  • 1 <= x <= k <= nums.length
 *
 * @param nums the input integer array
 * @param k the length of each sliding subarray
 * @param x the number of most frequent elements to keep
 * @return an IntArray of length nums.size − k + 1 where each element is the x-sum of the corresponding subarray
 */
private fun findXSum(nums: IntArray, k: Int, x: Int): IntArray {
    val n = nums.size
    val ans = IntArray(n - k + 1)
    var i = 0
    while (i + k <= n) {
        val freqMap = nums.copyOfRange(i, i + k)
            .toList()
            .groupingBy { it }
            .eachCount()

        val pq = PriorityQueue<Pair<Int, Int>> { a, b ->
            when {
                a.second == b.second -> b.first - a.first
                else -> b.second - a.second
            }
        }

        for ((num, freq) in freqMap) {
            pq.add(num to freq)
        }

        var sum = 0
        repeat(x) {
            if (pq.isNotEmpty()) {
                val (num, freq) = pq.poll()
                sum += num * freq
            }
        }
        ans[i++] = sum
    }
    return ans
}

fun main() {
    val testCases = listOf(
        // Provided examples
        Quadruple(intArrayOf(1, 1, 2, 2, 3, 4, 2, 3), 6, 2, intArrayOf(6, 10, 12)),
        Quadruple(intArrayOf(3,8,7,8,7,5), 2, 2, intArrayOf(11,15,15,15,12)),
        // Additional test cases
        Quadruple(intArrayOf(1,2,3,4,5), 3, 1, intArrayOf(3,4,5)),
        Quadruple(intArrayOf(5,5,5,5), 2, 1, intArrayOf(10,10,10)),
        Quadruple(intArrayOf(1,2,1,2,1,3), 4, 2, intArrayOf(6,6,5)),
        Quadruple(intArrayOf(10,20,20,10,30), 3, 2, intArrayOf(50,50,50)),
        Quadruple(intArrayOf(4,4,4,3,3,2,2,2), 5, 2, intArrayOf(18,14,10,12)),
        Quadruple(intArrayOf(1,1,2,3,3,3,2,2), 4, 3, intArrayOf(7,9,11,11,10)),
        Quadruple(intArrayOf(7,8,9,10), 2, 2, intArrayOf(15,17,19)),
        Quadruple(intArrayOf(1,2,2,3,3,3), 3, 2, intArrayOf(5,7,8,9))
    )

    validateSolution(testCases, ::findXSum)
}
