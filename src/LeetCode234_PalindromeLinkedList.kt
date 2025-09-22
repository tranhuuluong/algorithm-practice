import model.ListNode
import model.toListNode

/**
 * 234: Palindrome Linked List
 * Easy
 * https://leetcode.com/problems/palindrome-linked-list/
 *
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 * Example 1:
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 * Input: head = [1,2]
 * Output: false
 *
 * Constraints:
 * - The number of nodes in the list is in the range [1, 10^5] :contentReference[oaicite:0]{index=0}
 * - 0 <= Node.val <= 9 :contentReference[oaicite:1]{index=1}
 * Follow up: Could you do it in O(n) time and O(1) space? :contentReference[oaicite:2]{index=2}
 *
 * @param head the head of the singly linked list (non-null)
 * @return Boolean true if the linked list is a palindrome, false otherwise
 */
private fun isPalindrome(head: ListNode?): Boolean {
    fun reverse(node: ListNode?): ListNode? {
        var newHead: ListNode? = null
        var head = node
        while (head != null) {
            val next = head.next
            head.next = newHead
            newHead = head
            head = next
        }
        return newHead
    }

    var slow = head
    var fast = head
    while (slow != null && fast != null) {
        slow = slow.next
        fast = fast.next?.next
    }
    slow = reverse(slow)
    fast = head
    while (slow != null) {
        if (slow.value != fast?.value) return false
        slow = slow.next
        fast = fast.next
    }
    return true
}

fun main() {
    val testCases = listOf(
        // Official examples
        Pair(listOf(1,2,2,1).toListNode(), true),
        Pair(listOf(1,2).toListNode(), false),
        // Additional cases
        Pair(listOf(1).toListNode(), true),
        Pair(listOf(1,2,1).toListNode(), true),
        Pair(listOf(1,2,3,2,1).toListNode(), true),
        Pair(listOf(1,2,3,4,3,2,1).toListNode(), true),
        Pair(listOf(1,2,3,4,2,1).toListNode(), false),
        Pair(listOf(0,0).toListNode(), true),
        Pair(listOf(1,1,2).toListNode(), false),
        Pair(listOf(2,1,1,2).toListNode(), true),
        Pair(listOf(9,8,7,8,9).toListNode(), true)
    )

    validateSolution(testCases, ::isPalindrome)
}
