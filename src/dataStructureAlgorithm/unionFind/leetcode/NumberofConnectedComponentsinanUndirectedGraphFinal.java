package dataStructureAlgorithm.unionFind.leetcode;

public class NumberofConnectedComponentsinanUndirectedGraphFinal {

    private int find(int[] parents, int index) {
        while (index != parents[index]) {
            parents[index] = parents[parents[index]];
            index = parents[index];
        }
        return index;
    }

    private int find2(int[] parents, int index) {
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

    public int countComponents(int n, int[][] edges) {
        int res = n;
        int[] parents = new int[n];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] pair : edges) {
            int x = find(parents, pair[0]);
            int y = find(parents, pair[1]);
            if (x != y) {
                parents[x] = y;
                res--;
            }
        }
        return res;
    }
}
