public class StaircaseTraversal {

    public static void main(String[] args) {
        StaircaseTraversal program = new StaircaseTraversal();

        System.out.println(program.staircaseTraversal(4, 2));
    }

    public int staircaseTraversal(int height, int maxSteps) {
        return findWays(height, maxSteps);
    }

    public int findWays(int height, int maxSteps) {
        int count = 0;
        System.out.println("currentHeight = " + height + ", maxSteps = " + maxSteps + ", count = " + count);
        if (height == 0) {
            count = 1;
        }
        for (int i = 1; i <= maxSteps; i++) {
            if (i > height) break;
            int nextHeight = height - i;
            System.out.println("    currentHeight = "+ height +", finding ways for currentHeight - " + i + " count = " + count);
            int ways = findWays(nextHeight, maxSteps);
            count += ways;
        }
        System.out.println("        Found ways for height = " + height + " count = " + count);
        return count;
    }
}
