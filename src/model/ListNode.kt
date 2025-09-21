package model

class ListNode(var value: Int, var next: ListNode? = null) {

    override fun toString(): String {
        val visitedNodes = mutableSetOf<ListNode>()
        var cur: ListNode? = this

        // Detecting cycle while building the string representation
        return buildString {
            while (cur != null) {
                if (visitedNodes.contains(cur)) {
                    append("Cycle detected at index ${visitedNodes.indexOf(cur)}")
                    break
                }

                visitedNodes.add(cur)
                append(cur.value)

                cur = cur.next
                if (cur != null) {
                    append(" -> ")
                }
            }
        }
    }

    fun last(): ListNode? {
        var result: ListNode? = this
        while (result?.next != null) {
            result = result.next
        }
        return result
    }

    operator fun get(pos: Int): ListNode? {
        var result: ListNode? = this
        repeat(pos) {
            result = result?.next
        }
        return result
    }
}

fun List<Int>.toCycleListNode(pos: Int): ListNode? {
    if (isEmpty()) return null
    val nodes = toListNode()
    nodes?.last()?.next = nodes[pos]
    return nodes
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