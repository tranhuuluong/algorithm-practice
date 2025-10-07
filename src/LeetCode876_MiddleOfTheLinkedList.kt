import model.ListNode
import model.toListNode

/**
 * 876: Middle of the Linked List
 * Easy
 * https://leetcode.com/problems/middle-of-the-linked-list/
 *
 * You are given the head of a singly linked list. Return the middle node of the linked list.
 * If there are two middle nodes, return the second middle node. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 *
 * Example 2:
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 *
 * Constraints:
 * - The number of nodes in the list will be between 1 and 100. :contentReference[oaicite:1]{index=1}
 * - 1 <= Node.val <= 100 :contentReference[oaicite:2]{index=2}
 *
 * @param head the head of the singly linked list (non-null)
 * @return ListNode? the middle node of the linked list; if two, return the second middle
 */
private fun middleNode(head: ListNode?): ListNode? {
    var slow = head
    var fast = head
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }
    return slow
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(listOf(1,2,3,4,5).toListNode(), listOf(3,4,5).toListNode()),
        Pair(listOf(1,2,3,4,5,6).toListNode(), listOf(4,5,6).toListNode()),
        // Additional cases
        Pair(listOf(1).toListNode(), listOf(1).toListNode()),
        Pair(listOf(1,2).toListNode(), listOf(2).toListNode()),
        Pair(listOf(1,2,3).toListNode(), listOf(2,3).toListNode()),
        Pair(listOf(1,2,3,4).toListNode(), listOf(3,4).toListNode()),
        Pair(listOf(10,20,30,40,50,60,70).toListNode(), listOf(40,50,60,70).toListNode()),
        Pair(listOf(5,4,3,2,1).toListNode(), listOf(3,2,1).toListNode()),
        Pair(listOf(100, 200).toListNode(), listOf(200).toListNode()),
        Pair(listOf(9,9,9,9,9,9,9,9).toListNode(), listOf(9,9,9,9).toListNode())
    )

    validateSolution(testCases, ::middleNode)
}