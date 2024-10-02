import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, w;

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.w, other.w);
    }
}

public class secondMSTKruskal {
    static final int N = 100007;
    static int[] parent = new int[N];
    static int[] rank = new int[N];
    static Vector<Edge> edges = new Vector<>();

    // Disjoint set (union-find) functions
    public static void makeSet(int n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public static int find(int u) {
        if (parent[u] != u)
            parent[u] = find(parent[u]); // Path compression
        return parent[u];
    }

    public static void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }

    // Kruskal's algorithm to find the MST, excluding a particular edge
    public static Vector<Edge> kruskal(int n, Edge excluded) {
        makeSet(n);
        Vector<Edge> mstEdges = new Vector<>();
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (excluded != null && edge == excluded) continue;

            int rootU = find(edge.u);
            int rootV = find(edge.v);

            if (rootU != rootV) {
                union(rootU, rootV);
                mstEdges.add(edge);
                edgeCount++;

                if (edgeCount == n - 1) break; 
            }
        }

        // If less than n-1 edges are used, return an empty MST (not possible)
        if (edgeCount != n - 1) return new Vector<>();
        return mstEdges;
    }

    public static int calculateMSTWeight(Vector<Edge> mstEdges) {
        int weight = 0;
        for (Edge edge : mstEdges) {
            weight += edge.w;
        }
        return weight;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); 
        int e = in.nextInt();

        for (int i = 0; i < e; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edges.add(new Edge(u, v, w));
        }

        // Sort edges by weight
        Collections.sort(edges);

        // Find the first MST
        Vector<Edge> firstMST = kruskal(n, null);
        int firstMSTWeight = calculateMSTWeight(firstMST);

        for(Edge fe: firstMST){
            System.out.println(fe.u+" "+fe.v+" "+fe.w);
        }
        System.out.println(firstMSTWeight);

        // Find the second MST
        Vector<Edge> secondMST = new Vector<>();
        int secondMSTWeight = Integer.MAX_VALUE;

        for (Edge edge : firstMST) {
            Vector<Edge> candidateMST = kruskal(n, edge);
            int candidateWeight = calculateMSTWeight(candidateMST);
            if (candidateWeight > firstMSTWeight && candidateWeight < secondMSTWeight) {
                secondMSTWeight = candidateWeight;
                secondMST = candidateMST;
            }
        }

        for(Edge se: secondMST){
            System.out.println(se.u+" "+se.v+" "+se.w);
        }
        System.out.println(secondMSTWeight);

        // Find the third MST
        Vector<Edge> thirdMST = new Vector<>();
        int thirdMSTWeight = Integer.MAX_VALUE;

        for (Edge edge : secondMST) {
            Vector<Edge> candidateMST = kruskal(n, edge);
            int candidateWeight = calculateMSTWeight(candidateMST);
            if (candidateWeight > secondMSTWeight && candidateWeight < thirdMSTWeight) {
                thirdMSTWeight = candidateWeight;
                thirdMST = candidateMST;
            }
        }


        for(Edge te: thirdMST){
            System.out.println(te.u+" "+te.v+" "+te.w);
        }
        System.out.println(thirdMSTWeight);

        // Find the fourth MST
        Vector<Edge> fourthMST = new Vector<>();
        int fourthMSTWeight = Integer.MAX_VALUE;

        for (Edge edge : thirdMST) {
            Vector<Edge> candidateMST = kruskal(n, edge);
            int candidateWeight = calculateMSTWeight(candidateMST);
            if (candidateWeight > thirdMSTWeight && candidateWeight < fourthMSTWeight) {
                fourthMSTWeight = candidateWeight;
                fourthMST = candidateMST;
            }
        }
        
        for(Edge ffe: fourthMST){
            System.out.println(ffe.u+" "+ffe.v+" "+ffe.w);
        }
        System.out.println(fourthMSTWeight);
    }
}
