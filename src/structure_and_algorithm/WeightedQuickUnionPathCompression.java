package src.structure_and_algorithm;
// O(N+Mα(N)):N the number of elements, M the number of operations, α is the inverse Ackermann function
public class WeightedQuickUnionPathCompression implements DisjointSets {
    private int[] parent;
    private int[] size;

    public WeightedQuickUnionPathCompression(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]); // Path compression
        }
        return parent[p];
    }

    public void connect(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
