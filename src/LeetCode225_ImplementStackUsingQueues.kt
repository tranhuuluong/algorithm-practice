import java.util.LinkedList
import java.util.Queue

/**
 * 225: Implement Stack using Queues
 * Easy
 * https://leetcode.com/problems/implement-stack-using-queues/
 *
 * Implement a last-in-first-out (LIFO) stack using only queues. The implemented stack should support all the
 * functions of a normal stack (`push`, `top`, `pop`, and `empty`).
 *
 * MyStack operations:
 *  * `void push(int x)` Pushes element x onto stack.
 *  * `int pop()` Removes the element on top of the stack and returns it.
 *  * `int top()` Returns the element on top of the stack.
 *  * `boolean empty()` Returns `true` if the stack is empty, `false` otherwise.
 *
 * Notes:
 *  * You must use only standard operations of a queue — which means only `push to back`, `peek/pop from front`, `size`, and `is empty` operations are valid. :contentReference[oaicite:0]{index=0}
 *  * You may assume that all calls to `pop` and `top` are valid. :contentReference[oaicite:1]{index=1}
 *
 * Example 1:
 * Input:
 *   ["MyStack", "push", "push", "top", "pop", "empty"]
 *   [[], [1], [2], [], [], []]
 * Output:
 *   [null, null, null, 2, 2, false] :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 *  * 1 ≤ x ≤ 9 :contentReference[oaicite:3]{index=3}
 *  * At most 100 calls will be made to `push`, `pop`, `top`, and `empty`. :contentReference[oaicite:4]{index=4}
 *
 * @param capacity unused (constructor for MyStack)
 * @return none (operations modify internal state or return values)
 */
class StackUsingQueue() {
    private val queue: Queue<Int> = LinkedList()

    // (0(n))
    fun push(x: Int) {
        queue.offer(x)
        repeat(queue.size - 1) {
            queue.offer(queue.poll())
        }
    }

    fun pop(): Int {
        return queue.poll()
    }

    fun top(): Int {
        return queue.peek()
    }

    fun empty(): Boolean {
        return queue.isEmpty()
    }
}

class MyStack {

    private data class Node(val value: Int) {
        var next: Node? = null
    }

    private var head: Node? = null

    fun push(x: Int) {
        val node = Node(x)
        node.next = head
        head = node
    }

    fun pop(): Int {
        val value = head?.value
        head = head?.next
        return value ?: throw Exception("Stack is empty!")
    }

    fun top(): Int {
        return head?.value ?: throw Exception("Stack is empty!")
    }

    fun empty(): Boolean {
        return head == null
    }
}

fun main() {
    val stack = StackUsingQueue()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    assert(stack.top() == 3)
    assert(!stack.empty())
    assert(stack.pop() == 3)
    assert(!stack.empty())
    assert(stack.pop() == 2)
    assert(!stack.empty())
    assert(stack.pop() == 1)
    assert(stack.empty())

    val myStack = MyStack()
    myStack.push(1)
    myStack.push(2)
    myStack.push(3)
    assert(myStack.top() == 3)
    assert(!myStack.empty())
    assert(myStack.pop() == 3)
    assert(!myStack.empty())
    assert(myStack.pop() == 2)
    assert(!myStack.empty())
    assert(myStack.pop() == 1)
    assert(myStack.empty())
}