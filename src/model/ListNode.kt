package model

data class ListNode(var value: Int, var next: ListNode? = null) {

    override fun toString(): String {
        var cur: ListNode? = this
        return buildString {
            while (cur != null) {
                append(cur.value)
                if (cur.next != null) {
                    append(" -> ")
                }
                cur = cur.next
            }
        }
    }
}

fun List<Int>.toListNode(): ListNode? {
    val dummy = ListNode(0)
    var curr = dummy
    for (v in this) {
        curr.next = ListNode(v)
        curr = curr.next!!
    }
    return dummy.next
}

fun ListNode?.toList(): List<Int> {
    val result = mutableListOf<Int>()
    var curr = this
    while (curr != null) {
        result.add(curr.value)
        curr = curr.next
    }
    return result
}