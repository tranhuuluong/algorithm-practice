import java.util.PriorityQueue

/**
 * 3075: Maximize Happiness of Selected Children
 * Medium
 * https://leetcode.com/problems/maximize-happiness-of-selected-children/
 *
 * You are given an array `happiness` of length $n$, where `happiness[i]` represents the happiness value of the $i$-th child. You are also given an integer $k$.
 *
 * You want to select $k$ children from the $n$ children.
 *
 * When you select a child, their happiness is added to your total happiness.
 * Additionally, after selecting a child, the happiness of all **unselected** children decreases by 1. The happiness of any child cannot become negative.
 *
 * Your goal is to maximize the total happiness you can achieve.
 *
 * Return the maximum total happiness.
 *
 * Example 1:
 * Input: happiness = [1,2,3], k = 2
 * Output: 4
 * Explanation: We have $n = 3$ children and want to select $k = 2$.
 * 1. Select the child with happiness 3. Total happiness: $3$. Remaining happiness: $[1, 2]$.
 * 2. Decrease remaining happiness by 1: $[1-1, 2-1] = [0, 1]$.
 * 3. Select the child with happiness 1. Total happiness: $3 + 1 = 4$.
 *
 * Example 2:
 * Input: happiness = [1,1,1,1], k = 2
 * Output: 1
 * Explanation: We have $n = 4$ children and want to select $k = 2$.
 * 1. Select a child with happiness 1. Total happiness: $1$. Remaining happiness: $[1, 1, 1]$.
 * 2. Decrease remaining happiness by 1: $[0, 0, 0]$.
 * 3. Select another child with happiness 0. Total happiness: $1 + 0 = 1$.
 *
 * Example 3:
 * Input: happiness = [2,3,4,5], k = 1
 * Output: 5
 * Explanation: We have $n = 4$ children and want to select $k = 1$.
 * 1. Select the child with happiness 5. Total happiness: $5$.
 *
 * Constraints:
 * $1 \le n = happiness.length \le 2 \times 10^5$
 * $1 \le k \le n$
 * $1 \le happiness[i] \le 10^8$
 *
 * @param happiness An array of initial happiness values.
 * @param k The number of children to select.
 * @return The maximum total happiness achievable.
 */
private fun maximizeHappiness1(happiness: IntArray, k: Int): Long {
    happiness.sort()
    var ans = 0L
    var index = happiness.size - 1
    repeat(k) { turn ->
        ans += (happiness[index--] - turn).coerceAtLeast(0)
    }
    return ans
}

private fun maximizeHappiness2(happiness: IntArray, k: Int): Long {
    val pq = PriorityQueue<Int>(Comparator.reverseOrder())
    for (h in happiness) {
        pq.add(h)
    }
    var ans = 0L
    repeat(k) { turn ->
        ans += (pq.poll() - turn).coerceAtLeast(0)
    }
    return ans
}

fun main() {
    // Function takes 2 arguments (happiness, k), so we must use Triple for (arg1, arg2, expected)
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(1, 2, 3), 2, 4L),
        Triple(intArrayOf(1, 1, 1, 1), 2, 1L),
        Triple(intArrayOf(2, 3, 4, 5), 1, 5L),

        // Additional edge cases (min 10 total)
        // 1. k=n (select all)
        Triple(intArrayOf(1, 2, 3), 3, 4L), // 3 + (2-1) + (1-2) -> 3 + 1 + 0 = 4

        // 2. Large numbers, k=3
        Triple(intArrayOf(10, 20, 30, 40), 3, 87L), // 40 + (30-1) + (20-2) = 40 + 29 + 18 = 87

        // 3. All distinct and large, k=2
        Triple(intArrayOf(90, 95, 100), 2, 194L), // 100 + (95-1) = 100 + 94 = 194

        // 4. Zero happiness check (cannot go negative)
        Triple(intArrayOf(1, 5, 10), 2, 14L), // 10 + (5-1) = 10 + 4 = 14

        // 5. Array with happiness 1, k=5
        Triple(intArrayOf(1, 1, 1, 1, 1, 1), 5, 1L), // 1 + (1-1) + (1-2) + (1-3) + (1-4) -> 1 + 0 + 0 + 0 + 0 = 1

        // 6. k=4, happiness values 5, 10, 15, 20, 25
        Triple(intArrayOf(5, 10, 15, 20, 25), 4, 64L), // 25 + 19 + 13 + 7 = 64

        // 7. k=3
        Triple(intArrayOf(5, 10, 15, 20, 25), 3, 57L), // 25 + 19 + 13 = 57

        // 8. One large number, many small, k=2
        Triple(intArrayOf(1, 1, 100), 2, 100L) // 100 + (1-1) = 100
    )

    validateSolution(testCases, ::maximizeHappiness1)
    printDivider()
    validateSolution(testCases, ::maximizeHappiness2)
}