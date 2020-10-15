package dataStructureAlgorithm.unionFind;

public interface IUnionFind {

    /**
     * Count the number of nodes
     *
     * @return
     */
    int count();

    /**
     * Merge two nodes in the set
     *
     * @param x
     * @param y
     */
    void union(int x, int y);

    /**
     * Find the corresponding set based on index
     *
     * @param index
     * @return
     */
    int find(int index);

    /**
     * Check if two nodes are in the same set
     *
     * @param x
     * @param y
     * @return
     */
    boolean connected(int x, int y);
}
