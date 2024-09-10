import java.util.*;

class Edge{
    int u,v,w;
    public Edge(int u, int v, int w){
        this.u=u;
        this.v=v;
        this.w=w;
    }
}

class EdgeCmp implements Comparator<Edge>{
    public int compare(Edge u, Edge v){
        return Integer.compare(u.w, v.w);
    }
}

public class RoadReparation {
    static final int N = 100007;
    static boolean[] visited = new boolean[N];
    static Vector<Edge>[] vector = (Vector<Edge>[]) new Vector[N];


    public static void prims(int s, int n){
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeCmp());
        Vector<Edge> edgeList = new Vector<>();
        pq.add(new Edge(s, s, 0));
        int count = 0;

        while(!pq.isEmpty()){
            Edge parent = pq.poll();
            int u = parent.u;
            int v = parent.v;
            int w = parent.w;
            if(visited[v]) continue;
            visited[v] = true;
            count++;
            edgeList.add(parent);

            for(Edge child: vector[v]){
                if(!visited[child.v]){
                    pq.add(new Edge(v, child.v, child.w));
                }
            }
        }

        long sum = 0;
        for(Edge edge: edgeList){
            sum += (long)(edge.w);
        }

        if(count == n){
            System.out.println(sum);
        }
        else{
            System.out.println("IMPOSSIBLE");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();

        for(int i=0; i<=n; i++){
            vector[i] = new Vector<>();
        }

        for(int i=0; i<e; i++){
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            vector[u].add(new Edge(u, v, w));
            vector[v].add(new Edge(v, u, w));
        }

        prims(1,n);

    }
}
