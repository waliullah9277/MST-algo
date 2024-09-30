import java.util.*;

class Edge {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

class EdgeCom implements Comparator<Edge> {
    public int compare(Edge u, Edge v) {
        return u.w - v.w;
    }
}

class secondMST {
    static final int N = 100007;
    static Vector<Edge>[] vector = new Vector[N];
    static boolean[] visited = new boolean[N];

    public static Vector<Edge> prims(int s, Edge excluded) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeCom());
        Vector<Edge> edgeList = new Vector<>();
        Arrays.fill(visited, false);
        pq.add(new Edge(s, s, 0));

        while (!pq.isEmpty()) {
            Edge parent = pq.poll();
            int u = parent.u;
            int v = parent.v;
            int w = parent.w;
            if (visited[v]) continue;
            visited[v] = true;
            if (parent != excluded) {
                edgeList.add(parent);
            }
            for (Edge child : vector[v]) {
                if (!visited[child.v] && (excluded == null || !isEqual(child, excluded))) {
                    pq.add(new Edge(v, child.v, child.w));
                }
            }
        }
        edgeList.remove(0);
        return edgeList;
    }

    public static boolean isEqual(Edge e1, Edge e2) {
        return (e1.u == e2.u && e1.v == e2.v && e1.w == e2.w) ||
               (e1.u == e2.v && e1.v == e2.u && e1.w == e2.w);
    }

    public static int calculateWeight(Vector<Edge> edges) {
        int sum = 0;
        for (Edge e : edges) {
            sum += e.w;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        for (int i = 0; i <= n; i++) {
            vector[i] = new Vector<>();
        }
        for (int i = 0; i < e; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            vector[u].add(new Edge(u, v, w));
            vector[v].add(new Edge(v, u, w));
        }

        // Find the first MST
        Vector<Edge> firstMST = prims(0, null);
        int firstMSTWeight = calculateWeight(firstMST);
        // for(Edge fe: firstMST){
        //     System.out.println(fe.u+" "+fe.v+" "+fe.w);
        // }
        // System.out.println("Weight of the first MST: " + firstMSTWeight);

        // Find the second MST
        int secondMSTWeight = Integer.MAX_VALUE;
        Vector<Edge> secondMST = null;
        for (Edge excludedEdge : firstMST) {
            Vector<Edge> candidateMST = prims(0, excludedEdge);
            int candidateWeight = calculateWeight(candidateMST);
            if (candidateWeight > firstMSTWeight && candidateWeight < secondMSTWeight) {
                secondMSTWeight = candidateWeight;
                secondMST = candidateMST;
            }
        }

        // for(Edge se: secondMST){
        //         System.out.println(se.u+" "+se.v+" "+se.w);
        //     }
        // System.out.println("Weight of the second MST: " + secondMSTWeight);

        // Find the 3rd MST
        int thiredMSTWeight = Integer.MAX_VALUE;
        for (Edge excludedEdge : firstMST) {
            Vector<Edge> candidateMST = prims(0, excludedEdge);
            int candidateWeight = calculateWeight(candidateMST);

            if (candidateWeight > firstMSTWeight && candidateWeight > secondMSTWeight && candidateWeight < thiredMSTWeight) {
                thiredMSTWeight = candidateWeight;
            }
        }
        // System.out.println("Weight of the thired MST: " + thiredMSTWeight);

        // Find the 4th MST
        int fourMSTWeight = Integer.MAX_VALUE;
        Vector<Edge> fourMST = null;
        for (Edge excludedEdge : firstMST) {
            Vector<Edge> candidateMST = prims(0, excludedEdge);
            int candidateWeight = calculateWeight(candidateMST);

            if (candidateWeight > firstMSTWeight && candidateWeight > secondMSTWeight && candidateWeight > thiredMSTWeight && candidateWeight < fourMSTWeight) {
                fourMSTWeight = candidateWeight;
                fourMST = candidateMST;
            }
        }
        for(Edge fe: fourMST){
            System.out.println(fe.u+" "+fe.v+" "+fe.w);
        }
        // System.out.println("Weight of the four MST: " + fourMSTWeight);
        System.out.println(fourMSTWeight);

    }
}