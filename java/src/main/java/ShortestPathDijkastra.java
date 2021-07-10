import java.util.*;

public class ShortestPathDijkastra {
    public static void main(String[] args) {
        int[][][] adjWeights = {
                {{1, 2},{3, 3},{4, 2}},
                {{0, 1},{6, 3}},
                {{3, 9}},
                {{0, 3},{1, 4},{4, 4},{8, 7}},
                {{0, 1},{10, 3}},
                {{7, 1},{8, 4}},
                {{8, 1}},
                {},
                {{7, 1}},
                {{10, 2}},
                {}
        };
        new ShortestPathDijkastra().dijkstrasAlgorithm(3, adjWeights);

        /**
         * 0   1   2  3  4  5   6  7  8   9  10
         * [3, 4, -1, 0, 4, -1, 7, 8, 7, -1, 7]
         */
    }

    private static class NodeAndDistance implements Comparable<NodeAndDistance>{
        Integer vertex;
        Integer distance;

        public NodeAndDistance(Integer vertex, Integer distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(NodeAndDistance o) {
            return Integer.compare(this.distance,o.distance);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", "[", "]")
                    .add("v=" + vertex)
                    .add("d=" + distance)
                    .toString();
        }
    }



    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        int[] minDistances = new int[edges.length];
        Arrays.fill(minDistances, Integer.MAX_VALUE);
        minDistances[start] = 0;
        exploreVertex(start, edges, minDistances);

        for (int i = 0; i < minDistances.length; i++) {
            minDistances[i] = (minDistances[i] == Integer.MAX_VALUE) ? -1 : minDistances[i];
        }

        Arrays.stream(minDistances).forEach(i -> System.out.print(i + ", "));
        return minDistances;
    }

    private void exploreVertex(int v, int[][][] adjacency, int[] allMinDistances) {
        PriorityQueue<NodeAndDistance> pq = new PriorityQueue<>();
        pq.add(new NodeAndDistance(v, 0));

        //Its greedy BFS search that's why using PQ instead of queue
        while (!pq.isEmpty()) {
            System.out.println("allMinDistances = " + Arrays.toString(allMinDistances) + ", pq = " + pq);
            NodeAndDistance node = pq.poll();

            // And add edge in pq for exploration if its total cost is really better than the existing total cost
            Arrays.stream(adjacency[node.vertex]).forEach(edgeAndDistance -> {
                int d = allMinDistances[node.vertex] + edgeAndDistance[1];
                if (d < allMinDistances[edgeAndDistance[0]]) { // this is where we prefer shorter distance
                    System.out.println("adjVertex = " + edgeAndDistance[0] + " d=" + d + " < " + allMinDistances[edgeAndDistance[0]]);
                    allMinDistances[edgeAndDistance[0]] = d;
                    pq.offer(new NodeAndDistance(edgeAndDistance[0], d));
                }
            });
        }
    }
}
