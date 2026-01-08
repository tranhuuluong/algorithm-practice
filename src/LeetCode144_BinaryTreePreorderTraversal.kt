import model.TreeNode
import model.toTreeNode

/**
 * 144. Binary Tree Preorder Traversal
 * Easy
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Preorder Traversal follows the Root -> Left -> Right pattern.
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * Explanation:
 * The tree structure is:
 * 1
 * \
 * 2
 * /
 * 3
 * Preorder: Root (1) -> Left (null) -> Right (2) -> Root (2) -> Left (3) -> Right (null)
 * The traversal should be: Root(1) -> Right(2) -> Left(3). Result: [1, 2, 3]
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
 * @return A list of the nodes' values in preorder traversal.
 */
private fun preorderTraversal(root: TreeNode?): List<Int> = buildList {
    fun traversal(node: TreeNode?) {
        if (node != null) {
            add(node.value)
            traversal(node.left)
            traversal(node.right)
        }
    }
    traversal(root)
}

fun main() {
    val testCases = listOf(
        // Official examples
        // [1, null, 2, 3] -> (1)->(2)->(3). Preorder: 1 (Root) -> 2 (Right) -> 3 (Left of 2) = [1, 2, 3]
        Pair(listOf(1, null, 2, 3).toTreeNode(), listOf(1, 2, 3)),

        // Empty tree
        Pair(listOf<Int?>().toTreeNode(), listOf()),

        // Single node
        Pair(listOf(1).toTreeNode(), listOf(1)),

        // Simple full tree [3, 1, 5]
        // Preorder: Root (3) -> Left (1) -> Right (5) = [3, 1, 5]
        Pair(listOf(3, 1, 5).toTreeNode(), listOf(3, 1, 5)),

        // Complex Unbalanced Tree [10, 5, 15, 2, 7, 12, 17]
        // Preorder: [10, 5, 2, 7, 15, 12, 17]
        Pair(listOf(10, 5, 15, 2, 7, 12, 17).toTreeNode(), listOf(10, 5, 2, 7, 15, 12, 17)),

        // Tree with repeated values
        // [1, 1, 1]
        //   1
        //  / \
        // 1   1
        // Preorder: [1, 1, 1]
        Pair(listOf(1, 1, 1).toTreeNode(), listOf(1, 1, 1))
    )

    validateSolution(testCases, ::preorderTraversal)
}