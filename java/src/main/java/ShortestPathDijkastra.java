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

    private void exploreVertex(int v, int[][][] edges, int[] minDistances) {
        PriorityQueue<NodeAndDistance> pq = new PriorityQueue<>();
        pq.add(new NodeAndDistance(v, 0));

        //Its greedy BFS search that's why using PQ instead of queue
        while (!pq.isEmpty()) {
            System.out.println("minDistances = " + Arrays.toString(minDistances) + ", pq = " + pq);
            NodeAndDistance node = pq.poll();
            int[][] adj = edges[node.vertex];
            for (int[] edgeWeights : adj) {
                int adjVertex = edgeWeights[0];
                int d = minDistances[node.vertex] + edgeWeights[1];
                // And add edge in pq for exploration if its total cost is really better than the existing total cost
                if(d < minDistances[adjVertex]){
                    System.out.println("adjVertex = " + adjVertex +" d="+ d + " < "+ minDistances[adjVertex]);
                    minDistances[adjVertex] =  d;
                    pq.offer(new NodeAndDistance(adjVertex, d));
                }
            }
        }
    }
}
