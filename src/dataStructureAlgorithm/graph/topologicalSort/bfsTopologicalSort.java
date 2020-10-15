package dataStructureAlgorithm.graph.topologicalSort;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Time: O(V+E)
 * Space: O(V)
 */

public class bfsTopologicalSort {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int k = 0;
        // 1. Calculate the number of entry
        int[] entry = new int[numCourses];
        for (int[] pair : prerequisites) {
            entry[pair[0]]++;
        }
        // BFS
        Queue<Integer> queue = new LinkedList<>();
        // Put the entry whose number is zero into queue, the sort is set
        for (int i = 0; i < entry.length; i++) {
            if (entry[i] == 0) {
                queue.offer(entry[i]);
                res[k] = i;
                k++;
            }
        }
        //
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            // Minus 1 for the entry of all the nodes connected with pre
            for (int[] pair : prerequisites) {
                // pair[1] is the prerequisite course
                if (pair[1] == pre) {
                    entry[pair[0]]--;
                    // Check all the entry of neighbors of entry, put all nodes whose entry is 0 into the queue
                    if (entry[pair[0]] == 0) {
                        queue.offer(pair[0]);
                        res[k] = pair[0];
                        k++;
                    }
                }
            }
        }
        // if there is no cycle in the dataStructureAlgorithm.graph, all the nodes should be put into sort list. Otherwise, cycle exists.
        return (k == numCourses) ? res : new int[0];
    }
}
