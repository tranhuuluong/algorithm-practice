import kotlin.math.log10
import kotlin.math.max

/**
 * 3043: Find the Length of the Longest Common Prefix
 * Medium
 * https://leetcode.com/problems/find-the-length-of-the-longest-common-prefix/
 *
 * You are given two arrays with positive integers arr1 and arr2.
 * A prefix of a positive integer is an integer formed by one or more of its digits,
 * starting from its leftmost digit. A common prefix of two integers a and b is an integer c,
 * such that c is a prefix of both a and b.
 * You need to find the length of the longest common prefix among all pairs (x, y),
 * where x ∈ arr1 and y ∈ arr2. If no common prefix exists, return 0.
 *
 * Example 1:
 * Input: arr1 = [1,10,100], arr2 = [1000]
 * Output: 3
 *
 * Example 2:
 * Input: arr1 = [1,2,3], arr2 = [4,4,4]
 * Output: 0
 *
 * Constraints:
 *  - 1 <= arr1.length, arr2.length <= 5 * 10^4
 *  - 1 <= arr1[i], arr2[i] <= 10^8
 *
 * @param arr1 the first array of positive integers
 * @param arr2 the second array of positive integers
 * @return the maximum length of any common prefix between elements of arr1 and arr2
 */
private fun longestCommonPrefixLength(arr1: IntArray, arr2: IntArray): Int {
    fun Int.digits(): Int {
        var digits = 0
        var num = this
        while (num > 0) {
            digits++
            num /= 10
        }
        return digits
    }

    val prefixes = mutableSetOf<Int>()
    var result = 0

    for (num in arr1) {
        var num = num
        while (num > 0) {
            prefixes.add(num)
            num /= 10
        }
    }

    for (num in arr2) {
        var num = num
        while (num !in prefixes && num > 0) {
            num /= 10
        }

        result = max(result, num.digits())
    }
    return result
}

private fun longestCommonPrefixLength2(arr1: IntArray, arr2: IntArray): Int {
    val prefixes = mutableSetOf<Int>()
    var result = 0

    for (num in arr1) {
        var num = num
        while (num > 0) {
            prefixes.add(num)
            num /= 10
        }
    }

    for (num in arr2) {
        var num = num
        while (num !in prefixes && num > 0) {
            num /= 10
        }

        result = max(result, log10(num.toDouble()).toInt() + 1)
    }
    return result
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1, 10, 100), intArrayOf(1000), 3),
        Triple(intArrayOf(1, 2, 3), intArrayOf(4, 4, 4), 0),
        // Additional realistic test cases
        Triple(intArrayOf(123, 456), intArrayOf(1234, 123), 3),
        Triple(intArrayOf(98765, 98), intArrayOf(987, 980), 3),
        Triple(intArrayOf(10, 100, 1000), intArrayOf(10000, 100), 4),
        Triple(intArrayOf(1), intArrayOf(1), 1),
        Triple(intArrayOf(12, 123, 1234), intArrayOf(124, 125), 2),
        Triple(intArrayOf(55, 56, 57), intArrayOf(58, 59), 1),
        Triple(intArrayOf(111, 222, 333), intArrayOf(444, 555), 0),
        Triple(intArrayOf(1000000), intArrayOf(100, 1000, 10000), 5)
    )

    validateSolution(testCases, ::longestCommonPrefixLength)
    printDivider()
    validateSolution(testCases, ::longestCommonPrefixLength2)
}
