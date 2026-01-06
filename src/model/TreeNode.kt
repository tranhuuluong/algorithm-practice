package model

class TreeNode(
    var value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null
)

fun List<Int?>.toTreeNode(): TreeNode? {
    if (this.isEmpty() || this[0] == null) return null

    // Helper function to insert nodes recursively based on index
    fun insertLevelOrder(index: Int): TreeNode? {
        // 1. Base case for index out of bounds
        if (index >= this.size) return null

        val value = this[index]

        // 2. Base case for null placeholder
        if (value == null) return null

        val node = TreeNode(value)

        // The left child is always at (2 * index + 1)
        node.left = insertLevelOrder(2 * index + 1)

        // The right child is always at (2 * index + 2)
        node.right = insertLevelOrder(2 * index + 2)

        return node
    }

    return insertLevelOrder(0)
}