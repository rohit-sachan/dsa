import java.util.ArrayList;
import java.util.List;

public class NonAttackingQueens {

    public static void main(String[] args) {
        NonAttackingQueens naq =  new NonAttackingQueens();
        System.out.println(naq.nonAttackingQueens(4));
    }
    /*
    return number of ways n queens can be arranged legally on n * n
    */
    public int nonAttackingQueens(int n) {
        //because n queens then at least 1 queen per row(because queens can't be in same row or column) and per column has to be there
        List<List<Integer>> result = new ArrayList<>(); // list of column positions per row
        findPositions(n, 0, new ArrayList<>(),result);
        System.out.println(result);
        return result.size();
    }

    private void findPositions(int n, int row, ArrayList<Integer> columnPositions, List<List<Integer>> result) {
        if(row == n){
            System.out.println("reached last position row="+row +" n="+n );
            result.add(new ArrayList<>(columnPositions));
        }
        for (int col = 0; col < n; col++) {
            columnPositions.add(col);
            if(isValidPosition(columnPositions, n)){
                // if placed in current row then move on to next row , this row is done and cant place any more queen.
                findPositions(n, row+1, columnPositions, result);
            }
            columnPositions.remove(columnPositions.size()-1);
        }
    }

    private boolean isValidPosition(ArrayList<Integer> columnPositions, int n) {
        int rowTobePlacedQueen = columnPositions.size() - 1;
        Integer columnToBePlacedQueen = columnPositions.get(rowTobePlacedQueen);
        // now check if last placed queen is good or not.
        for (int r = 0; r < rowTobePlacedQueen; r++) {
            // if any of the previous queens's column is same as lastPlacedQueen then its not good
            if(inSameColumn(columnPositions.get(r), columnToBePlacedQueen)
                    || inDiagonalPath(r, columnPositions.get(r), columnToBePlacedQueen, rowTobePlacedQueen)){
                return false;
            }
        }
        return true;
    }

    private boolean inDiagonalPath(Integer rowExistingQueen, Integer columnExistingQueen, Integer columnToBePlacedQueen, Integer rowTobePlacedQueen) {
        return Math.abs(rowTobePlacedQueen - rowExistingQueen) == Math.abs(columnToBePlacedQueen - columnExistingQueen);
    }

    private boolean inSameColumn(Integer columnPosition , Integer lastPlacedQueen) {
        return columnPosition.equals(lastPlacedQueen);
    }

}
