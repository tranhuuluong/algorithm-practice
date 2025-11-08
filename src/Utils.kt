import model.ListNode
import model.Quadruple
import model.Quintuple
import kotlin.collections.withIndex

fun Any?.prettyPrint(): String = when (this) {
    is IntArray -> this.joinToString(prefix = "[", postfix = "]")
    is LongArray -> this.joinToString(prefix = "[", postfix = "]")
    is DoubleArray -> this.joinToString(prefix = "[", postfix = "]")
    is FloatArray -> this.joinToString(prefix = "[", postfix = "]")
    is CharArray -> this.joinToString(prefix = "[", postfix = "]")
    is BooleanArray -> this.joinToString(prefix = "[", postfix = "]")
    is Array<*> -> this.joinToString(prefix = "[", postfix = "]") { it.prettyPrint() }
    else -> this.toString()
}

fun isEqual(a: Any?, b: Any?): Boolean {
    return when {
        a is IntArray && b is IntArray -> a contentEquals b
        a is LongArray && b is LongArray -> a contentEquals b
        a is DoubleArray && b is DoubleArray -> a contentEquals b
        a is FloatArray && b is FloatArray -> a contentEquals b
        a is CharArray && b is CharArray -> a contentEquals b
        a is BooleanArray && b is BooleanArray -> a contentEquals b
        a is Array<*> && b is Array<*> -> a contentDeepEquals b
        a is ListNode && b is ListNode -> a.toString() == b.toString()
        else -> a == b
    }
}

fun printDivider() {
    val reset = "\u001B[0m"
    val brightYellow = "\u001B[93m"
    val brightCyan = "\u001B[96m"

    val totalWidth = 60
    val text = "🚀🚀🚀 NEXT SOLUTION 🚀🚀🚀"
    val padding = (totalWidth - text.length) / 2
    val paddedText = " ".repeat(padding) + text + " ".repeat(padding)

    println("\n$brightYellow${"=".repeat(totalWidth)}$reset")
    println("$brightCyan$paddedText$reset")
    println("$brightYellow${"=".repeat(totalWidth)}$reset\n")
}

fun <I, O> validateSolution(
    testCases: List<Pair<I, O>>,
    solution: (I) -> O,
    comparator: (O, O) -> Boolean = { expected, actual -> isEqual(expected, actual) },
) {
    for ((index, test) in testCases.withIndex()) {
        val (input, expected) = test
        val actual = solution(input)
        println("Test Case #$index")
        println("Input: ${input.prettyPrint()}")
        println("Expected: ${expected.prettyPrint()}, Actual: ${actual.prettyPrint()}")
        println(if (comparator(expected, actual)) "✅ Passed" else "❌ Failed")
        println("----")
    }
    printConslution {
        testCases.all { (input, expected) ->
            comparator(expected, solution(input))
        }
    }
}

fun <I1, I2, O> validateSolution(
    testCases: List<Triple<I1, I2, O>>,
    solution: (I1, I2) -> O,
    comparator: (O, O) -> Boolean = { expected, actual -> isEqual(expected, actual) },
) {
    for ((index, test) in testCases.withIndex()) {
        val (input1, input2, expected) = test
        val actual = solution(input1, input2)
        println("Test Case #$index")
        println("Input: (${input1.prettyPrint()}, ${input2.prettyPrint()})")
        println("Expected: ${expected.prettyPrint()}, Actual: ${actual.prettyPrint()}")
        println(if (comparator(expected, actual)) "✅ Passed" else "❌ Failed")
        println("----")
    }
    printConslution {
        testCases.all { (input1, input2, expected) ->
            comparator(expected, solution(input1, input2))
        }
    }
}

fun <I1, I2, I3, O> validateSolution(
    testCases: List<Quadruple<I1, I2, I3, O>>,
    solution: (I1, I2, I3) -> O,
    comparator: (O, O) -> Boolean = { expected, actual -> isEqual(expected, actual) },
) {
    for ((index, test) in testCases.withIndex()) {
        val (i1, i2, i3, expected) = test
        val actual = solution(i1, i2, i3)
        println("Test Case #$index")
        println("Input: (${i1.prettyPrint()}, ${i2.prettyPrint()}, ${i3.prettyPrint()})")
        println("Expected: ${expected.prettyPrint()}, Actual: ${actual.prettyPrint()}")
        println(if (comparator(expected, actual)) "✅ Passed" else "❌ Failed")
        println("----")
    }
    printConslution {
        testCases.all { (i1, i2, i3, expected) ->
            comparator(expected, solution(i1, i2, i3))
        }
    }
}

fun <I1, I2, I3, I4, O> validateSolution(
    testCases: List<Quintuple<I1, I2, I3, I4, O>>,
    solution: (I1, I2, I3, I4) -> O,
    comparator: (O, O) -> Boolean = { expected, actual -> isEqual(expected, actual) },
) {
    for ((index, test) in testCases.withIndex()) {
        val (i1, i2, i3, i4, expected) = test
        val actual = solution(i1, i2, i3, i4)
        println("Test Case #$index")
        println("Input: (${i1.prettyPrint()}, ${i2.prettyPrint()}, ${i3.prettyPrint()}, ${i4.prettyPrint()})")
        println("Expected: ${expected.prettyPrint()}, Actual: ${actual.prettyPrint()}")
        println(if (comparator(expected, actual)) "✅ Passed" else "❌ Failed")
        println("----")
    }
    printConslution {
        testCases.all { (i1, i2, i3, i4, expected) ->
            comparator(expected, solution(i1, i2, i3, i4))
        }
    }
}

private fun printConslution(accepted: () -> Boolean) {
    val green = "\u001B[32m"
    val red = "\u001B[31m"
    val reset = "\u001B[0m"

    if (accepted()) {
        println("${green}✅ Accepted$reset")
    } else {
        println("${red}❌ Wrong Answer$reset")
    }
}