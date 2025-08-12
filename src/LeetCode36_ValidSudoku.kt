/**
 * 36: Valid Sudoku
 * Medium
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Example 1:
 * Input: board = [["5","3",".",".","7",".",".",".","."],
 *                 ["6",".",".","1","9","5",".",".","."],
 *                 [".","9","8",".",".",".",".","6","."],
 *                 ["8",".",".",".","6",".",".",".","3"],
 *                 ["4",".",".","8",".","3",".",".","1"],
 *                 ["7",".",".",".","2",".",".",".","6"],
 *                 [".","6",".",".",".",".","2","8","."],
 *                 [".",".",".","4","1","9",".",".","5"],
 *                 [".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Constraints:
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit '1'-'9' or '.'
 *
 * @param board the 9x9 Sudoku board represented as a 2D array of characters
 * @return true if the filled cells of the board represent a valid Sudoku configuration, false otherwise
 */

//00 01 02 | 03 04 05 | 06 07 08
//10 11 12 | 13 14 15 | 16 17 18
//20 21 22 | 23 24 25 | 26 27 28

//30 31 32 | 33 34 35 | 36 37 38
//40 41 42 | 43 44 45 | 46 47 48
//50 51 52 | 53 54 55 | 56 57 58

//60 61 62 | 63 64 65 | 66 67 68
//70 71 72 | 73 74 75 | 76 77 78
//80 81 82 | 83 84 85 | 86 86 88

private fun isValidSudoku(board: Array<CharArray>): Boolean {
    val rows = Array<MutableSet<Char>>(9) { mutableSetOf() }
    val columns = Array<MutableSet<Char>>(9) { mutableSetOf() }
    val boxes = Array<MutableSet<Char>>(9) { mutableSetOf() }

    for (i in 0..board.lastIndex) {
        for (j in 0..board[i].lastIndex) {
            val num = board[i][j]
            if (!num.isDigit()) continue
            if (!rows[i].add(num)) return false
            if (!columns[j].add(num)) return false
            val boxNumber = (i / 3) * 3 + (j / 3)
//            Alternatives
//            val boxNumber = when {
//                i < 3 && j < 3 -> 0
//                i < 3 && j < 6 -> 1
//                i < 3 && j < 9 -> 2
//                i < 6 && j < 3 -> 3
//                i < 6 && j < 6 -> 4
//                i < 6 && j < 9 -> 5
//                i < 9 && j < 3 -> 6
//                i < 9 && j < 6 -> 7
//                else -> 8
//            }
            if (!boxes[boxNumber].add(num)) return false
        }
    }

    return true
}

private fun isValidSudoku2(board: Array<CharArray>): Boolean {
    val rows = Array(9) { BooleanArray(9) }
    val columns = Array(9) { BooleanArray(9) }
    val boxes = Array(9) { BooleanArray(9) }

    for (i in 0..board.lastIndex) {
        for (j in 0..board[i].lastIndex) {
            val num = board[i][j]
            if (!num.isDigit()) continue

            val index = num.digitToInt() - 1

            if (rows[i][index]) return false
            rows[i][index] = true

            if (columns[j][index]) return false
            columns[j][index] = true

            val boxNumber = (i / 3) * 3 + (j / 3)
            if (boxes[boxNumber][index]) return false
            boxes[boxNumber][index] = true
        }
    }

    return true
}

fun main() {
    val testCases = listOf(
        // Official example
        Pair(arrayOf(
            "53..7....".toCharArray(),
            "6..195...".toCharArray(),
            ".98....6.".toCharArray(),
            "8...6...3".toCharArray(),
            "4..8.3..1".toCharArray(),
            "7...2...6".toCharArray(),
            ".6....28.".toCharArray(),
            "...419..5".toCharArray(),
            "....8..79".toCharArray()
        ), true),

        // Additional realistic test cases
        Pair(arrayOf(
            "........3".toCharArray(),
            "......1..".toCharArray(),
            ".....2...".toCharArray(),
            "....3....".toCharArray(),
            "...4.....".toCharArray(),
            "..5......".toCharArray(),
            ".6.......".toCharArray(),
            "7........".toCharArray(),
            ".........".toCharArray()
        ), true), // no duplicates at all

        Pair(arrayOf(
            "53..7....".toCharArray(),
            "6..195...".toCharArray(),
            ".98....6.".toCharArray(),
            "8...6...3".toCharArray(),
            "4..8.3..1".toCharArray(),
            "7...2...6".toCharArray(),
            ".6....28.".toCharArray(),
            "...419..5".toCharArray(),
            "5...8..79".toCharArray()
        ), false), // duplicate '5' in first column

        Pair(arrayOf(
            "5.3......".toCharArray(),
            ".53.....1".toCharArray(),
            "........2".toCharArray(),
            "........3".toCharArray(),
            "........4".toCharArray(),
            "........5".toCharArray(),
            "........6".toCharArray(),
            "........7".toCharArray(),
            "........8".toCharArray()
        ), false), // duplicate '5' in box

        Pair(arrayOf(
            "123456789".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray()
        ), true),

        Pair(arrayOf(
            "123456789".toCharArray(),
            "1........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray()
        ), false), // duplicate '1' in first column

        Pair(arrayOf(
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            "....5....".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            "....5....".toCharArray()
        ), false), // duplicate '5' in middle column

        Pair(arrayOf(
            "......9..".toCharArray(),
            "......9..".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray()
        ), false), // duplicate '9' in same column

        Pair(arrayOf(
            "....1....".toCharArray(),
            "...1.....".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray()
        ), false), // duplicate '1' in top-left 3x3 box

        Pair(arrayOf(
            "2.......3".toCharArray(),
            ".2......3".toCharArray(),
            ".....2...".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            ".........".toCharArray(),
            "3.......2".toCharArray(),
            "..3......".toCharArray(),
            ".........".toCharArray()
        ), false) // multiple duplicates in rows, cols, boxes
    )

    validateSolution(testCases, ::isValidSudoku)
    printDivider()
    validateSolution(testCases, ::isValidSudoku2)
}
