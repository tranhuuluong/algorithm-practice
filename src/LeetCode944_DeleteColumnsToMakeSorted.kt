/**
 * 944: Delete Columns to Make Sorted
 * Easy
 * https://leetcode.com/problems/delete-columns-to-make-sorted/
 *
 * You are given an array of $n$ strings `strs`, all of the same length.
 * The strings can be arranged in a grid where `strs[i]` is the $i$-th row. For example, if `strs = ["cba", "daf", "ghi"]`, it is arranged as:
 * c b a
 * d a f
 * g h i
 *
 * We want to delete the columns that are not sorted lexicographically. In the above example (columns 0, 1, and 2):
 * - Column 0 is ('c', 'd', 'g'). It is sorted since 'c' <= 'd' <= 'g'.
 * - Column 1 is ('b', 'a', 'h'). It is not sorted since 'b' > 'a'.
 * - Column 2 is ('a', 'f', 'i'). It is sorted since 'a' <= 'f' <= 'i'.
 *
 * Return the number of columns that are not sorted.
 *
 * Example 1:
 * Input: strs = ["cba", "daf", "ghi"]
 * Output: 1
 * Explanation: The only unsorted column is column 1.
 *
 * Example 2:
 * Input: strs = ["a", "b"]
 * Output: 0
 * Explanation: All columns (only one) are sorted.
 *
 * Example 3:
 * Input: strs = ["zyx", "wvu", "tsr"]
 * Output: 3
 * Explanation: All three columns are unsorted.
 *
 * Constraints:
 * $n == strs.length$
 * $1 \le n \le 100$
 * $1 \le strs[i].length \le 100$
 * $strs[i]$ consists of lowercase English letters.
 *
 * @param strs An array of strings of the same length.
 * @return The number of columns that are not sorted lexicographically.
 */
private fun minDeletionSize(strs: Array<String>): Int {
    var ans = 0
    for (i in 0 until strs.first().length) {
        for (j in 1 until strs.size) {
            if (strs[j][i] < strs[j - 1][i]) {
                ans++
                break
            }
        }
    }
    return ans
}

fun main() {
    // Function takes 1 argument (strs), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair(arrayOf("cba", "daf", "ghi"), 1),
        Pair(arrayOf("a", "b"), 0),
        Pair(arrayOf("zyx", "wvu", "tsr"), 3),

        // 1. Single row (Always sorted)
        Pair(arrayOf("abcde"), 0),

        // 2. All sorted (4 rows, 1 col)
        Pair(arrayOf("a", "b", "c", "d"), 0),

        // 3. One column, unsorted (3 rows, 1 col)
        Pair(arrayOf("z", "y", "x"), 1),

        // 4. Mixed sorted/unsorted, longer strings (3 rows, 3 cols)
        // Col 0: r, h, l (r>h -> Unsorted). Col 1: g, g, m (g<=g, g<=m -> Sorted). Col 2: s, e, n (s>e -> Unsorted). Count: 2
        Pair(arrayOf("rgs", "hge", "lmn"), 2),

        // 5. Unsorted at the end (3 rows, 3 cols)
        // Col 0: a, b, c (Sorted). Col 1: e, f, g (Sorted). Col 2: z, y, x (z>y -> Unsorted). Count: 1
        Pair(arrayOf("aez", "bfy", "cgx"), 1),

        // 6. Two columns, unsorted (2 rows, 2 cols)
        // Col 0: a, b (Sorted). Col 1: z, a (z>a -> Unsorted). Count: 1
        Pair(arrayOf("az", "ba"), 1),

        // 7. Unsorted only by one character in the middle (3 rows, 3 cols)
        // Col 0: a, a, a (Sorted). Col 1: b, z, d (b<z, z>d -> Unsorted). Col 2: c, z, e (c<z, z>e -> Unsorted). Count: 2
        Pair(arrayOf("abc", "azz", "ade"), 2),

        // 8. Array that starts unsorted, ends sorted (3 rows, 2 cols)
        // Col 0: z, y, x (Unsorted). Col 1: a, b, c (Sorted). Count: 1
        Pair(arrayOf("za", "yb", "xc"), 1),

        // 9. All columns unsorted (3 rows, 3 cols)
        Pair(arrayOf("fed", "cba", "zyx"), 3), // Col 0: f>c, c<z (Unsorted). Col 1: e>b, b<y (Unsorted). Col 2: d>a, a<x (Unsorted).

        // 10. Columns with repeating characters (4 rows, 3 cols)
        // Col 0: b, a, b, c (b>a -> Unsorted). Col 1: c, c, c, c (Sorted). Col 2: a, b, a, b (b>a -> Unsorted). Count: 2
        Pair(arrayOf("bca", "acb", "bca", "ccb"), 2)
    )

    validateSolution(testCases, ::minDeletionSize)
}