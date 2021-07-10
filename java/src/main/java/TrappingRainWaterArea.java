import java.util.Arrays;

public class TrappingRainWaterArea {
    public static void main(String[] args) {
        System.out.println(TrappingRainWaterArea.calculateWater(new int []{2, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int calculateWater(int[] heights){
        // for every index find the leftMax and rightMax height of pillar, water over that point will be "max(min(leftMax, rightMax)-heightAtIdx, 0)"
        // two loops ,
        //1st left to right and put leftMax for each index,
        //2nd right to left and put rightMax for each index and calculate + accumulate as well.

        int [] leftMaxArr = new int[heights.length];
        // left to right
        int maxAt = 0;
        for (int i=1; i < heights.length; i ++){
            maxAt = Math.max(heights[i-1], maxAt);
            leftMaxArr[i] = maxAt;
        }
        System.out.println("left to right -->" + Arrays.toString(leftMaxArr));



        //right to left
        maxAt = 0;
        int aw = 0;
        int [] rightMaxArr = new int[heights.length];
        for(int i = heights.length-2; i>=0; i--){
            maxAt = Math.max(heights[i+1], maxAt);
            rightMaxArr[i] = maxAt;
            System.out.println("right to left <--" + Arrays.toString(rightMaxArr));
            int waterAt = Math.max(Math.min(leftMaxArr[i], rightMaxArr[i]) - heights[i], 0);
            aw = aw + waterAt;
        }

        return aw;
    }
}
