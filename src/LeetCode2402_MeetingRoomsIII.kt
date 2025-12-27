/**
 * 2402: Meeting Rooms III
 * Hard
 * https://leetcode.com/problems/meeting-rooms-iii/
 *
 * You are given an integer $n$ and a 2D integer array `meetings` where `meetings[i] = [start_i, end_i]`.
 * All meetings are scheduled in an office building containing $n$ meeting rooms, labeled from $0$ to $n-1$.
 *
 * When a meeting needs to be held, you must use an available meeting room with the **lowest** index.
 * If no meeting room is available, the meeting will be delayed until a room becomes free. The delayed meeting will start at the first time the room becomes free.
 *
 * When a meeting is delayed, its duration remains the same. The new end time will be $\text{new\_start} + (\text{end\_i} - \text{start\_i})$.
 *
 * Return the index of the meeting room that held the **most** meetings. If there are multiple rooms that held the maximum number of meetings, return the one with the **smallest** index.
 *
 * Example 1:
 * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * Output: 0
 * Explanation:
 * - Meeting [0,10]: Room 0 is available. End time: 10. Room 0 count: 1.
 * - Meeting [1,5]: Room 1 is available. End time: 5. Room 1 count: 1.
 * - Meeting [2,7]: Both rooms busy (R0 until 10, R1 until 5). Room 1 frees at 5. New start: 5. New end: $5 + (7-2) = 10$. Room 1 count: 2.
 * - Meeting [3,4]: Both rooms busy (R0 until 10, R1 until 10). Both free at 10. Room 0 (lowest index) is chosen. New start: 10. New end: $10 + (4-3) = 11$. Room 0 count: 2.
 * Total counts: R0: 2, R1: 2. Tiebreaker: Smallest index is 0.
 *
 * Example 2:
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * Output: 1
 * Explanation:
 * - Meeting [1,20]: Room 0. End: 20. R0 count: 1.
 * - Meeting [2,10]: Room 1. End: 10. R1 count: 1.
 * - Meeting [3,5]: Room 2. End: 5. R2 count: 1.
 * - Meeting [4,9]: Room 2 frees at 5. New start: 5. New end: $5 + (9-4) = 10$. R2 count: 2.
 * - Meeting [6,8]: Room 1 frees at 10. Room 2 frees at 10. Room 1 (lowest index) is chosen. New start: 10. New end: $10 + (8-6) = 12$. R1 count: 2.
 * Total counts: R0: 1, R1: 2, R2: 2. Tiebreaker: Smallest index is 1.
 *
 * Constraints:
 * $1 \le n \le 100$
 * $1 \le meetings.length \le 10^5$
 * $meetings[i].length == 2$
 * $0 \le start_i < end_i \le 10^9$
 * All $start_i$ are unique.
 *
 * @param n The number of meeting rooms.
 * @param meetings The list of meetings, [start, end].
 * @return The index of the room that held the most meetings.
 */
private fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
    meetings.sortBy { (startTime, _) -> startTime }
    val meetingRooms = LongArray(n)
    val meetingCount = IntArray(n)
    for ((startTime, endTime) in meetings) {
        var minEndTime = Long.MAX_VALUE
        var room = -1
        var meetingRoomFound = false
        for (i in 0 until n) {
            if (meetingRooms[i] <= startTime) {
                meetingRooms[i] = endTime.toLong()
                meetingCount[i]++
                meetingRoomFound = true
                break
            }

            if (meetingRooms[i] < minEndTime) {
                minEndTime = meetingRooms[i]
                room = i
            }
        }

        if (meetingRoomFound) continue
        meetingRooms[room] += endTime - startTime
        meetingCount[room]++
    }

    var ans = -1
    var maxMeetingCount = 0
    for (i in 0 until n) {
        if (meetingCount[i] > maxMeetingCount) {
            maxMeetingCount = meetingCount[i]
            ans = i
        }
    }
    return ans
}

fun main() {
    // Function takes 2 arguments (n, meetings), so we must use Triple for (arg1, arg2, expected)
    val testCases = listOf(
        // Official examples
        Triple(2, arrayOf(intArrayOf(0, 10), intArrayOf(1, 5), intArrayOf(2, 7), intArrayOf(3, 4)), 0),
        Triple(3, arrayOf(intArrayOf(1, 20), intArrayOf(2, 10), intArrayOf(3, 5), intArrayOf(4, 9), intArrayOf(6, 8)), 1),

        // Additional edge cases (min 10 total)
        // 1. All meetings fit easily in the lowest-indexed room
        Triple(2, arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4)), 0),

        // 2. Only one room is available
        Triple(1, arrayOf(intArrayOf(1, 5), intArrayOf(2, 6), intArrayOf(3, 7)), 0),

        // 3. All rooms used once, highest room wins the count tie
        Triple(3, arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6)), 0),

        // 4. Meeting duration is long, forcing delays
        // M1 (1, 100): R0 End: 100.
        // M2 (2, 3): R1 End: 3.
        // M3 (3, 4): R1 frees at 3. New start: 3. New end: 4. R1 count: 2.
        // M4 (4, 5): R1 frees at 4. New start: 4. New end: 5. R1 count: 3.
        // M5 (101, 102): R0 frees at 100. New start: 101. New end: 102. R0 count: 2.
        // R0: 2, R1: 3. Winner: 1.
        Triple(2, arrayOf(intArrayOf(1, 100), intArrayOf(2, 3), intArrayOf(3, 4), intArrayOf(4, 5), intArrayOf(101, 102)), 1),

        // 5. Tie at the end, lowest index wins
        // M1 (1, 3): R0 End 3.
        // M2 (2, 4): R1 End 4.
        // M3 (4, 6): R0 frees at 3. R0 wins. New start: 4. New end: 6. R0 count: 2.
        // M4 (5, 7): R1 frees at 4. R1 wins. New start: 5. New end: 7. R1 count: 2.
        // R0: 2, R1: 2. Winner: 0.
        Triple(2, arrayOf(intArrayOf(1, 3), intArrayOf(2, 4), intArrayOf(4, 6), intArrayOf(5, 7)), 0),

        // 6. Large number of rooms (n=5)
        Triple(5, arrayOf(intArrayOf(1, 2), intArrayOf(3, 4), intArrayOf(5, 6), intArrayOf(7, 8), intArrayOf(9, 10)), 0),

        // 7. Meetings that are far apart in time
        Triple(2, arrayOf(intArrayOf(1, 2), intArrayOf(100, 101), intArrayOf(200, 201)), 0),

        // 8. Long delay, high room index wins
        // M1 (0, 10): R0 End: 10.
        // M2 (0, 10): R1 End: 10.
        // M3 (0, 1): Both busy at 0. Both free at 10. R0 wins. New start: 10. New end: 11. R0 count: 2.
        // M4 (0, 1): R1 frees at 10. New start: 11. New end: 12. R1 count: 2.
        // M5 (0, 1): R0 frees at 11. New start: 12. New end: 13. R0 count: 3.
        // R0: 3, R1: 2. Winner: 0.
        Triple(2, arrayOf(intArrayOf(0, 10), intArrayOf(0, 10), intArrayOf(0, 1), intArrayOf(0, 1), intArrayOf(0, 1)), 0)
    )

    validateSolution(testCases, ::mostBooked)
}