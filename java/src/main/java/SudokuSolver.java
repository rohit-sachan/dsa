import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {

    public static void main(String[] args) {
        ArrayList<Integer> row1 = new ArrayList<>(Arrays.asList(7, 8, 0, 4, 0, 0, 1, 2, 0));
        ArrayList<Integer> row2 = new ArrayList<>(Arrays.asList(6, 0, 0, 0, 7, 5, 0, 0, 9));
        ArrayList<Integer> row3 = new ArrayList<>(Arrays.asList(0, 0, 0, 6, 0, 1, 0, 7, 8));
        ArrayList<Integer> row4 = new ArrayList<>(Arrays.asList(0, 0, 7, 0, 4, 0, 2, 6, 0));
        ArrayList<Integer> row5 = new ArrayList<>(Arrays.asList(0, 0, 1, 0, 5, 0, 9, 3, 0));
        ArrayList<Integer> row6 = new ArrayList<>(Arrays.asList(9, 0, 4, 0, 6, 0, 0, 0, 5));
        ArrayList<Integer> row7 = new ArrayList<>(Arrays.asList(0, 7, 0, 3, 0, 0, 0, 1, 2));
        ArrayList<Integer> row8 = new ArrayList<>(Arrays.asList(1, 2, 0, 0, 0, 7, 4, 0, 0));
        ArrayList<Integer> row9 = new ArrayList<>(Arrays.asList(0, 4, 9, 2, 0, 6, 0, 0, 7));

        List<List<Integer>> arr =
                Arrays.asList(
                        row1,
                        row2,
                        row3,
                        row4,
                        row5,
                        row6,
                        row7,
                        row8,
                        row9
                );

        SudokuSolver solver = new SudokuSolver();

        solver.solveSudoku(arr);
    }

    private static class Coordinate {
        int r;
        int c;
        public Coordinate (int r, int c) {
            this.r = r;
            this.c = c;
        }
        public Coordinate roll() {
            if (this.c == MAX - 1) {
                return new Coordinate(r+1, 0);
            } else {
                return new Coordinate(r, c+1);
            }
        }
        public String toString() {
            return "["+r+", "+c+"]";
        }
    }

    static int MAX = -1;

    public List<List<Integer>> solveSudoku(List<List<Integer>> board) {
        MAX = board.size();
        Utils.print2DList(board);
        tryNumbers(new Coordinate(0,0), board);
        Utils.print2DList(board);
        return board;
    }

    public boolean tryNumbers(Coordinate co, List<List<Integer>> board) {
        System.out.println(co);
        Utils.print2DList(board);

        if (co.r >= MAX) { // reached to the last
            return true;
        }

        if (board.get(co.r).get(co.c) != 0) { // already a number at that cell (partially filled)
            return tryNumbers(co.roll(), board);
        }

        boolean successful = false;
        for (int v = 1; v <= MAX; v++) {
            if (isRightFit(v, co.r, co.c, board)) {
                updateBoard(co, board, v);
                successful = tryNumbers(co.roll(), board);
                if(successful){
                   break;
                } else {
                    //revert to empty
                    updateBoard(co, board, 0);
                }
            }
        }
        return successful;
    }

    private void updateBoard(Coordinate co, List<List<Integer>> board, int v) {
        board.get(co.r).remove(co.c);
        board.get(co.r).add(co.c, v);
    }

    private boolean isRightFit(int v, int r, int c, List<List<Integer>> board) {
        return board.get(r).get(c) == 0 && checkIfNumberFitsInLocalSqr(v, r, c, board) && fitsInRow(v, r, c, board) && fitsInColumn(v, r, c, board);
    }

    private boolean fitsInColumn(int v, int r, int c, List<List<Integer>> board) {
        for (List<Integer> integers : board) {
            if (integers.get(c) == v) {
                return false;
            }
        }
        return true;
    }

    private boolean fitsInRow(int v, int r, int c, List<List<Integer>> board) {
        for (int c_ = 0; c_ < MAX; c_++) {
            if (board.get(r).get(c_) == v) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfNumberFitsInLocalSqr(int v, int r, int c, List<List<Integer>> board) {
        int blockSize = new Float(Math.sqrt(MAX)).intValue();
        int r_ = r % blockSize;
        int c_ = c % blockSize;
        int blockNumber_r = r / 3;
        int blockNumber_c = c / 3;

        for (int i = 0; i < blockSize; i++) {
            for (int j = 0; j < blockSize; j++) {
                int r_deduced = blockSize * blockNumber_r + i;
                int c_deduced = blockSize * blockNumber_c + j;
//                System.out.println("c_deduced = " + c_deduced + "r_deduced = " + r_deduced);
                if (board.get(r_deduced).get(c_deduced) == v) {
                    return false;
                }
            }
        }
        return true;
    }


}
