/**
 * 2483: Minimum Penalty for a Shop
 * Medium
 * https://leetcode.com/problems/minimum-penalty-for-a-shop/
 *
 * You are given the customer visit log of a shop represented by a 0-indexed string `customers` of length $n$, where `customers[i]` is either 'Y' or 'N'.
 *
 * 'Y' indicates a customer entered the shop at hour $i$.
 * 'N' indicates no customer entered the shop at hour $i$.
 *
 * A shop owner wants to close the shop at some hour $j$ where $0 \le j \le n$. The penalty for closing at hour $j$ is calculated as follows:
 *
 * The shop is open from hour 0 to hour $j-1$ (inclusive).
 * The shop is closed from hour $j$ to hour $n-1$ (inclusive).
 *
 * Penalty = (Number of times a customer visits the shop when it is **closed**) + (Number of times a customer does **not** visit the shop when it is **open**).
 *
 * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
 *
 * Note: The hour $n$ means the shop is open for all $n$ hours. In this case, the penalty only counts the 'N's in the entire string.
 *
 * Example 1:
 * Input: customers = "YYNY"
 * Output: 2
 * Explanation:
 * - j = 0: Shop closed immediately. Open: []. Closed: [Y, Y, N, Y]. Penalty = (3 'Y's closed) + (0 'N's open) = 3.
 * - j = 1: Open: [Y]. Closed: [Y, N, Y]. Penalty = (3 'Y's closed) + (0 'N's open) = 3.
 * - j = 2: Open: [Y, Y]. Closed: [N, Y]. Penalty = (1 'Y' closed) + (0 'N's open) = 1. (Minimum)
 * - j = 3: Open: [Y, Y, N]. Closed: [Y]. Penalty = (1 'Y' closed) + (1 'N' open) = 2.
 * - j = 4: Open: [Y, Y, N, Y]. Closed: []. Penalty = (0 'Y' closed) + (1 'N' open) = 1.
 *
 * The minimum penalty is 1, achieved at hours 2 and 4. We return the earliest hour, which is 2.
 *
 * Example 2:
 * Input: customers = "NNNNN"
 * Output: 0
 * Explanation: The minimum penalty is 0, achieved when closing at hour 0.
 * - j = 0: Open: []. Closed: [N, N, N, N, N]. Penalty = (0 'Y's closed) + (0 'N's open) = 0.
 *
 * Example 3:
 * Input: customers = "YYYY"
 * Output: 4
 * Explanation: The minimum penalty is 0, achieved when closing at hour 4.
 * - j = 4: Open: [Y, Y, Y, Y]. Closed: []. Penalty = (0 'Y's closed) + (0 'N's open) = 0.
 *
 * Constraints:
 * $1 \le customers.length \le 10^5$
 * `customers[i]` is either 'Y' or 'N'.
 *
 * @param customers The customer visit log.
 * @return The earliest hour to close the shop for minimum penalty.
 */
private fun bestClosingTime(customers: String): Int {
    var penalty = customers.count { it == 'Y' }
    var minPenalty = penalty
    var ans = 0
    for (i in 0 until customers.length) {
        if (customers[i] == 'Y') penalty-- else penalty++
        if (penalty < minPenalty) {
            ans = i + 1
            minPenalty = penalty
        }
    }
    return ans
}

fun main() {
    // Function takes 1 argument (customers), so we must use Pair for (arg1, expected)
    val testCases = listOf(
        // Official examples
        Pair("YYNY", 2),
        Pair("NNNNN", 0),
        Pair("YYYY", 4),

        // Additional edge cases (min 10 total)
        // 1. Minimum length, mixed
        Pair("YN", 1), // j=0: P=1(N open, Y closed=1) + 0 = 1. j=1: P=1(N open) + 0 = 1. j=2: P=0 + 1(N open)=1. Earliest is 0.
        // Re-check:
        // j=0: Open []. Closed [Y, N]. Penalty = Y_closed + N_open = 1 + 0 = 1.
        // j=1: Open [Y]. Closed [N]. Penalty = Y_closed + N_open = 0 + 1 = 1.
        // j=2: Open [Y, N]. Closed []. Penalty = Y_closed + N_open = 0 + 1 = 1.
        Pair("YN", 1),

        // 2. Maximum length of all Ns (represented by 6 Ns)
        Pair("NNNNNN", 0),

        // 3. Maximum length of all Ys (represented by 6 Ys)
        Pair("YYYYYY", 6),

        // 4. Alternating, length 4
        // j=0: Closed [Y, N, Y, N]. P=2+0=2.
        // j=1: Open [Y]. Closed [N, Y, N]. P=1+0=1.
        // j=2: Open [Y, N]. Closed [Y, N]. P=1+1=2.
        // j=3: Open [Y, N, Y]. Closed [N]. P=0+1=1.
        // j=4: Open [Y, N, Y, N]. Closed []. P=0+2=2.
        // Min 1, achieved at j=1 and j=3. Earliest is 1.
        Pair("YNYN", 1),

        // 5. Min penalty at the end
        // j=0: Closed [N, Y, Y, Y]. P=3+0=3.
        // j=1: Open [N]. Closed [Y, Y, Y]. P=3+1=4.
        // j=2: Open [N, Y]. Closed [Y, Y]. P=2+1=3.
        // j=3: Open [N, Y, Y]. Closed [Y]. P=1+1=2.
        // j=4: Open [N, Y, Y, Y]. Closed []. P=0+1=1.
        Pair("NYYY", 4),

        // 6. Multiple Ns near the start
        // j=0: Closed [N, N, Y, Y]. P=2+0=2.
        // j=1: Open [N]. Closed [N, Y, Y]. P=2+1=3.
        // j=2: Open [N, N]. Closed [Y, Y]. P=2+2=4.
        // j=3: Open [N, N, Y]. Closed [Y]. P=1+2=3.
        // j=4: Open [N, N, Y, Y]. Closed []. P=0+2=2.
        // Min 2, achieved at j=0 and j=4. Earliest is 0.
        Pair("NNYY", 0),

        // 7. Large string, minimum penalty at j=4
        Pair("YNNYNYYY", 8),
        // Total Ns: 3. Total Ys: 5.
        // j=0: P = 5 (all Ys closed) + 0 = 5.
        // j=1: Open [Y]. Closed [N, N, Y, N, Y, Y]. P = 4 (Y closed) + 0 (N open) = 4.
        // j=2: Open [Y, N]. Closed [N, Y, N, Y, Y]. P = 4 (Y closed) + 1 (N open) = 5.
        // j=3: Open [Y, N, N]. Closed [Y, N, Y, Y]. P = 3 (Y closed) + 2 (N open) = 5.
        // j=4: Open [Y, N, N, Y]. Closed [N, Y, Y]. P = 3 (Y closed) + 2 (N open) = 5. Wait.
        // j=4: Open [Y, N, N, Y]. Closed [N, Y, Y, Y]. P = 4 (Y closed) + 2 (N open) = 6.
        // Let's re-calculate YNNYNYYY:
        // j=0: Open: []. Closed: [YNNYNYYY]. P=5+0=5.
        // j=1: Open: [Y]. Closed: [NNYNYYY]. P=4+0=4.
        // j=2: Open: [YN]. Closed: [NYNYYY]. P=4+1=5.
        // j=3: Open: [YNN]. Closed: [YNYYY]. P=4+2=6.
        // j=4: Open: [YNNY]. Closed: [NYYY]. P=3+2=5.
        // j=5: Open: [YNNYN]. Closed: [YYY]. P=3+3=6.
        // j=6: Open: [YNNYNY]. Closed: [YY]. P=2+3=5.
        // j=7: Open: [YNNYNYY]. Closed: [Y]. P=1+3=4.
        // j=8: Open: [YNNYNYYY]. Closed: []. P=0+3=3.
        // Minimum is 3, achieved at j=8.
        Pair("YNNYNYYY", 8)
    )

    validateSolution(testCases, ::bestClosingTime)
}