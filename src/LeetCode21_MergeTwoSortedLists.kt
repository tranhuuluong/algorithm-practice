import model.ListNode
import model.toListNode

/**
 * 21: Merge Two Sorted Lists
 * Easy
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 * Constraints:
 * - The number of nodes in both lists is in the range [0, 50] :contentReference[oaicite:0]{index=0}
 * - -100 <= Node.val <= 100 :contentReference[oaicite:1]{index=1}
 * - Both list1 and list2 are sorted in non-decreasing order :contentReference[oaicite:2]{index=2}
 *
 * @param list1 the head of the first sorted linked list (may be null)
 * @param list2 the head of the second sorted linked list (may be null)
 * @return ListNode? the head of the merged sorted linked list
 */
private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    var p1 = list1
    var p2 = list2
    val result = ListNode(0)
    var cursor: ListNode? = result
    while (p1 != null && p2 != null) {
        if (p1.value < p2.value) {
            cursor?.next = p1
            p1 = p1.next
        } else {
            cursor?.next = p2
            p2 = p2.next
        }
        cursor = cursor?.next
    }
    when {
        p1 != null -> cursor?.next = p1
        p2 != null -> cursor?.next = p2
    }
    return result.next
}

fun main() {
    val testCases = listOf(
        // Official examples
        Triple(listOf(1,2,4).toListNode(), listOf(1,3,4).toListNode(), listOf(1,1,2,3,4,4).toListNode()),
        Triple(emptyList<Int>().toListNode(), emptyList<Int>().toListNode(), emptyList<Int>().toListNode()),
        Triple(emptyList<Int>().toListNode(), listOf(0).toListNode(), listOf(0).toListNode()),
        // Additional cases
        Triple(listOf(1).toListNode(), listOf(2).toListNode(), listOf(1,2).toListNode()),
        Triple(listOf(2).toListNode(), listOf(1).toListNode(), listOf(1,2).toListNode()),
        Triple(listOf(1,1,2).toListNode(), listOf(1,1,2).toListNode(), listOf(1,1,1,1,2,2).toListNode()),
        Triple(listOf(-10, -5, 0, 5).toListNode(), listOf(-7, -3, 2, 10).toListNode(), listOf(-10, -7, -5, -3, 0, 2, 5, 10).toListNode()),
        Triple(listOf(1,3,5,7).toListNode(), listOf(2,4,6,8).toListNode(), listOf(1,2,3,4,5,6,7,8).toListNode()),
        Triple(listOf(1,2,3).toListNode(), emptyList<Int>().toListNode(), listOf(1,2,3).toListNode()),
        Triple(listOf(4,5,6).toListNode(), listOf(1,2,3).toListNode(), listOf(1,2,3,4,5,6).toListNode())
    )

    validateSolution(testCases, ::mergeTwoLists)
}