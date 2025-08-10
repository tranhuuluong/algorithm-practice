import java.util.PriorityQueue

/**
 * 347: Top K Frequent Elements
 * Medium
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= number of unique elements in the array
 * It is guaranteed that the answer is unique.
 *
 * @param nums the array of integers from which to find the most frequent elements
 * @param k the number of top frequent elements to return
 * @return a list of the k most frequent elements (order may be arbitrary)
 */
private fun topKFrequent(nums: IntArray, k: Int): IntArray {
    if (nums.size == 1) return intArrayOf(nums.first())

    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }

    return map.toList()
        .sortedByDescending { (_, value) -> value }
        .take(k)
        .map { (key, _) -> key }
        .toIntArray()
}

// Min-heap
private fun topKFrequent2(nums: IntArray, k: Int): IntArray {
    if (nums.size == 1) return intArrayOf(nums.first())

    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }

    val heap = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })
    for ((num, freq) in map) {
        heap.add(num to freq)
    }

    val res = IntArray(k)
    for (i in 0 until k) {
        res[i] = heap.poll().first
    }

    return res
}

//Min-heap optimized
private fun topKFrequent3(nums: IntArray, k: Int): IntArray {
    if (nums.size == 1) return intArrayOf(nums.first())

    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }

    val heap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    for ((num, freq) in map) {
        heap.add(freq to num)
        if (heap.size > k) {
            heap.poll()
        }
    }

    val res = IntArray(k)
    for (i in 0 until k) {
        res[i] = heap.poll().second
    }

    return res
}

private fun topKFrequent4(nums: IntArray, k: Int): IntArray {
    if (nums.size == 1) return intArrayOf(nums.first())

    val map = mutableMapOf<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }

    val list = List(nums.size) { mutableListOf<Int>() }
    for ((num, freq) in map) {
        list[freq].add(num)
    }

    val res = mutableListOf<Int>()
    for (i in list.lastIndex downTo 0) {
        for (num in list[i]) {
            res.add(num)
            if (res.size == k) return res.toIntArray()
        }
    }

    return intArrayOf()
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1, 1, 1, 2, 2, 3), 2, intArrayOf(1, 2)),
        Triple(intArrayOf(1), 1, intArrayOf(1)),
        // Additional valid test cases (unique top k)
        Triple(intArrayOf(4, 4, 4, 1, 1, 2), 2, intArrayOf(4, 1)),
        Triple(intArrayOf(5, 5, 5, 4, 4, 3, 3, 3, 3), 1, intArrayOf(3)),
        Triple(intArrayOf(6, 6, 6, 6, 2, 2, 2, 1), 2, intArrayOf(6, 2)),
        Triple(intArrayOf(10, 10, 10, 8, 8, 8, 7), 2, intArrayOf(10, 8)),
        Triple(intArrayOf(10, 10, 10, 8, 8, 7, 7, 7, 7), 2, intArrayOf(7, 10)),
        Triple(intArrayOf(-1, -1, -1, 0, 0, 0, 0, 1), 2, intArrayOf(0, -1)),
        Triple(intArrayOf(10000, 10000, 10000, -10000, -10000, 0), 2, intArrayOf(10000, -10000)),
        Triple(intArrayOf(1, 1, 1, 2, 2, 3), 3, intArrayOf(1, 2, 3))
    )

    // compare ignoring order
    val comparator: (IntArray, IntArray) -> Boolean = { expected, actual ->
        expected.sorted() == actual.sorted()
    }
    validateSolution(testCases, ::topKFrequent, comparator)
    printDivider()
    validateSolution(testCases, ::topKFrequent2, comparator)
    printDivider()
    validateSolution(testCases, ::topKFrequent3, comparator)
    printDivider()
    validateSolution(testCases, ::topKFrequent4, comparator)
}
