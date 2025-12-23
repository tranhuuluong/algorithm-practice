/**
 * 3477: Fruits Into Baskets II
 * Easy
 * (Official URL unavailable for problem 3477)
 *
 * You are given two arrays of integers, `fruits` and `baskets`, each of length $n$, where `fruits[i]` represents the quantity of the $i$-th type of fruit, and `baskets[j]` represents the capacity of the $j$-th basket.
 *
 * From left to right, place the fruits according to these rules:
 *
 * 1. Each fruit type must be placed in the **leftmost available basket** with a capacity **greater than or equal** to the quantity of that fruit type.
 * 2. Each basket can hold **only one type** of fruit.
 * 3. If a fruit type **cannot be placed** in any basket, it remains unplaced.
 *
 * Return the number of fruit types that remain unplaced after all possible allocations are made.
 *
 * Example 1:
 * Input: fruits = [4,2,5], baskets = [3,5,4]
 * Output: 1
 * Explanation:
 * - fruits[0] = 4 is placed in baskets[1] = 5.
 * - fruits[1] = 2 is placed in baskets[0] = 3.
 * - fruits[2] = 5 cannot be placed in baskets[2] = 4.
 * Since one fruit type remains unplaced, we return 1.
 *
 * Example 2:
 * Input: fruits = [3,6,1], baskets = [6,4,7]
 * Output: 0
 * Explanation:
 * - fruits[0] = 3 is placed in baskets[0] = 6.
 * - fruits[1] = 6 cannot be placed in baskets[1] = 4 (insufficient capacity) but can be placed in the next available basket, baskets[2] = 7.
 * - fruits[2] = 1 is placed in baskets[1] = 4.
 * Since all fruits are successfully placed, we return 0.
 *
 * Constraints:
 * $n == fruits.length == baskets.length$
 * $1 \le n \le 100$
 * $1 \le fruits[i], baskets[i] \le 1000$
 *
 * @param fruits An array where $fruits[i]$ is the quantity of the $i$-th fruit type.
 * @param baskets An array where $baskets[j]$ is the capacity of the $j$-th basket.
 * @return The number of fruit types that remain unplaced.
 */
private fun countUnplacedFruits(fruits: IntArray, baskets: IntArray): Int {
    for (fruit in fruits) {
        for (i in 0 until baskets.size) {
            if (baskets[i] >= fruit) {
                baskets[i] = -1
                break
            }
        }
    }
    return baskets.count { it >= 0 }
}

fun main() {
    // Function takes 2 arguments (fruits, baskets), so we must use Triple for (arg1, arg2, expected)
    val testCases = listOf(
        // Official examples
        Triple(intArrayOf(4, 2, 5), intArrayOf(3, 5, 4), 1),
        Triple(intArrayOf(3, 6, 1), intArrayOf(6, 4, 7), 0),

        // Additional edge cases (min 10 total)
        // 1. Min length (n=1), placed
        Triple(intArrayOf(10), intArrayOf(10), 0),

        // 2. Min length (n=1), unplaced
        Triple(intArrayOf(11), intArrayOf(10), 1),

        // 3. All fruits placed, baskets in order
        Triple(intArrayOf(1, 2, 3), intArrayOf(1, 2, 3), 0),

        // 4. All fruits unplaced (Baskets too small for the first fruit)
        Triple(intArrayOf(5, 1, 1), intArrayOf(2, 3, 4), 1),

        // 5. First fruit takes the last basket, leaving middle baskets for subsequent fruits
        // F[0]=10 -> B[2]=15 (Remaining Baskets: [5, 8])
        // F[1]=5 -> B[0]=5 (Remaining Baskets: [8])
        // F[2]=8 -> B[1]=8 (Remaining Baskets: [])
        Triple(intArrayOf(10, 5, 8), intArrayOf(5, 8, 15), 0),

        // 6. Max constraints for values (Assuming n=3 for simplicity)
        Triple(intArrayOf(1000, 1, 1), intArrayOf(1000, 1000, 1000), 0),

        // 7. Max constraints for values, unplaced
        // F[0]=1000 -> B[2]=1000 (Remaining Baskets: [1, 500])
        // F[1]=1 -> B[0]=1 (Remaining Baskets: [500])
        // F[2]=501 -> B[1]=500 (Capacity < Quantity -> UNPLACED)
        Triple(intArrayOf(1000, 1, 501), intArrayOf(1, 500, 1000), 1),

        // 8. Order matters: F[0] uses a basket that F[1] needed
        // F[0]=5 -> B[0]=6 (Remaining: [5, 4])
        // F[1]=5 -> B[1]=5 (Remaining: [4])
        // F[2]=5 -> B[2]=4 (UNPLACED)
        Triple(intArrayOf(5, 5, 5), intArrayOf(6, 5, 4), 1),

        // 9. Order matters: F[0] skips B[0] and F[1] skips B[0], leading to success
        // F[0]=10 -> B[1]=10 (Remaining: [5, 15])
        // F[1]=15 -> B[2]=15 (Remaining: [5])
        // F[2]=5 -> B[0]=5 (Remaining: [])
        Triple(intArrayOf(10, 15, 5), intArrayOf(5, 10, 15), 0),

        // 10. Large number of unplaced items
        Triple(intArrayOf(100, 100, 100, 100), intArrayOf(1, 1, 1, 1), 4)
    )

    validateSolution(testCases, ::countUnplacedFruits)
}