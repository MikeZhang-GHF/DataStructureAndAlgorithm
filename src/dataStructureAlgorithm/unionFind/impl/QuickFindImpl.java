package dataStructureAlgorithm.unionFind.impl;

import dataStructureAlgorithm.unionFind.IUnionFind;

/**
 * (0,1) (1,2) (2,3) ==> union to one set
 */

public class QuickFindImpl implements IUnionFind {
    /**
     * The count of nodes
     */
    int count;

    /**
     * Node
     */
    private int[] id;


    public QuickFindImpl(int count) {
        this.count = count;
        id = new int[count];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int x, int y) {
        // Set 1
        int A = find(x);
        // Set 2
        int B = find(y);

        if (A == B) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == A) {
                id[i] = B;
            }
        }
    }

    @Override
    public int find(int index) {
        // Validate the index
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Out of Range");
        }
        return id[index];
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
