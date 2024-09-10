import java.util.*;

class Edge{
    int u,v,w;
    Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class EdgeCom implements Comparator<Edge>{
    public int compare(Edge u, Edge v){
        return u.w - v.w;
    }
}

class prims_mst {
    static final int N = 100007;
    static Vector<Edge>[] vector = new Vector[N];
    static boolean[] visited = new boolean[N];

    public static void prims(int s){
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeCom());
        Vector<Edge> edgeList = new Vector<>();

        pq.add(new Edge(s, s, 0));

        while(!pq.isEmpty()){
            Edge parent = pq.poll();
            int u = parent.u;
            int v = parent.v;
            int w = parent.w;

            if(visited[v]) continue;
            visited[v] = true;
            edgeList.add(parent);

            for(Edge child: vector[v]){
                if(!visited[child.v]){
                    pq.add(new Edge(v, child.v, child.w));
                }
            }
        }

        edgeList.remove(0);
        System.out.println("-----------");
        System.out.println("Edge List");
        int count = 0;
        for(Edge edge: edgeList){
            System.out.println(edge.u+" "+edge.v+" "+edge.w);
            count += edge.w;
        }
        System.out.println("Weight value summation is: " + count);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your node: ");
        int n = in.nextInt();
        System.out.print("Enter your edge: ");
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

        // prims algorithm call here 
        prims(1);
    }
}