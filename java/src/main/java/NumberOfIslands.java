import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands noi = new NumberOfIslands();
        //{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}}
        char[][] matrix = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        int count = noi.numIslands(matrix);
        System.out.println(count);
    }

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == '1') {
                    count++;
//                    doBfsAndMark(r, c, grid);
                    doDfsAndMark(r, c, grid);
                }
            }
        }
        return count;
    }

    private void doDfsAndMark(int r, int c, char[][] grid) {
        System.out.println("DFS for " + r + ", " + c);
        grid[r][c] = '*'; //visited
        print2DIntArray(grid);
        getAdj(new int[]{r, c}, grid).forEach( it ->
                doDfsAndMark(it[0], it[1], grid)
        );
    }

    public void doBfsAndMark(int x, int y, char[][] grid) {
         System.out.println("BFS for " + x + ", " + y);
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            grid[curr[0]][curr[1]] = '*'; //visited
             System.out.println("Next --> " + curr[0] + ", " +curr[1]);
             print2DIntArray(grid);
            List<int[]> adjucency = getAdj(curr, grid);
            q.addAll(adjucency);
        }

    }

    int[][] offset = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public List<int[]> getAdj(int[] n, char[][] grid) {
        List<int[]> adj = new ArrayList<>();
        int r = n[0];
        int c = n[1];
        for (int[] move : offset) {
            int nextR = r + move[0];
            int nextC = c + move[1];

            if (nextR < 0 || nextR > grid.length - 1) {
                continue;
            } else if (nextC < 0 || nextC > grid[0].length - 1) {
                continue;
            } else {
                if (grid[nextR][nextC] == '1') {
                    adj.add(new int[]{nextR, nextC});
                     System.out.println("Adj: for " + n[0] + ", " + n[1]+ " is : " + nextR+ ", " +nextC);
                }
            }

        }
        return adj;
    }

    public static void print2DIntArray(char[][] arr) {
        for (int r = 0; r < arr.length; r++) {         //for loop for row iteration.
            for (int c = 0; c < arr[r].length; c++) {   //for loop for column iteration.
                System.out.printf("%-4s", arr[r][c] + "");
            }
            System.out.println();
        }
        System.out.println("------------------------------------- ");
    }

}
