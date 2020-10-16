package dataStructureAlgorithm.unionFind.impl;

import dataStructureAlgorithm.unionFind.IUnionFind;

/**
 * (0,1) (1,2) (2,3) ==> union to one set
 */

public class UnionByWeightedImpl implements IUnionFind {
    /**
     * The count of nodes
     */
    int count;

    /**
     * Node
     */
    private int[] parents;

    /**
     * Weight
     */
    private int[] size;

    public UnionByWeightedImpl(int count) {
        this.count = count;
        parents = new int[count];
        this.size = new int[count];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void union(int x, int y) {
        // Set 1
        int xRoot = find(x);
        // Set 2
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }
        // Union two sets by weight
        if (size[xRoot] < size[yRoot]) {
            parents[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        } else {
            parents[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
        }
    }

    @Override
    public int find(int index) {
        // Validate the index
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Out of Range");
        }
        // Find the root of the tree from this node
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
