import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

public class KnightWalk {
    private static int minSteps;

    public static void main(String[] args) {
        System.out.println(minStepToReachTarget(new int[]{1, 3}, new int[]{5, 0}, 6));
    }

    public static int minStepToReachTarget(int knightPos[], int targetPos[], int n) {
        int step = 0;
        int[][] board = new int[n][n];
        for (int[] row : board) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(knightPos);
        while (!q.isEmpty()) {
            int currentSizeAtLevel = q.size();
            for (int i = 0; i < currentSizeAtLevel; i++) {
                int[] currentPos = q.poll();
                if (board[currentPos[0]][currentPos[1]] != Integer.MIN_VALUE) {
                    System.out.println("visiting " + (currentPos[0]) + ", " + (currentPos[1]));
                    if ((currentPos[0] == targetPos[0]) && (currentPos[1] == targetPos[1])) {
                        return step;
                    }
                    board[currentPos[0]][currentPos[1]] = step;
                    q.addAll(neighbouringMoves(currentPos, n, board));
                }
            }
            System.out.println("Visited all " + currentSizeAtLevel + " neighbours at step " + step);
            Utils.print2DIntArray(board);
            step++;
        }
        return step;
    }

    private static Set<Pair<Integer, Integer>> cloneVisited(Set<Pair<Integer, Integer>> visited) {
        return visited.stream().map(e -> new Pair<Integer, Integer>(e.first, e.second)).collect(Collectors.toSet());
    }

    private static boolean reached(int[] targetPos, int[] nextPos) {
        return nextPos[0] == targetPos[0] && nextPos[1] == targetPos[1];
    }

    static int[] moveX = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] moveY = {1, 2, 2, 1, -1, -2, -2, -1};

    public static ArrayList<int[]> neighbouringMoves(int[] pos, int n, int[][] board) {
        ArrayList<int[]> possibleReach = new ArrayList<>();
        for (int i = 0; i < moveX.length; i++) {
            int x = pos[0];
            int y = pos[1];

            x = x + moveX[i];
            y = y + moveY[i];
            if ((x >= 0 && x < n && y >= 0 && y < n) && board[x][y] == Integer.MAX_VALUE) {
                possibleReach.add(new int[]{x, y});
            } else {
                System.out.println(x + ", " + y + " is not reachable");
            }
        }
        if (possibleReach.size() == 0) {
            System.out.println("pos = " + Arrays.toString(pos) + " has no possible reach");
        } else {
            System.out.println("possible reaches = " + possibleReach.stream().map(e -> "[" + e[0] + ", " + e[1] + "]").collect(Collectors.joining("; ")));
        }
        return possibleReach;
    }

    private static class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public String toString() {
            return "[" + first + ", " + second + "]";
        }

        public boolean equals(Object o) {
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair<F, S> other = (Pair<F, S>) o;
            if (other.first != this.first) return false;
            return other.second == this.second;
        }

        public int hashCode() {
            return 31 * first.hashCode() + second.hashCode();
        }

    }


    /**

     - - - - - - -
     - - * - * - -
     * - - - - - *
     - - - K - - -
     * - - - - - *
     - - * - * - -
     */

    /**

     - o - - - o
     - - - K - -
     - o - - - o
     - - o - * -
     - - * - - -
     * - - o - o
     */

    /**
     *

     */
}