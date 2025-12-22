import model.Quadruple

/**
 * 2032: Two Out of Three
 * Easy
 * https://leetcode.com/problems/two-out-of-three/
 *
 * Given three arrays $nums1$, $nums2$, and $nums3$, return a distinct array of all the values that are present in at least two out of the three arrays.
 * You may return the result in any order.
 *
 * Example 1:
 * Input: nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
 * Output: [3,2]
 * Explanation:
 * The number 3 is present in all three arrays.
 * The number 2 is present in nums1 and nums2.
 *
 * Example 2:
 * Input: nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
 * Output: [2,3,1]
 * Explanation:
 * The number 2 is present in nums2 and nums3.
 * The number 3 is present in nums1 and nums2.
 * The number 1 is present in nums1 and nums3.
 *
 * Example 3:
 * Input: nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
 * Output: []
 * Explanation: No number is present in at least two arrays.
 *
 * Constraints:
 * $1 \le nums1.length, nums2.length, nums3.length \le 100$
 * $1 \le nums1[i], nums2[i], nums3[i] \le 1000$
 *
 * @param nums1 The first array of integers.
 * @param nums2 The second array of integers.
 * @param nums3 The third array of integers.
 * @return A list of distinct values present in at least two out of the three arrays.
 */
private fun twoOutOfThree(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
    val set1 = nums1.toSet()
    val set2 = nums2.toSet()
    val set3 = nums3.toSet()
    val set = set1 + set2 + set3
    return buildList {
        for (num in set) {
            if (num in set1 && num in set2 || num in set2 && num in set3 || num in set1 && num in set3) {
                add(num)
            }
        }
    }
}

fun main() {
    // Function takes 3 arguments (nums1, nums2, nums3), so we must use Quadruple for (arg1, arg2, arg3, expected)
    val testCases = listOf(
        // Official examples
        Quadruple(intArrayOf(1, 1, 3, 2), intArrayOf(2, 3), intArrayOf(3), listOf(3, 2)),
        Quadruple(intArrayOf(3, 1), intArrayOf(2, 3), intArrayOf(1, 2), listOf(3, 1, 2)),
        Quadruple(intArrayOf(1, 2, 2), intArrayOf(4, 3, 3), intArrayOf(5), listOf()),

        // Additional edge cases (min 10 total)
        // 1. All three arrays identical (small)
        Quadruple(intArrayOf(5, 5), intArrayOf(5), intArrayOf(5), listOf(5)),

        // 2. No overlap at all (small)
        Quadruple(intArrayOf(1), intArrayOf(2), intArrayOf(3), listOf()),

        // 3. Complete overlap of all elements
        Quadruple(intArrayOf(10, 20), intArrayOf(10, 20), intArrayOf(10, 20), listOf(10, 20)),

        // 4. Overlap only between 1 and 2
        Quadruple(intArrayOf(10, 11), intArrayOf(11, 12), intArrayOf(13), listOf(11)),

        // 5. Overlap only between 1 and 3
        Quadruple(intArrayOf(10, 11), intArrayOf(12), intArrayOf(11, 13), listOf(11)),

        // 6. Overlap only between 2 and 3
        Quadruple(intArrayOf(10), intArrayOf(11, 12), intArrayOf(12, 13), listOf(12)),

        // 7. Larger numbers (near constraint max)
        Quadruple(intArrayOf(990, 1000), intArrayOf(990, 999), intArrayOf(1000, 999), listOf(990, 1000, 999)),

        // 8. Large input, scattered overlap
        Quadruple(
            intArrayOf(1, 5, 10, 15),
            intArrayOf(5, 12, 15),
            intArrayOf(1, 10, 15),
            listOf(1, 5, 10, 15) // 1 (1,3), 5 (1,2), 10 (1,3), 15 (1,2,3)
        ),

        // 9. Repeated elements matter only for the set per array, not the final count
        Quadruple(
            intArrayOf(1, 1, 1, 2),
            intArrayOf(2, 2, 2, 3),
            intArrayOf(3, 3, 3, 1),
            listOf(1, 2, 3)
        ) // 1 (1,3), 2 (1,2), 3 (2,3)
    )

    validateSolution(testCases, ::twoOutOfThree)
}