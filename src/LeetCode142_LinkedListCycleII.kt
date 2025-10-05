import model.ListNode
import model.toCycleListNode
import model.toListNode

/**
 * 142: Linked List Cycle II
 * Medium
 * https://leetcode.com/problems/linked-list-cycle-ii/
 *
 * Given the head of a singly linked list, return the node where the cycle begins. If there is no cycle, return null.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the `next` pointer.
 * Internally, pos is used to denote the index of the node that the tail's `next` pointer connects to (0-indexed), where pos = -1 means there is no cycle. pos is not passed as a parameter.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: tail connects to node index 1
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: tail connects to node index 0
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: no cycle
 *
 * Constraints:
 * - The number of nodes in the list is the range [0, 10⁴] :contentReference[oaicite:0]{index=0}
 * - -10⁵ <= Node.val <= 10⁵ :contentReference[oaicite:1]{index=1}
 * - pos is -1 or a valid index in the linked list :contentReference[oaicite:2]{index=2}
 *
 * @param head the head of the singly linked list (may be null)
 * @return ListNode? the node where the cycle begins, or null if there is no cycle
 */
private fun detectCycleBruteForce(head: ListNode?): ListNode? {
    val set = mutableSetOf<ListNode>()
    var cursor = head
    while (cursor != null) {
        if (!set.add(cursor)) return cursor
        cursor = cursor.next
    }
    return null
}

private fun detectCycleTwoPointer(head: ListNode?): ListNode? {
    var slow = head
    var fast = head
    while (fast != null) {
        slow = slow?.next
        fast = fast.next?.next
        if (slow == fast) {
            fast = head
            while (slow != fast) {
                slow = slow?.next
                fast = fast?.next
            }
            return slow
        }
    }
    return null
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(listOf(3, 2, 0, -4).toCycleListNode(1), listOf(2, 0, -4).toCycleListNode(0)),
        Pair(listOf(1, 2).toCycleListNode(0), listOf(1, 2).toCycleListNode(0)),
        Pair(listOf(1).toCycleListNode(-1), null),

        // Additional cases
        Pair(emptyList<Int>().toListNode(), null),
        Pair(listOf(1).toListNode(), null),
        Pair(listOf(1, 2, 3, 4, 5).toCycleListNode(2), listOf(3, 4, 5).toCycleListNode(0)),
        Pair(listOf(1, 2, 3, 4, 5).toCycleListNode(3), listOf(4, 5).toCycleListNode(0)),
        Pair(listOf(1, 2, 3, 4, 5).toListNode(), null),
        Pair(listOf(1, 1, 1, 1).toCycleListNode(2), listOf(1, 1).toCycleListNode(0)),
        Pair(listOf(1, 2, 3).toCycleListNode(0), listOf(1, 2, 3).toCycleListNode(0))
    )

    val comparator: (ListNode?, ListNode?) -> Boolean = { expected, actual ->
        expected.toString() == actual.toString()

    }
    validateSolution(testCases, ::detectCycleBruteForce, comparator)
    printDivider()
    validateSolution(testCases, ::detectCycleTwoPointer, comparator)
}