package dataStructureAlgorithm.unionFind.leetcode;

public class NumberofConnectedComponentsinanUndirectedGraph2 {

    private int res;
    private int[] parents;
    /**
     * The number of nodes
     */
    private int count;

    private int find(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("index is out of range");
        }
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

    private void union(int x, int y) {
        // Set 1
        int xRoot = find(x);
        // Set 2
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }
        // union
        parents[xRoot] = yRoot;
        res--;
    }

    private boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public int countComponents(int n, int[][] edges) {
        res = n;
        this.count = n;
        parents = new int[count];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] pair : edges) {
            int xRoot = find(pair[0]);
            int yRoot = find(pair[1]);
            union(xRoot, yRoot);
        }
        return res;
    }
}
