import model.ListNode
import model.toListNode

/**
 * 3217: Delete Nodes From Linked List Present in Array
 * Medium
 * https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/
 *
 * You are given an array of integers `nums` and the `head` of a linked list. Return the `head` of the modified linked list after removing all nodes from the linked list that have a value that exists in `nums`. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: nums = [1,2,3], head = [1,2,3,4,5]
 * Output: [4,5]
 *
 * Example 2:
 * Input: nums = [1], head = [1,2,1,2,1,2]
 * Output: [2,2,2]
 *
 * Example 3:
 * Input: nums = [5], head = [1,2,3,4]
 * Output: [1,2,3,4]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5 :contentReference[oaicite:1]{index=1}
 * 1 <= nums[i] <= 10^5 :contentReference[oaicite:2]{index=2}
 * All elements in nums are unique. :contentReference[oaicite:3]{index=3}
 * The number of nodes in the given list is in the range [1, 10^5]. :contentReference[oaicite:4]{index=4}
 * 1 <= Node.val <= 10^5 :contentReference[oaicite:5]{index=5}
 * The input is generated such that there is at least one node in the linked list that has a value not present in `nums`. :contentReference[oaicite:6]{index=6}
 *
 * @param nums the array of values to remove from the list
 * @param head the head of the singly-linked list
 * @return the head of the modified linked list after all removals
 */
private fun deleteNodes(nums: IntArray, head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var cur = head
    val numSet = nums.toSet()
    var ans: ListNode? = null
    while (cur != null) {
        if (cur.value !in numSet) {
            if (prev == null) {
                ans = cur
            }
            prev?.next = cur
            prev = cur
        } else {
            prev?.next = cur.next
        }
        cur = cur.next
    }
    return ans
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1,2,3), listOf(1,2,3,4,5).toListNode(), listOf(4,5).toListNode()),
        Triple(intArrayOf(1), listOf(1,2,1,2,1,2).toListNode(), listOf(2,2,2).toListNode()),
        Triple(intArrayOf(5), listOf(1,2,3,4).toListNode(), listOf(1,2,3,4).toListNode()),
        // Additional valid test cases
        Triple(intArrayOf(2,4), listOf(1,2,3,4,5,6).toListNode(), listOf(1,3,5,6).toListNode()),
        Triple(intArrayOf(1,3,5), listOf(1,1,2,3,4,5,6,5).toListNode(), listOf(2,4,6).toListNode()),
        Triple(intArrayOf(10), listOf(10,10,10,10,11).toListNode(), listOf(11).toListNode()),
        Triple(intArrayOf(7,8), listOf(1,7,2,8,3,7,4,8,5).toListNode(), listOf(1,2,3,4,5).toListNode()),
        Triple(intArrayOf(100), listOf(100,1,100,2,3,100,4).toListNode(), listOf(1,2,3,4).toListNode()),
        Triple(intArrayOf(9,2), listOf(1,2,9,2,3,9,4,5).toListNode(), listOf(1,3,4,5).toListNode()),
        Triple(intArrayOf(3), listOf(3,3,3,4,5,3,6).toListNode(), listOf(4,5,6).toListNode())
    )

    validateSolution(testCases, ::deleteNodes)
}