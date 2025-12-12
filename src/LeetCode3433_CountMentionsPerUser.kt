/**
 * 3433: Count Mentions Per User
 * Medium
 * https://leetcode.com/problems/count-mentions-per-user/
 *
 * You are given an integer numberOfUsers and an array events of size n x 3.
 *
 * Each event is one of:
 *
 * 1. ["MESSAGE", timestamp, mentions_string]
 *    - Mentions string may contain:
 *        • "idX" tokens, where X ∈ [0, numberOfUsers−1], duplicates allowed
 *        • "ALL"   → mentions all users (online or offline)
 *        • "HERE"  → mentions only online users
 *
 * 2. ["OFFLINE", timestamp, idX]
 *    - User X becomes offline at time timestamp and stays offline for 60 time units.
 *    - User automatically returns online at time timestamp + 60.
 *
 * Rules:
 * - All users start online.
 * - If an OFFLINE and MESSAGE occur at same timestamp, the OFFLINE effect applies first.
 * - Each mention counts separately.
 * - Return an array mentions[i] = total mentions of user i across all MESSAGE events.
 *
 * Example 1:
 * Input:
 * numberOfUsers = 2
 * events = [
 *   ["MESSAGE","10","id1 id0"],
 *   ["OFFLINE","11","0"],
 *   ["MESSAGE","71","HERE"]
 * ]
 * Output: [2,2]
 *
 * Example 2:
 * Input:
 * numberOfUsers = 2
 * events = [
 *   ["MESSAGE","10","id1 id0"],
 *   ["OFFLINE","11","0"],
 *   ["MESSAGE","12","ALL"]
 * ]
 * Output: [2,2]
 *
 * Example 3:
 * Input:
 * numberOfUsers = 2
 * events = [
 *   ["OFFLINE","10","0"],
 *   ["MESSAGE","12","HERE"]
 * ]
 * Output: [0,1]
 *
 * Constraints:
 * 1 <= numberOfUsers <= 100
 * 1 <= events.length <= 100
 * 1 <= timestamp <= 100000
 * User in OFFLINE event is always currently online.
 *
 * @param numberOfUsers total user count
 * @param events list of events, each event is List<String> of size 3
 * @return IntArray of mention counts per user
 */
private fun countMentions(numberOfUsers: Int, events: List<List<String>>): IntArray {
    val offlineMap = mutableMapOf<Int, Long>()
    val ans = IntArray(numberOfUsers)
    val sortedEvents = events.sortedBy { (type, timeStamp, _) ->
        timeStamp.toFloat() + if (type == "MESSAGE") 0.1f else 0f
    }
    for (event in sortedEvents) {
        val (type, timestampString, mentionString) = event
        val timestamp = timestampString.toLong()
        when (type) {
            "OFFLINE" -> offlineMap[mentionString.toInt()] = timestamp
            else -> {
                when (mentionString) {
                    "HERE" -> {
                        for (i in ans.indices) {
                            if (!offlineMap.containsKey(i)) {
                                ans[i]++
                            } else if (offlineMap[i]!! + 60 <= timestamp) {
                                ans[i]++
                            }
                        }
                    }

                    "ALL" -> {
                        for (i in ans.indices) {
                            ans[i]++
                        }
                    }

                    else -> mentionString.split(" ").map {
                        it.removePrefix("id")
                    }.forEach {
                        ans[it.toInt()]++
                    }
                }
            }
        }
    }
    return ans
}

fun main() {
    val testCases = listOf(
        Triple(
            2,
            listOf(
                listOf("MESSAGE", "10", "id1 id0"),
                listOf("OFFLINE", "11", "0"),
                listOf("MESSAGE", "71", "HERE")
            ),
            intArrayOf(2, 2)
        ),
        Triple(
            2,
            listOf(
                listOf("MESSAGE", "10", "id1 id0"),
                listOf("OFFLINE", "11", "0"),
                listOf("MESSAGE", "12", "ALL")
            ),
            intArrayOf(2, 2)
        ),
        Triple(
            2,
            listOf(
                listOf("OFFLINE", "10", "0"),
                listOf("MESSAGE", "12", "HERE")
            ),
            intArrayOf(0, 1)
        ),
        Triple(
            3,
            listOf(
                listOf("MESSAGE", "2", "HERE"),
                listOf("OFFLINE", "2", "1"),
                listOf("OFFLINE", "1", "0"),
                listOf("MESSAGE", "61", "HERE")
            ),
            intArrayOf(1, 0, 2)
        ),
        Triple(
            2,
            listOf(
                listOf("MESSAGE", "70", "HERE"),
                listOf("OFFLINE", "10", "0"),
                listOf("OFFLINE", "71", "0")
            ),
            intArrayOf(1, 1)
        ),
        Triple(
            3,
            listOf(
                listOf("MESSAGE", "1", "ALL"),
                listOf("OFFLINE", "66", "1"),
                listOf("MESSAGE", "66", "HERE"),
                listOf("OFFLINE", "5", "1")
            ),
            intArrayOf(2, 1, 2)
        ),
        Triple(
            5,
            listOf(
                listOf("OFFLINE", "65", "3"),
                listOf("MESSAGE", "24", "HERE"),
                listOf("OFFLINE", "98", "2"),
                listOf("MESSAGE", "97", "HERE"),
                listOf("OFFLINE", "41", "1"),
                listOf("OFFLINE", "11", "0"),
                listOf("MESSAGE", "10", "id2 id2 id2 id4")
            ),
            intArrayOf(1, 1, 5, 1, 3)
        )
    )

    validateSolution(testCases, ::countMentions)
}
