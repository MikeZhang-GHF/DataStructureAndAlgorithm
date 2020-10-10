package dataStructureAlgorithm.sortAlgorithm;

import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] nums) {
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            heapAdjust(nums, i, nums.length);
        }
        System.out.println(Arrays.toString(nums));
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapAdjust(nums, 0, i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void heapAdjust(int[] nums, int parent, int length) {
        // start points to parent node
        int child = 2 * parent + 1;

        while (child < length) {
            //1. compare left child and right child to get the bigger child
            if (child + 1 < length && nums[child] < nums[child + 1]) {
                child++;
            }
            //2. compare the bigger child with its parent, = must have because of leaf node
            if (nums[parent] >= nums[child]) {
                // if parent is bigger than or equal to its bigger child, the parent is in the right position
                break;
                // if parent is smaller than its bigger child, swap
            } else {
                // swap the parent node and bigger child node
                swap(nums, parent, child);
            }
            // Move down on level deeper
            parent = child;
        }
    }

    /**
     * Test the heap sort
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{2, 23, 30, 8, 32, 31, 41, 1, 15};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
