/**
 * 496: Next Greater Element I
 * Easy
 * https://leetcode.com/problems/next-greater-element-i/
 *
 * You are given two distinct integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each element in nums1, find the first greater element to its right in nums2. If it does not exist, return -1 for that element.
 *
 * Example 1:
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 *
 * Example 2:
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 *
 * Constraints:
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10^4
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2. :contentReference[oaicite:0]{index=0}
 *
 * @param nums1 the subset array
 * @param nums2 the superset array
 * @return an IntArray where each element is the next greater element of nums1[i] in nums2 or -1 if none exists
 */
private fun nextGreaterElement(nums1: IntArray, nums2: IntArray): IntArray {
    val numToPos = buildMap {
        nums2.forEachIndexed { index, num ->
            put(num, index)
        }
    }
    val ans = IntArray(nums1.size)
    for (i in nums1.indices) {
        val index = numToPos[nums1[i]]!!
        for (j in index + 1..nums2.lastIndex) {
            if (nums2[j] > nums1[i]) {
                ans[i] = nums2[j]
                break
            }
        }
        if (ans[i] == 0) ans[i] = -1
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(4,1,2), intArrayOf(1,3,4,2), intArrayOf(-1,3,-1)),  // Example 1
        Triple(intArrayOf(2,4), intArrayOf(1,2,3,4), intArrayOf(3,-1)),         // Example 2
        Triple(intArrayOf(1), intArrayOf(1), intArrayOf(-1)),                   // single element no greater
        Triple(intArrayOf(3), intArrayOf(2,3,1), intArrayOf(-1)),               // element has no right greater in nums2
        Triple(intArrayOf(2,1), intArrayOf(1,2), intArrayOf(-1,2)),             // two elements subset
        Triple(intArrayOf(5,3), intArrayOf(3,5,4,2,1), intArrayOf(-1,5)),       // 5 no greater, 3 greater is 5 (but subset order)
        Triple(intArrayOf(9,8), intArrayOf(1,8,9,2), intArrayOf(-1,9)),        // both have no greater to their right
        Triple(intArrayOf(6,1), intArrayOf(6,5,1,8), intArrayOf(8,8)),          // 6->8,1->8
        Triple(intArrayOf(100), intArrayOf(100,99,98), intArrayOf(-1)),         // decreasing sequence
        Triple(intArrayOf(0,1), intArrayOf(0,1,2,3), intArrayOf(1,2))           // both have next greater
    )

    validateSolution(testCases, ::nextGreaterElement)
}
