import kotlin.system.measureTimeMillis

/**
 * 49: Group Anagrams
 * Medium
 * https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * Example 1:
 * Input: strs = ["tea","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * Constraints:
 * 1 <= strs.length <= 10⁴
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 *
 * @param strs the array of strings to group
 * @return a list of groups, where each group contains anagrams
 */
private fun groupAnagrams(strs: Array<String>): List<List<String>> {
    when {
        strs.isEmpty() -> return listOf(emptyList())
        strs.size == 1 -> return listOf(strs.toList())
    }

    val map = mutableMapOf<String, List<String>>()
    for (s in strs) {
        val sorted = s.toCharArray().sorted().toString()
        val currentList = map.getOrDefault(sorted, emptyList())
        map[sorted] = currentList + s
    }
    return map.values.toList()
}

private fun groupAnagrams2(strs: Array<String>): List<List<String>> {
    when {
        strs.isEmpty() -> return listOf(emptyList())
        strs.size == 1 -> return listOf(strs.toList())
    }

    val map = mutableMapOf<String, List<String>>()
    for (s in strs) {
        val arr = IntArray(26)
        for (c in s) {
            arr[c - 'a'] += 1
        }
        val key = arr.joinToString()
        val currentList = map.getOrDefault(key, emptyList())
        map[key] = currentList + s
    }
    return map.values.toList()
}

fun main() {
    val testCases = listOf(
        // Official example
        Pair(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"), listOf(
            listOf("bat"),
            listOf("nat", "tan"),
            listOf("ate", "eat", "tea")
        )),
        // Additional realistic test cases
        Pair(arrayOf(""), listOf(listOf(""))),
        Pair(arrayOf("a"), listOf(listOf("a"))),
        Pair(arrayOf("abc", "bca", "cab"), listOf(listOf("abc", "bca", "cab"))),
        Pair(arrayOf("ab", "ba", "cd", "dc", "abcd"), listOf(
            listOf("ab", "ba"),
            listOf("cd", "dc"),
            listOf("abcd")
        )),
        Pair(arrayOf("listen", "silent", "enlist", "google", "gogole"), listOf(
            listOf("listen", "silent", "enlist"),
            listOf("google", "gogole")
        )),
        Pair(arrayOf("aaa", "aa", "a"), listOf(
            listOf("aaa"),
            listOf("aa"),
            listOf("a")
        )),
        Pair(arrayOf("rat", "tar", "art", "star", "tars", "rats"), listOf(
            listOf("rat", "tar", "art"),
            listOf("star", "tars", "rats")
        )),
        Pair(arrayOf(""), listOf(listOf(""))),
        Pair(arrayOf("qwer", "rewq", "werq", "abc", "bac"), listOf(
            listOf("qwer", "rewq", "werq"),
            listOf("abc", "bac")
        ))
    )

    val comparator: (List<List<String>>, List<List<String>>) -> Boolean = { expected, actual ->
        fun normalize(list: List<List<String>>): List<List<String>> {
            return list
                .map { it.sorted() }
                .sortedBy { it.joinToString(separator = ",") }
        }
        when {
            expected.size != actual.size -> false
            else -> normalize(expected) == normalize(actual)
        }
    }

    val solution1 = measureTimeMillis {
        validateSolution(testCases, ::groupAnagrams, comparator)
    }
    printDivider()
    val solution2 = measureTimeMillis {
        validateSolution(testCases, ::groupAnagrams2, comparator)
    }
    val faster = if (solution1 < solution2) "groupAnagrams" else "groupAnagrams2"
    println("⏱️  Execution Time:")
    println("  • groupAnagrams   ➜ $solution1 ms")
    println("  • groupAnagrams2  ➜ $solution2 ms")
    println("🚀 Faster: $faster")
}
