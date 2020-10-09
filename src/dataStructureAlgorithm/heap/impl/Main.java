package dataStructureAlgorithm.heap.impl;

/**
 * Test the implementation of IMaxHeap
 */
public class Main {
    public static void main(String[] args) {
        IMaxHeapImpl<Integer> maxHeap = new IMaxHeapImpl<>();
        Integer[] nums = new Integer[]{2, 23, 30, 8, 32, 31, 41, 1, 15};
        for (Integer num : nums) {
            maxHeap.offer(num);
        }
        maxHeap.print();
    }
}
