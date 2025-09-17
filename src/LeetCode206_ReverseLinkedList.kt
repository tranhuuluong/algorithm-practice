import model.ListNode
import model.toListNode

/**
 * 206: Reverse Linked List
 * Easy
 * https://leetcode.com/problems/reverse-linked-list/
 *
 * You are given the head of a singly linked list. Reverse the list, and return the reversed list.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * Example 3:
 * Input: head = []
 * Output: []
 *
 * Constraints:
 * - The number of nodes in the list is the range [0, 5000]
 * - -5000 <= Node.val <= 5000
 *
 * @param head the head of the singly linked list (may be null)
 * @return ListNode? the head of the reversed linked list
 */
private fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var cur = head
    while (cur != null) {
        val next = cur.next
        cur.next = prev
        prev = cur
        cur = next
    }
    return prev
}

private fun reverseListRecursion(head: ListNode?): ListNode? {
    fun revertList(head: ListNode?, prev: ListNode?): ListNode? {
        if (head == null) return prev

        val next = head.next
        head.next = prev
        return revertList(next, head)
    }
    return revertList(head, null)
}

fun main() {
    validateSolution(testCases(), ::reverseList)
    printDivider()
    validateSolution(testCases(), ::reverseListRecursion)
}

private fun testCases() = listOf(
    // Official examples
    Pair(listOf(1,2,3,4,5).toListNode(), listOf(5,4,3,2,1).toListNode()),
    Pair(listOf(1,2).toListNode(), listOf(2,1).toListNode()),
    Pair(emptyList<Int>().toListNode(), emptyList<Int>().toListNode()),
    // Additional cases
    Pair(listOf(1).toListNode(), listOf(1).toListNode()),
    Pair(listOf(1,2,3).toListNode(), listOf(3,2,1).toListNode()),
    Pair(listOf(-1,0,1).toListNode(), listOf(1,0,-1).toListNode()),
    Pair(listOf(5,4,3,2,1).toListNode(), listOf(1,2,3,4,5).toListNode()),
    Pair(listOf(1,1,1).toListNode(), listOf(1,1,1).toListNode()),
    Pair(listOf(2,1,0,-1).toListNode(), listOf(-1,0,1,2).toListNode()),
    Pair(listOf(1000, 2000, 3000).toListNode(), listOf(3000,2000,1000).toListNode())
)

