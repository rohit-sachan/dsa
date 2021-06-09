import java.util.Comparator;
import java.util.PriorityQueue;

public class ContinuousMedianHandler {
    double median = 0;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(a -> -a));

    public void insert(int number) {
        System.out.println("Received " + number);
        if (minHeap.size() > 0 && number > minHeap.peek()) {
            minHeap.add(number);
        } else {
            maxHeap.add(number);
        }
        int sDiff = Math.abs(maxHeap.size() - minHeap.size());
        if (sDiff >= 2) {
            rebalance();
        }

        System.out.println(" minHeap " + minHeap);
        System.out.println(" maxHeap " + maxHeap);
        if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
            median = (minHeap.peek() + maxHeap.peek()) / 2.0;
            System.out.println("Total Even, median= " + median);
        } else {
            if (minHeap.size() > maxHeap.size()) {
                median = minHeap.peek();
                System.out.println("Total Odd minHeap, median= " + median);
            } else {
                median = maxHeap.peek();
                System.out.println("Total Odd maxHeap, median= " + median);
            }
        }
    }

    private void rebalance() {
        if (minHeap.size() > maxHeap.size()) {
            int minTop = minHeap.poll();
            maxHeap.add(minTop);
        } else {
            int maxTop = maxHeap.poll();
            minHeap.add(maxTop);
        }
    }

    public double getMedian() {
        return median;
    }
}