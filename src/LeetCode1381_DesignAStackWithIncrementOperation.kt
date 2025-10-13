import kotlin.math.min

/**
 * 1381: Design a Stack With Increment Operation
 * Medium
 * https://leetcode.com/problems/design-a-stack-with-increment-operation/
 *
 * You are to design a stack which supports the following operations:
 * * CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
 * * void push(int x) Adds x to the top of the stack if the stack hasn’t reached the maxSize.
 * * int pop() Pops and returns the top of the stack. If the stack is empty, return -1.
 * * void increment(int k, int val) Increments the bottom k elements of the stack by val. If there are fewer than k elements, increment all of them.
 *
 * Example:
 * Input:
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * Output:
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 *
 * Constraints:
 * - 1 <= maxSize, x, k <= 1000
 * - 0 <= val <= 100
 * - At most 1000 calls will be made to push, pop, and increment respectively
 *
 * @param maxSize maximum capacity of the custom stack
 */

class CustomStack(val maxSize: Int) {

    private val stack = IntArray(maxSize)
    private var size = 0

    fun push(x: Int) {
        if (size < maxSize) {
            stack[size++] = x
        }
    }

    fun pop(): Int {
        return if (size == 0) -1 else stack[--size]
    }

    fun increment(k: Int, value: Int) {
        for (i in 0..min(k - 1, size - 1)) {
            stack[i] += value
        }
    }

}

fun main() {
    val a = 'a'
    a.digitToInt()
    val customStack = CustomStack(3)
    customStack.push(1)
    customStack.push(2)
    customStack.push(3)
    customStack.push(4)
    customStack.push(5)
    assert(customStack.pop() == 3)
    customStack.increment(3, 100)
    assert(customStack.pop() == 102)
    assert(customStack.pop() == 101)
    assert(customStack.pop() == -1)
}