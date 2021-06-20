import java.util.*;

public class TopologicalSort {
    static Set<Integer> currentpath = new HashSet<>();

    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        Map<Integer, List<Integer>> adj = createAdjacencyList(jobs, deps);
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> topoStack = new Stack<>();
        boolean possible = true;
        for (Integer job : jobs) {
            possible = possible && findTopo(job, adj, visited, topoStack, possible);
        }
        if (!possible) {
            System.out.println(" Found cycle");
            return new ArrayList<Integer>();
        }

        ArrayList<Integer> list = new ArrayList<>(topoStack);
        Collections.reverse(list);
        return list;
    }

    public static LinkedHashMap<Integer, List<Integer>> createAdjacencyList(List<Integer> jobs, List<Integer[]> deps) {
        LinkedHashMap<Integer, List<Integer>> res = new LinkedHashMap<>();
        for (Integer[] dep : deps) {
            res.compute(dep[0], (k, v) -> {
                if (v == null) {
                    List<Integer> adj = new ArrayList<Integer>();
                    adj.add(dep[1]);
                    return adj;
                } else {
                    v.add(dep[1]);
                    return v;
                }
            });
        }
        return res;
    }

    public static boolean findTopo(Integer job, Map<Integer, List<Integer>> adjs, Set<Integer> visited, Stack<Integer> topoStack, boolean possible) {
        System.out.println("job = " + job + ", adjs = " + adjs + ", visited = " + visited + ", topoStack = " + topoStack + ", possible = " + possible + ", currentpath = " + currentpath);
        if (currentpath.contains(job)) {
            System.out.println(" * job = " + job + " is already in current path = " + currentpath + " it's a Cycle, can't find topological short");
            return false;
        }
        if (!visited.contains(job)) {
            currentpath.add(job);
            System.out.println("   + added job = " + job + " in current path = " + currentpath);
            if (adjs.get(job) != null) {
                for (Integer adj : adjs.get(job)) {
                    possible = possible && findTopo(adj, adjs, visited, topoStack, possible);
                }
            }
            currentpath.remove(job);
            System.out.println("   - removed job = " + job + " in current path = " + currentpath);
            topoStack.push(job);
        }
        visited.add(job);
        return possible;
    }

    public static void main(String[] args) {
        List<Integer> jobs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Integer[][] depsArray = new Integer[][]{{3, 1}, {8, 1}, {8, 7}, {5, 7}, {5, 2}, {1, 4}, {6, 7}, {1, 2}, {7, 6}};

//        List<Integer> jobs = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
//        Integer[][] depsArray = new Integer[][]{{1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3}};

        List<Integer[]> deps = new ArrayList<Integer[]>();
        Collections.addAll(deps, depsArray);
        List<Integer> order = topologicalSort(jobs, deps);
    }
}
