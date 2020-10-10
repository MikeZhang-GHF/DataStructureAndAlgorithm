package dataStructureAlgorithm.heap.impl;

public class IMaxHeapImpl<E extends Comparable<E>> implements IMaxHeap {

    private int capacity;

    private int size;

    private E[] data;

    public IMaxHeapImpl() {
        this.capacity = 16;
        this.size = 0;
        this.data = (E[]) new Comparable[capacity];
    }

    public IMaxHeapImpl(E[] data) {
        this.capacity = data.length;
        this.size = data.length;
        this.data = data;
        heapify(data);
    }

    /**
     * Heapify
     * 1. We leave the leaf nodes
     * 2. starting from the last leaf node and
     * 3. siftdown the all the parent nodes
     * @param data
     */
    private void heapify(E[] data) {
        for (int i = getParentIndex(size - 1); i >= 0; i--) {
            siftDownHeapify(i);
        }
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private E leftChild(int index) {
        return data[getLeftChildIndex(index)];
    }

    private E rightChild(int index) {
        return data[getRightChildIndex(index)];
    }

    private E parent(int index) {
        return data[getParentIndex(index)];
    }

    private void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * siftUp
     * 1. put the data in the last position of array, data[size - 1]
     * 2. compare the data with its parent and swap if its necessary
     */
    private void siftup() {
        int index = size - 1;
        while (getParentIndex(index) >= 0 && parent(index).compareTo(data[index]) < 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    /**
     * Time: O(N)
     * h = sum((the height of heap)logN * N/2(the number of all leaves)) = (h-1) * N / 2
     * sum = n- logn => O(N)
     * @param index
     */
    private void siftDownHeapify(int index) {
        // Always start from position of zero
        while (getLeftChildIndex(index) < size) {
            int biggerChildIndex = getLeftChildIndex(index);
            // 1.Compare left and right child to get the bigger child
            if (getRightChildIndex(index) < size && rightChild(index).compareTo(leftChild(index)) > 0) {
                biggerChildIndex = getRightChildIndex(index);
            }
            // 2.Compare the bigger child with current to get the bigger one
            if (data[index].compareTo(data[biggerChildIndex]) > 0) {
                break;
            } else {
                swap(index, biggerChildIndex);
            }
            // 3. Move deeper in the Max heap
            index = biggerChildIndex;
        }
    }

    /**
     * siftDown
     * this function is a little bit complicated
     * 1. compare the data with its left child only(its impossible that it has right child only)
     * then relocate them if necessary
     */
    private void siftDown() {
        // Always start from position of zero
        int index = 0;
        while (getLeftChildIndex(index) < size) {
            int biggerChildIndex = getLeftChildIndex(index);
            // 1.Compare left and right child to get the bigger child
            if (getRightChildIndex(index) < size && rightChild(index).compareTo(leftChild(index)) > 0) {
                biggerChildIndex = getRightChildIndex(index);
            }
            // 2.Compare the bigger child with current to get the bigger one
            if (data[index].compareTo(data[biggerChildIndex]) > 0) {
                break;
            } else {
                swap(index, biggerChildIndex);
            }
            // 3. Move deeper in the Max heap
            index = biggerChildIndex;
        }
    }

    /**
     * Time: O(NlogN)
     * @param e
     * @return
     */

    @Override
    public boolean offer(Object e) {
        if (data.length == size) {
            expandCapacity();
        }
        // always put the data at the last position
        data[size] = (E) e;
        size++;
        siftup();
        return true;
    }

    private void expandCapacity() {
        E[] temp = (E[]) new Comparable[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            temp[i] = data[i];
        }
        capacity *= 2;
        data = temp;
    }


    @Override
    public E poll() {
        if (size == 0) {
            throw new IllegalArgumentException("Max heap is empty!");
        }
        E e = data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;
        siftDown();
        return e;
    }

    @Override
    public E peek() {
        if (size == 0) {
            throw new IllegalArgumentException("Max heap is empty!");
        }
        return data[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }
}
