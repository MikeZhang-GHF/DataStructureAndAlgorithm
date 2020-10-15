package graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DfsTopologicalSort {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        // Init the graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        // Convert the prerequisites to graph
        for (int[] pair : prerequisites) {
            // the prerequisite course
            int prev = pair[1];
            int next = pair[0];
            graph.get(prev).add(next);
        }

        /**
         * DFS
         * 0: unvisited 1: visiting 2: visited
         */
        HashMap<Integer, Integer> visited = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            visited.put(i, 0);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            //if the node is unvisited
            if (visited.get(i) == 0) {
                // if cycle in the path exists, return 0
                if (!helper(graph, visited, res, i)) {
                    return new int[0];
                }
            }
        }
        int[] result = new int[numCourses];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(numCourses - i - 1);
        }
        return result;
    }

    /**
     * Traverse the graph to check the cycle in the path
     *
     * @param graph
     * @param visited
     * @param res
     * @param i
     * @return
     */
    private boolean helper(List<List<Integer>> graph, HashMap<Integer, Integer> visited,
                           List<Integer> res, int i) {
        // the status of the node is visited, indicating there is no cycle in the path, its a topological sort
        if (visited.get(i) == 2) {
            return true;
        }
        // the status of the node is visiting again, a cycle in the path exists
        if (visited.get(i) == 1) {
            return false;
        }
        // Set the status of the node is visiting
        visited.put(i, 1);
        for (int j : graph.get(i)) {
            // check if a cycle exists in the subgraph
            if (!helper(graph, visited, res, i)) {
                return false;
            }
        }
        // Set the status of the node is visited if there is no cycle in the subgraph
        visited.put(i, 2);
        res.add(i);
        return true;
    }
}
