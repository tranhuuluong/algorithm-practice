/**
 * 146: LRU Cache
 * Medium
 * https://leetcode.com/problems/lru-cache/
 *
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the `LRUCache` class:
 * - `LRUCache(int capacity)` Initialize the LRU cache with positive size `capacity`.
 * - `int get(int key)` Return the value of the `key` if the key exists, otherwise return `-1`.
 * - `void put(int key, int value)` Update the value of the `key` if the `key` exists. Otherwise, add the `key-value` pair to the cache. If the number of keys exceeds the `capacity` from this operation, evict the least recently used key.
 *
 * Example:
 * ```
 * Input
 * ["LRUCache","put","put","get","put","get","put","get","get","get"]
 * [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
 * Output
 * [null,null,null,1,null,-1,null,-1,3,4]
 * ```
 *
 * Constraints:
 * - 1 <= capacity <= 3000 :contentReference[oaicite:0]{index=0}
 * - 0 <= key <= 10⁴ :contentReference[oaicite:1]{index=1}
 * - 0 <= value <= 10⁵ :contentReference[oaicite:2]{index=2}
 * - At most 2 × 10⁵ calls will be made to `get` and `put`. :contentReference[oaicite:3]{index=3}
 *
 * @param capacity the maximum number of items the cache can hold
 * @return none (LRUCache object supports get and put operations)
 */
class LRUCache(val capacity: Int) {

    data class Node(val key: Int, val value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    private val cache = mutableMapOf<Int, Node>()
    private val head = Node(0, 0)
    private val tail = Node(0, 0)

    init {
        head.next = tail
        tail.prev = head
    }

    fun get(key: Int): Int {
        if (!cache.containsKey(key)) return -1
        val node = cache[key]!!
        remove(node)
        addToFront(node)
        return node.value
    }

    fun put(key: Int, value: Int) {
        if (cache.contains(key)) {
            remove(cache[key]!!)
        }
        val node = Node(key, value)
        addToFront(node)
        cache[key] = node

        if (cache.size > capacity) {
            val lru = tail.prev!!
            remove(lru)
            cache.remove(lru.key)
        }
    }

    fun peekFirst(): Int? = cache[head.next?.key]?.value

    fun peekLast(): Int? = cache[tail.prev?.key]?.value

    private fun remove(node: Node) {
        val next = node.next
        val prev = node.prev
        prev?.next = next
        next?.prev = prev
    }

    // 0 <-> 1 <-> 0
    private fun addToFront(node: Node) {
        val next = head.next
        next?.prev = node
        node.next = next
        head.next = node
        node.prev = head
    }
}

fun main() {
    val cache = LRUCache(3)
    cache.put(1, 1)
    cache.put(2, 2)
    cache.put(3, 3)

    // 3-2-1
    assert(cache.peekFirst() == 3)
    assert(cache.peekLast() == 1)

    // 4-3-2
    cache.put(4, 4)

    assert(cache.peekFirst() == 4)
    assert(cache.peekLast() == 2)
    assert(cache.get(1) == -1)

    // 2-4-3
    cache.get(2)

    assert(cache.peekFirst() == 2)
    assert(cache.peekLast() == 3)
}