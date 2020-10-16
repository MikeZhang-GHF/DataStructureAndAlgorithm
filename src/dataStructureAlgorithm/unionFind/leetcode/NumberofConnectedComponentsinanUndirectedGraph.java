package dataStructureAlgorithm.unionFind.leetcode;

public class NumberofConnectedComponentsinanUndirectedGraph {

    private int res;
    private int[] id;
    /**
     * The number of nodes
     */
    private int count;

    private int find(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("index is out of range");
        }
        return id[index];
    }

    private void union(int x, int y) {
        // Set 1
        int A = find(x);
        // Set 2
        int B = find(y);

        if (A == B) {
            return;
        }
        // union
        for (int i = 0; i < id.length; i++) {
            if (id[i] == A) {
                id[i] = B;
            }
        }
        //
        res--;
    }

    private boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int countComponents(int n, int[][] edges) {
        res = n;
        this.count = n;
        id = new int[count];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }

        for (int[] pair : edges) {
            int A = find(pair[0]);
            int B = find(pair[1]);
            union(A, B);
        }
        return res;
    }
}
