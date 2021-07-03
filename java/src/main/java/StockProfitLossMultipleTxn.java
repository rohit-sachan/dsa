import utils.Utils;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StockProfitLossMultipleTxn {

    public static void main(String[] args) {
        int [] prices = {5, 11, 3, 50, 60, 90};
        int k =2;

        int profit = StockProfitLossMultipleTxn.maxProfitWithKTransactions(prices, k);
        System.out.println("profit = " + profit);
    }

    public static int maxProfitWithKTransactions(int[] prices, int k) {
        if(prices.length == 0 ) return 0;

        int minBuyPrice = prices[0];
        int[][] profit = new int [k+1][prices.length];

        for(int txn = 1; txn <= k; txn ++) {
            for (int d = 1; d < prices.length; d++) {
                minBuyPrice = min(prices[d], minBuyPrice);
                if(txn<2) {
                    profit[txn][d] = max(profit[txn][d - 1], prices[d] - minBuyPrice);
                } else {
                    profit[txn][d] = max(profit[txn][d - 1], findMaxInPreviousTxn(profit, prices, txn, d));
                }
            }
        }
        Utils.print2DIntArray(profit);
        return Arrays.stream(profit[k]).max().getAsInt();
    }

    private static int findMaxInPreviousTxn(int[][] profit, int[] prices, int txn, int d) {
        int currentPossibleSellingPrice = prices[d];
        // now find buy price in previous transactions, if want to sell today,
        int previousTransactionProfitIncludingReBuying = Integer.MIN_VALUE;
        for (int x=0; x< d ; x++) {
            previousTransactionProfitIncludingReBuying = max(
                    profit[txn-1][x]-prices[x],
                    previousTransactionProfitIncludingReBuying
            );
        }
        return currentPossibleSellingPrice + previousTransactionProfitIncludingReBuying;
    }
}
