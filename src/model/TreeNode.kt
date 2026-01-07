package model

class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun List<Int?>.toTreeNode(): TreeNode? {
    if (this.isEmpty() || this[0] == null) {
        return null
    }

    // Use 'value' field as defined in your class
    val root = TreeNode(this[0]!!)

    // Queue holds the nodes whose children we are about to attach.
    val queue: java.util.Queue<TreeNode> = java.util.LinkedList()
    queue.offer(root)

    var i = 1

    // Process the list sequentially, creating left and right children for the node at the front of the queue.
    while (i < this.size && queue.isNotEmpty()) {
        val parent = queue.poll()

        // --- Left Child (at list index i) ---
        val leftVal = this[i]
        if (leftVal != null) {
            val leftNode = TreeNode(leftVal)
            parent.left = leftNode
            queue.offer(leftNode) // Add the new node to the queue to process its children next
        }
        i++

        // --- Right Child (at list index i) ---
        if (i < this.size) {
            val rightVal = this[i]
            if (rightVal != null) {
                val rightNode = TreeNode(rightVal)
                parent.right = rightNode
                queue.offer(rightNode) // Add the new node to the queue
            }
            i++
        }
    }

    return root
}