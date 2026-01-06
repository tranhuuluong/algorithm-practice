import model.TreeNode
import model.toTreeNode
import kotlin.math.max

/**
 * 104: Maximum Depth of Binary Tree
 * Easy
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Given the root of a binary tree, return its maximum depth.
 *
 * @param root The root node of the binary tree.
 * @return The maximum depth of the tree.
 */
private fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    return 1 + max(maxDepth(root.left), maxDepth(root.right))
}

fun main() {
    // The test cases use your List<Int?>.toTreeNode() extension function to create the TreeNode? input.
    val testCases = listOf(
        // Official examples
        // Depth 3: [3, 9, 20, null, null, 15, 7]
        Pair(listOf(3, 9, 20, null, null, 15, 7).toTreeNode(), 3),

        // Depth 2: [1, null, 2]
        Pair(listOf(1, null, 2).toTreeNode(), 2),

        // Additional edge cases

        // 1. Empty tree (Depth 0)
        Pair(listOf<Int?>().toTreeNode(), 0),

        // 2. Single node (Depth 1)
        Pair(listOf(1).toTreeNode(), 1),

        // 3. Left skewed tree (Depth 3)
        // 1 -> 2 -> 3
        Pair(listOf(1, 2, null, 3, null, null, null).toTreeNode(), 3),

        // 4. Right skewed tree (Depth 3)
        // 1 -> 2 -> 3
        Pair(listOf(1, null, 2, null, null, null, 3).toTreeNode(), 3),

        // 5. Unbalanced tree (Depth 4)
        // Path: 1 -> 2 -> 4 -> 6
        Pair(listOf(1, 2, 3, 4, null, null, 5, 6).toTreeNode(), 4),

        // 6. Perfect binary tree (Depth 3)
        Pair(listOf(1, 2, 3, 4, 5, 6, 7).toTreeNode(), 3),

        // 7. Larger unbalanced tree (Depth 5)
        // Path: 1 -> 2 -> 4 -> 8 -> 16
        Pair(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17).toTreeNode(), 5)
    )

    validateSolution(testCases, ::maxDepth)
}