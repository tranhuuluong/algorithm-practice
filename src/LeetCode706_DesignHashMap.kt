/**
 * 706: Design HashMap
 * Easy
 * https://leetcode.com/problems/design-hashmap/
 *
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the `MyHashMap` class:
 *  * `MyHashMap()` initializes the object with an empty map.
 *  * `void put(int key, int value)` inserts a (key, value) pair into the HashMap. If the key already exists, update the value.
 *  * `int get(int key)` returns the value to which the specified key is mapped, or `-1` if this map contains no mapping for the key.
 *  * `void remove(int key)` removes the mapping for the key if the map contains it.
 *
 * Example:
 * ```
 * MyHashMap hashMap = new MyHashMap();
 * hashMap.put(1, 1);
 * hashMap.put(2, 2);
 * hashMap.get(1);    // returns 1
 * hashMap.get(3);    // returns -1 (not found)
 * hashMap.put(2, 1); // update the existing value
 * hashMap.get(2);    // returns 1
 * hashMap.remove(2); // remove the mapping for 2
 * hashMap.get(2);    // returns -1 (not found)
 * ```
 *
 * Constraints:
 * - 0 <= key, value <= 10⁶ :contentReference[oaicite:0]{index=0}
 * - At most 10⁴ calls will be made to put, get, and remove. :contentReference[oaicite:1]{index=1}
 *
 * @param none (constructor takes no arguments)
 * @return none (operations mutate internal state)
 */
class MyHashMap() {

    private data class Node(val key: Int, var value: Int) {
        var next: Node? = null
    }

    private val capacity = 1003
    private val buckets = Array<Node?>(capacity) { null }

    private fun hash(key: Int): Int {
        return key % capacity
    }

    fun put(key: Int, value: Int) {
        val index = hash(key)
        var node = buckets[index]
        if (containsKey(key)) {
            while (node != null) {
                if (node.key == key) {
                    node.value = value
                }
                node = node.next
            }
        } else {
            if (node == null) {
                buckets[index] = Node(key, value)
            } else {
                while (node?.next != null) {
                    node = node.next
                }
                node?.next = Node(key, value)
            }
        }
    }

    fun get(key: Int): Int {
        var node = buckets[hash(key)]
        while (node != null) {
            if (node.key == key) {
                return node.value
            }
            node = node.next
        }
        return -1
    }

    fun remove(key: Int) {
        val index = hash(key)
        var node = buckets[index]
        var prev: Node? = null
        while (node != null) {
            if (node.key == key) {
                if (prev == null) {
                    node = node.next
                    buckets[index] = node
                } else {
                    prev.next = node.next
                }
                return
            }
            prev = node
            node = node.next
        }
    }

    fun containsKey(key: Int): Boolean {
        var node = buckets[hash(key)]
        while (node != null) {
            if (node.key == key) {
                return true
            }
            node = node.next
        }
        return false
    }
}

fun main() {
    val map = MyHashMap()
    map.put(1, 2)
    assert(map.get(1) == 2)
    map.put(1, 3)
    assert(map.get(1) == 3)
    map.remove(1)
    assert(!map.containsKey(1))
    map.put(2, 3)
    map.put(3, 4)
    assert(map.get(2) == 3)
    assert(map.get(3) == 4)
}
