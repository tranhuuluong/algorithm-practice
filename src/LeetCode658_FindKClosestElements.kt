import model.Quadruple
import kotlin.math.abs

/**
 * 658: Find K Closest Elements
 * Medium
 * https://leetcode.com/problems/find-k-closest-elements/
 *
 * Given a sorted integer array `arr`, two integers `k` and `x`, return the `k` closest integers to `x` in the array.
 * The result should also be sorted in ascending order.
 * An integer `a` is closer to `x` than an integer `b` if:
 *   • |a - x| < |b - x|, or
 *   • |a - x| == |b - x| and a < b
 *
 * Example 1:
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 * Constraints:
 * - 1 <= k <= arr.length <= 10^4
 * - arr is sorted in ascending order
 * - -10^4 <= arr[i], x <= 10^4
 *
 * @param arr the input sorted integer array
 * @param k the number of closest elements to return
 * @param x the target integer to compare distance to
 * @return List<Int> the k integers from arr closest to x, sorted in ascending order
 */
private fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
    var l = 0
    var r = arr.lastIndex
    while (r - l >= k) {
        if (abs(arr[l] - x) > abs(arr[r] - x)) {
            l++
        } else {
            r--
        }
    }
    val result = mutableListOf<Int>()
    for (i in l..r) {
        result.add(arr[i])
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Quadruple(intArrayOf(1, 2, 3, 4, 5), 4, 3, listOf(1, 2, 3, 4)),
        Quadruple(intArrayOf(1, 2, 3, 4, 5), 4, -1, listOf(1, 2, 3, 4)),
        // Additional cases
        Quadruple(intArrayOf(1, 3, 5, 7, 9), 3, 6, listOf(3, 5, 7)),
        Quadruple(intArrayOf(1, 3, 3, 3, 5, 7), 3, 3, listOf(3, 3, 3)),
        Quadruple(intArrayOf(1, 2, 3, 4, 5), 5, 3, listOf(1, 2, 3, 4, 5)),
        Quadruple(intArrayOf(1, 2, 3, 4, 5), 1, 3, listOf(3)),
        Quadruple(intArrayOf(2, 4, 5, 6, 10), 2, 7, listOf(5, 6)),
        Quadruple(intArrayOf(2, 4, 5, 6, 10), 2, 1, listOf(2, 4)),
        Quadruple(intArrayOf(2, 4, 5, 6, 10), 3, 10, listOf(5, 6, 10)),
        Quadruple(intArrayOf(-10, -5, 0, 5, 10), 3, 1, listOf(-5, 0, 5))
    )

    validateSolution(testCases, ::findClosestElements)
}
