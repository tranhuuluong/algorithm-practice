import java.util.Stack

/**
 * 232: Implement Queue using Stacks
 * Easy
 * https://leetcode.com/problems/implement-queue-using-stacks/
 *
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
 * functions of a normal queue (`push`, `peek`, `pop`, and `empty`).
 *
 * Implement the `MyQueue` class:
 *  * `void push(int x)` Pushes element x to the back of the queue.
 *  * `int pop()` Removes the element from the front of the queue and returns it.
 *  * `int peek()` Returns the element at the front of the queue.
 *  * `boolean empty()` Returns `true` if the queue is empty, `false` otherwise.
 *
 * Example:
 * Input: ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output: [null, null, null, 1, 1, false]
 *
 * Constraints:
 *  * 1 <= x <= 9
 *  * At most 100 calls will be made to `push`, `pop`, `peek`, and `empty`.
 *  * All the calls to `pop` and `peek` are valid. :contentReference[oaicite:0]{index=0}
 *
 * @param none (constructor takes no arguments)
 * @return none
 */
class QueueUsingStack() {
    private val stack = Stack<Int>()

    fun push(x: Int) {
        val temp = Stack<Int>()
        while (!stack.empty()) temp.push(stack.pop())
        stack.push(x)
        while (!temp.empty()) stack.push(temp.pop())
    }

    fun pop(): Int = stack.pop()

    fun peek(): Int = stack.peek()

    fun empty(): Boolean = stack.empty()
}

class MyQueue() {

    private data class Node(val value: Int) {
        var next: Node? = null
    }

    private var head: Node? = null
    private var tail: Node? = null

    fun push(x: Int) {
        if (tail == null) {
            head = Node(x)
            tail = head
        } else {
            tail?.next = Node(x)
            tail = tail?.next
        }
    }

    fun pop(): Int {
        val value = head?.value
        head = head?.next
        if (head == null) tail = null
        return value ?: throw Exception("Queue is empty!")
    }

    fun peek(): Int = head?.value ?: throw Exception("Queue is empty!")

    fun empty(): Boolean = head == null
}

fun main() {
    val queue = QueueUsingStack()
    queue.push(1)
    queue.push(2)
    queue.push(3)
    assert(queue.peek() == 1)
    assert(!queue.empty())
    assert(queue.pop() == 1)
    assert(!queue.empty())
    assert(queue.pop() == 2)
    assert(!queue.empty())
    assert(queue.pop() == 3)
    assert(queue.empty())

    val myQueue = MyQueue()
    myQueue.push(1)
    myQueue.push(2)
    myQueue.push(3)
    assert(myQueue.peek() == 1)
    assert(!myQueue.empty())
    assert(myQueue.pop() == 1)
    assert(!myQueue.empty())
    assert(myQueue.pop() == 2)
    assert(!myQueue.empty())
    assert(myQueue.pop() == 3)
    assert(myQueue.empty())
}
