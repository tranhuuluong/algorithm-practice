/**
 * 705: Design HashSet
 * Easy
 * https://leetcode.com/problems/design-hashset/
 *
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSet class:
 *  * void add(key) Inserts the value key into the HashSet.
 *  * bool contains(key) Returns whether the value key exists in the HashSet or not.
 *  * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 *
 * Example 1:
 * Input
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 *
 * Constraints:
 * - 0 <= key <= 10^6 :contentReference[oaicite:0]{index=0}
 * - At most 10^4 calls will be made to add, remove, and contains. :contentReference[oaicite:1]{index=1}
 *
 * @param capacity internal capacity if needed (constructor parameter for MyHashSet)
 * @return none (operations mutate state)
 */
class MyHashSet {

    private data class Node(val value: Int) {
        var next: Node? = null
    }

    private val capacity = 1000003

    private val buckets = Array<Node?>(capacity) {
        null
    }

    private fun hash(key: Int): Int {
        return key % capacity
    }

    fun add(key: Int) : Boolean {
        if (contains(key)) {
            return false
        }

        var node = buckets[hash(key)]
        if (node == null) {
            buckets[hash(key)] = Node(key)
        } else {
            while (node?.next != null) {
                node = node.next
            }
            node?.next = Node(key)
        }
        return true
    }

    fun remove(key: Int) : Boolean {
        val index = hash(key)
        var node = buckets[index]
        var prev: Node? = null
        while (node != null) {
            if (node.value == key) {
                if (prev == null) {
                    buckets[index] = node.next
                } else {
                    prev.next = node.next
                }
                return true
            }
            prev = node
            node = node.next
        }
        return false
    }

    fun contains(key: Int): Boolean {
        var node = buckets[hash(key)]
        while (node != null) {
            if (node.value == key) {
                return true
            }
            node = node.next
        }
        return false
    }

}


fun main() {
    val set = MyHashSet()
    assert(set.add(1))
    assert(set.contains(1))
    assert(set.add(2))
    assert(set.contains(2))
    assert(set.add(3))
    assert(!set.add(3))
    assert(set.remove(2))
    assert(!set.contains(2))
}