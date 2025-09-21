import model.ListNode
import model.toCycleListNode
import model.toListNode

/**
 * 141: Linked List Cycle
 * Easy
 * https://leetcode.com/problems/linked-list-cycle/
 *
 * Given the head of a singly linked list, determine if the linked list has a cycle in it.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer.
 * Internally, `pos` is used to denote the index of the node that the tail connects to (0-indexed), but `pos` is not passed as a parameter.
 * Return `true` if there is a cycle in the linked list. Otherwise, return `false`.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 10⁴] :contentReference[oaicite:0]{index=0}
 * - -10⁵ <= Node.val <= 10⁵ :contentReference[oaicite:1]{index=1}
 * - pos is -1 or a valid index in the linked-list :contentReference[oaicite:2]{index=2}
 *
 * @param head the head of the singly linked list (may be null)
 * @return Boolean `true` if there is a cycle, `false` otherwise
 */
fun hasCycle(head: ListNode?): Boolean {
    if (head == null || head.next == null) return false
    var slow = head
    var fast = head
    while (fast != null) {
        slow = slow?.next
        fast = fast.next?.next
        if (slow == fast) return true
    }
    return false
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(listOf(3,2,0,-4).toCycleListNode(1), true),
        Pair(listOf(1,2).toCycleListNode(0), true),
        Pair(listOf(1).toListNode(), false),
        // Additional cases
        Pair(emptyList<Int>().toListNode(), false),
        Pair(listOf(1).toCycleListNode(0), true),
        Pair(listOf(1,2,3,4,5).toCycleListNode(2), true),
        Pair(listOf(1,2,3,4,5).toCycleListNode(4), true),
        Pair(listOf(1,2,3,4,5).toListNode(), false),
        Pair(listOf(1,1,1,1).toCycleListNode(2), true),
        Pair(listOf(1,2,3).toCycleListNode(0), true),
        Pair(listOf(-1,-2,-3,-4).toCycleListNode(1), true),
    )

    validateSolution(testCases, ::hasCycle)
}