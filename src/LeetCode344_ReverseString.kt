/**
 * 344. Reverse String
 * Easy
 * https://leetcode.com/problems/reverse-string/
 *
 * Write a function that reverses a string.
 * The input string is given as an array of characters s.
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * Example 2:
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 * Constraints:
 * - 1 <= s.length <= 10^5
 * - s[i] is a printable ASCII character.
 *
 * @param s the array of characters to be reversed in-place
 * @return Unit (modifies the input array in-place)
 */
private fun reverseString(s: CharArray): CharArray {
    var left = 0
    var right = s.lastIndex
    while (left < right) {
        val temp = s[left]
        s[left] = s[right]
        s[right] = temp
        left++
        right--
    }
    return s
}

fun main() {
    val testCases = listOf(
        Pair(charArrayOf('h','e','l','l','o'), charArrayOf('o','l','l','e','h')),
        Pair(charArrayOf('H','a','n','n','a','h'), charArrayOf('h','a','n','n','a','H')),
        Pair(charArrayOf('a'), charArrayOf('a')),
        Pair(charArrayOf('A','B'), charArrayOf('B','A')),
        Pair(charArrayOf('1','2','3','4','5'), charArrayOf('5','4','3','2','1')),
        Pair(charArrayOf('x','y','z','x','y'), charArrayOf('y','x','y','x','z')),
        Pair(charArrayOf('!','@','#','$'), charArrayOf('$','#','@','!')),
        Pair(charArrayOf('m','a','d','a','m'), charArrayOf('m','a','d','a','m')),
        Pair(charArrayOf('0','0','0','0'), charArrayOf('0','0','0','0')),
        Pair(charArrayOf('L','e','e','t','C','o','d','e'), charArrayOf('e','d','o','C','t','e','e','L'))
    )

    validateSolution(testCases, ::reverseString)
}
