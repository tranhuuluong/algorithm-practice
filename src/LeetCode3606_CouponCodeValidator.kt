import model.Quadruple
import java.util.PriorityQueue

/**
 * 3606: Coupon Code Validator
 * Easy
 * https://leetcode.com/problems/coupon-code-validator/
 *
 * You are given three arrays of equal length n describing n coupons:
 *
 * - code[i]: a string identifying the coupon.
 * - businessLine[i]: a string describing the business category.
 * - isActive[i]: a boolean indicating if the coupon is active.
 *
 * A coupon is valid if:
 * 1. code[i] is non-empty and contains only letters, digits, or underscores.
 * 2. businessLine[i] is one of:
 *       "electronics", "grocery", "pharmacy", "restaurant"
 * 3. isActive[i] is true.
 *
 * Output the list of valid coupon codes sorted by:
 *   1) businessLine order:
 *        electronics → grocery → pharmacy → restaurant
 *   2) lexicographical order of code within each category.
 *
 * Example 1:
 * code = ["SAVE20","","PHARMA5","SAVE@20"]
 * businessLine = ["restaurant","grocery","pharmacy","restaurant"]
 * isActive = [true,true,true,true]
 * Output = ["PHARMA5","SAVE20"]
 *
 * Example 2:
 * code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"]
 * businessLine = ["grocery","electronics","invalid"]
 * isActive = [false,true,true]
 * Output = ["ELECTRONICS_50"]
 *
 * @param code coupon codes
 * @param businessLine coupon business categories
 * @param isActive activity flags
 * @return list of valid coupon codes after filtering and sorting
 */
private fun validateCoupons(codes: Array<String>, businessLine: Array<String>, isActive: BooleanArray): List<String> {
    val pq = PriorityQueue<Pair<String, String>> { a, b ->
        when {
            a.second == b.second -> a.first.compareTo(b.first)
            else -> a.second.getPriority() - b.second.getPriority()
        }
    }

    for (i in codes.indices) {
        if (businessLine[i].isValidCategory() && isActive[i] && codes[i].isValidCode()) {
            pq.add(codes[i] to businessLine[i])
        }
    }

    return buildList {
        while (pq.isNotEmpty()) {
            add(pq.poll().first)
        }
    }
}

private fun String.isValidCode(): Boolean {
    return this.isNotBlank() && all { it.isLetterOrDigit() || it == '_' }
}

private fun String.isValidCategory(): Boolean {
    return this == "electronics" || this == "grocery" || this == "pharmacy" || this == "restaurant"
}

private fun String.getPriority() = when (this) {
    "electronics" -> 0
    "grocery" -> 1
    "pharmacy" -> 2
    else -> 3
}

fun main() {
    val testCases = listOf(
        Quadruple(
            arrayOf("SAVE20", "", "PHARMA5", "SAVE@20"),
            arrayOf("restaurant", "grocery", "pharmacy", "restaurant"),
            booleanArrayOf(true, true, true, true),
            listOf("PHARMA5", "SAVE20")
        ),
        Quadruple(
            arrayOf("GROCERY15", "ELECTRONICS_50", "DISCOUNT10"),
            arrayOf("grocery", "electronics", "invalid"),
            booleanArrayOf(false, true, true),
            listOf("ELECTRONICS_50")
        ),
        Quadruple(
            arrayOf("CODE1", "CODE_2", "CODE-3"),
            arrayOf("electronics", "grocery", "pharmacy"),
            booleanArrayOf(true, true, true),
            listOf("CODE1", "CODE_2")
        ),
        Quadruple(
            arrayOf("X", "Y", "Z"),
            arrayOf("restaurant", "restaurant", "restaurant"),
            booleanArrayOf(false, true, true),
            listOf("Y", "Z")
        ),
        Quadruple(
            arrayOf("E1", "G1", "P1", "R1"),
            arrayOf("electronics", "grocery", "pharmacy", "restaurant"),
            booleanArrayOf(true, true, true, true),
            listOf("E1", "G1", "P1", "R1")
        ),
        Quadruple(
            arrayOf("A", "_B", "C3", "D_"),
            arrayOf("electronics", "electronics", "electronics", "electronics"),
            booleanArrayOf(true, true, false, true),
            listOf("A", "D_", "_B")
        ),
        Quadruple(
            arrayOf("abc", "123", "_", "a_b_c"),
            arrayOf("grocery", "grocery", "grocery", "grocery"),
            booleanArrayOf(true, true, true, false),
            listOf("123", "_", "abc")
        ),
        Quadruple(
            arrayOf("E", "E2", "E1"),
            arrayOf("electronics", "electronics", "electronics"),
            booleanArrayOf(true, true, true),
            listOf("E", "E1", "E2")
        ),
        Quadruple(
            arrayOf("P", "p", "p_2"),
            arrayOf("pharmacy", "pharmacy", "pharmacy"),
            booleanArrayOf(true, false, true),
            listOf("P", "p_2")
        ),
        Quadruple(
            arrayOf("R1", "R2", "R3"),
            arrayOf("restaurant", "restaurant", "invalid"),
            booleanArrayOf(true, true, true),
            listOf("R1", "R2")
        )
    )

    validateSolution(testCases, ::validateCoupons)
}
