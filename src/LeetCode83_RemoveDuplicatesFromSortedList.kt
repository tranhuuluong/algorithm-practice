import model.ListNode
import model.toListNode

/**
 * 83: Remove Duplicates from Sorted List
 * Easy
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 *
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
 * Return the linked list sorted as well.
 *
 * Example 1:
 * Input: head = [1,1,2]
 * Output: [1,2]
 *
 * Example 2:
 * Input: head = [1,1,2,3,3]
 * Output: [1,2,3]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 300]
 * - -100 <= Node.val <= 100
 * - The list is guaranteed to be sorted in ascending order :contentReference[oaicite:0]{index=0}
 *
 * @param head the head of the sorted singly linked list (may be null)
 * @return ListNode? the head of the list after removing duplicates
 */
private fun deleteDuplicates(head: ListNode?): ListNode? {
    val result = ListNode(0)
    var cur = head
    var prev = head
    result.next = prev
    while (cur != null) {
        cur = cur.next
        if (cur?.value != prev?.value) {
            prev?.next = cur
            prev = prev?.next
        }
    }
    return result.next
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(listOf(1,1,2).toListNode(), listOf(1,2).toListNode()),
        Pair(listOf(1,1,2,3,3).toListNode(), listOf(1,2,3).toListNode()),
        // Additional cases
        Pair(emptyList<Int>().toListNode(), emptyList<Int>().toListNode()),
        Pair(listOf(1).toListNode(), listOf(1).toListNode()),
        Pair(listOf(1,1,1,1).toListNode(), listOf(1).toListNode()),
        Pair(listOf(1,2,3).toListNode(), listOf(1,2,3).toListNode()),
        Pair(listOf(1,1,2,2,3,3).toListNode(), listOf(1,2,3).toListNode()),
        Pair(listOf(-1, -1, 0, 0, 0, 1).toListNode(), listOf(-1,0,1).toListNode()),
        Pair(listOf(-100, -100, -50, -50, 0).toListNode(), listOf(-100, -50, 0).toListNode()),
        Pair(listOf(1,1,1,2,3,3,4).toListNode(), listOf(1,2,3,4).toListNode())
    )

    validateSolution(testCases, ::deleteDuplicates)
}
