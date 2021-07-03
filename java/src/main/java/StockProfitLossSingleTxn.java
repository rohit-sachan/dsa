import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class StockProfitLossSingleTxn {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        int profit = StockProfitLossSingleTxn.maxProfit(prices);
        System.out.println("profit = " + profit);
    }

    public static int maxProfit(int[] prices) {
        int minBuyPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            minBuyPrice = min(minBuyPrice, price);
            profit = max(profit, price - minBuyPrice);
        }
        System.out.println("minBuyPrice = " + minBuyPrice);
        Arrays.deepToString(new int[][]{prices});
        return profit;
    }
}
