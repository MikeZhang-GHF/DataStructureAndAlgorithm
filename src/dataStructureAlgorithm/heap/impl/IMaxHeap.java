package dataStructureAlgorithm.heap.impl;

/**
 * Max Heap the parent is no less than its left and right children
 */
public interface IMaxHeap<E> {
    boolean offer(E e);

    E poll();

    E peek();

    int size();

    boolean isEmpty();

    void print();

}
