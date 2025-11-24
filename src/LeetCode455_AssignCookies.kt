/**
 * 455: Assign Cookies
 * Easy
 * https://leetcode.com/problems/assign-cookies/
 *
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 *
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i,
 * and the child i will be content. Your goal is to maximize the number of your content children
 * and output the maximum number. :contentReference[oaicite:0]{index=0}
 *
 * Example 1:
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of the children are 1, 2, 3.
 * Even though you have 2 cookies (both size 1), you can only make one child content (the one with greed 1). :contentReference[oaicite:1]{index=1}
 *
 * Example 2:
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children (greed 1 and 2) and 3 cookies (sizes 1,2,3). You can give cookie size 1 to greed-1 child, and size 2 (or 3) to greed-2 child — both are content. :contentReference[oaicite:2]{index=2}
 *
 * Constraints:
 * - 1 <= g.length <= 3 * 10⁴ :contentReference[oaicite:3]{index=3}
 * - 0 <= s.length <= 3 * 10⁴ :contentReference[oaicite:4]{index=4}
 * - 1 <= g[i], s[j] <= 2³¹ − 1 :contentReference[oaicite:5]{index=5}
 *
 * @param g the greed factors of the children
 * @param s the sizes of the cookies
 * @return the maximum number of content children
 */
private fun findContentChildren(g: IntArray, s: IntArray): Int {
    g.sort()
    s.sort()
    var i = 0
    var j = 0
    var result = 0
    while (i <= g.lastIndex && j <= s.lastIndex) {
        if (s[j] >= g[i]) {
            result++
            i++
            j++
        } else {
            j++
        }
    }
    return result
}

fun main() {
    val testCases = listOf(
        Triple(intArrayOf(1,2,3), intArrayOf(1,1), 1),  // Example 1
        Triple(intArrayOf(1,2), intArrayOf(1,2,3), 2),  // Example 2
        Triple(intArrayOf(1), intArrayOf(), 0),         // No cookies
        Triple(intArrayOf(5,10,2), intArrayOf(10), 1),  // Only one cookie, enough for one child
        Triple(intArrayOf(1,2,2,3), intArrayOf(1,1,2), 2),
        Triple(intArrayOf(4,3,1), intArrayOf(1,2,3), 2),
        Triple(intArrayOf(2,2,3,3), intArrayOf(3,3), 2),
        Triple(intArrayOf(8,9,10), intArrayOf(5,6,7), 0),
        Triple(intArrayOf(1,1,1,1), intArrayOf(1,2,3,4), 4),
        Triple(intArrayOf(1,2,3,4), intArrayOf(1,1,1,1), 1)
    )

    validateSolution(testCases, ::findContentChildren)
}
