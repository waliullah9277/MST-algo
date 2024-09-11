import java.util.*;

class Edge{
    int u,v,w;
    public Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class kruskal_mst {
    static final int N = 100007;
    static int[] parent = new int[N];
    static int[] parentSize = new int[N];

    public static void dsu_set(int n){
        for(int i=1; i<=n; i++){
            parent[i] = -1;
            parentSize[i] = 1;
        }
    }

    public static int dsu_find(int node){
        while(parent[node] != -1){
            node = parent[node];
        }
        return node;
    }

    public static void dsu_union(int a, int b){
        int leaderA = dsu_find(a);
        int leaderB = dsu_find(b);

        if(leaderA != leaderB){
            if(parentSize[leaderA] > parentSize[leaderB]){
                parent[leaderA] = leaderB;
                parentSize[leaderA] += parentSize[leaderB];
            }
            else{
                parent[leaderB] = leaderA;
                parentSize[leaderB] += parentSize[leaderA];
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your node: ");
        int n = in.nextInt();
        System.out.print("Enter your edge: ");
        int e = in.nextInt();

        Vector<Edge> vector = new Vector<>();
        Vector<Edge> ans = new Vector<>();

        dsu_set(n);

        for(int i=0; i<e; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            vector.add(new Edge(u, v, w));
        }

        Collections.sort(vector,new Comparator<Edge>() {
            @Override
            public int compare(Edge u, Edge v){
                return u.w - v.w;
            }
        });

        for(Edge edge: vector){
            int u = edge.u;
            int v = edge.v;
            int w = edge.w;

            int leaderA = dsu_find(u);
            int leaderB = dsu_find(v);
            
            if(leaderA == leaderB) continue;

            ans.add(edge);
            dsu_union(u,v);
        }

        System.out.println();
        for(Edge val: ans){
            System.out.println(val.u+" "+val.v+" "+val.w);
        }
    }
}
