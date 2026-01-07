import model.TreeNode
import model.toTreeNode

/**
 * 94. Binary Tree Inorder Traversal
 * Easy
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Inorder Traversal follows the Left -> Root -> Right pattern.
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * Explanation:
 * 1
 * \
 * 2
 * /
 * 3
 * Inorder: Left (null) -> Root (1) -> Right (2) -> Left (3) -> Root (2) -> Right (null)
 * Wait, the official example is usually clearer:
 * Input: root = [1,null,2,3]
 * Tree Structure:
 * 1
 * \
 * 2
 * /
 * 3
 * Correct Inorder: 1 -> Left of 2 (3) -> Root (2) -> Right of 2 (null)
 * The provided example output [1, 3, 2] is incorrect for the tree shown in the problem description.
 *
 * Let's assume the standard LeetCode visualization for [1, null, 2, 3] is:
 * 1
 * \
 * 2
 * /
 * 3
 * Correct Inorder: 1, 3, 2. Wait, that's not right either.
 * Let's use the standard Inorder definition: Left -> Root -> Right
 *
 * Start at 1. Go Left (null). Visit 1. Go Right (2).
 * Start at 2. Go Left (3).
 * Start at 3. Go Left (null). Visit 3. Go Right (null). Return [3]
 * Return to 2. Visit 2. Go Right (null). Return [3, 2]
 * Return to 1. Result is [1, 3, 2]
 *
 * Ah, the official LeetCode example 1 image is:
 * 1
 * \
 * 2
 * /
 * 3
 * Correct Inorder: [1, 3, 2] -> This implies the standard interpretation (Left-Root-Right) is *not* applied to the array elements sequentially, but to the tree structure.
 * Let's trust the LeetCode output: [1, 3, 2]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * @param root The root node of the binary tree.
 * @return A list of the nodes' values in inorder traversal.
 */
private fun inorderTraversal(root: TreeNode?): List<Int> = buildList {
    fun traversal(node: TreeNode?) {
        if (node != null) {
            traversal(node.left)
            add(node.value)
            traversal(node.right)
        }
    }
    traversal(root)
}


fun main() {
    val testCases = listOf(
        // Official examples
        // [1, null, 2, 3] -> (1)->(2)->(3). Inorder: 1 (Root)-> 3 (Left of 2) -> 2 (Root of 2) = [1, 3, 2]
        Pair(listOf(1, null, 2, 3).toTreeNode(), listOf(1, 3, 2)),

        // Empty tree
        Pair(listOf<Int?>().toTreeNode(), listOf()),

        // Single node
        Pair(listOf(1).toTreeNode(), listOf(1)),

        // Simple full tree [3, 1, 5]
        // Inorder: Left (1) -> Root (3) -> Right (5) = [1, 3, 5]
        Pair(listOf(3, 1, 5).toTreeNode(), listOf(1, 3, 5)),

        // Complex Unbalanced Tree [10, 5, 15, 2, 7, 12, 17]
        // Inorder: [2, 5, 7, 10, 12, 15, 17]
        Pair(listOf(10, 5, 15, 2, 7, 12, 17).toTreeNode(), listOf(2, 5, 7, 10, 12, 15, 17)),

        // Tree with repeated values
        // [1, 1, 1]
        //   1
        //  / \
        // 1   1
        // Inorder: [1, 1, 1]
        Pair(listOf(1, 1, 1).toTreeNode(), listOf(1, 1, 1))
    )

    validateSolution(testCases, ::inorderTraversal)
}