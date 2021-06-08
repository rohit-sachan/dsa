public class GraphAlgos {
    int[][] adjccy = {{1, 3}, {2, 3, 4}, {0}, {}, {2, 5}, {}};

    public static void main(String[] args) {
        GraphAlgos graphAlgos = new GraphAlgos();
        System.out.println(graphAlgos.cycleInGraph(graphAlgos.adjccy));
    }
    private boolean[] vis = new boolean[100];

    private boolean[] inpath = new boolean[100];

    public boolean cycleInGraph(int[][] edges) {
        boolean flag = false;
        for (int i = 0; i < edges.length; i++) {
            if(vis[i]) continue;
            flag = flag || detectCycle(i, -1, edges);
        }
        return flag;
    }

    public boolean detectCycle(int node, int par, int[][] edges){
        System.out.println("Current node="+node+" parent-node="+par);
        vis[node]=true;
        System.out.println("   vis="+print_array(vis));
        inpath[node]=true;
        System.out.println("inpath="+print_array(inpath));
        for (int it: edges[node]) {
            System.out.println("       ["+ node+"]visiting child node="+it);

            if(!inpath[it]){
                if(detectCycle(it, node, edges)){
                    return true;
                }
            } else {
                System.out.println("       ["+ node+"] ohho child node="+it + "is already inpath ");
                return true;
            }
        }
        inpath[node]=false;
        return false;
    }

    private String print_array(boolean[] arr){
        StringBuilder sb =  new StringBuilder();
        for(int  i = 0 ; i < arr.length; i++){
            if(arr[i]){
                sb.append("T"+" ");
            } else{
                sb.append("_"+" ");
            }
        }
        return sb.toString();

    }
}
