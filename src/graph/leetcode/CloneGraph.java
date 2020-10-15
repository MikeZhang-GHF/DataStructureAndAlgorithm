package graph.leetcode;

import java.util.*;

/**
 * This is very important problem to practice the DFS and BFS,
 * The nodes and edges needed to be copied.
 */

public class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return helper(node, new HashMap<>());
    }

    // DFS to clone graph
    private Node helper(Node node, HashMap<Node, Node> map) {
        List<Node> neighbors = new ArrayList<>();
        Node copy = new Node(node.val, neighbors);
        //
        map.put(node, copy);
        //
        for (Node neighbor : node.neighbors) {
            if (map.containsKey(neighbor)) {
                neighbors.add(helper(neighbor, map));
            } else {
                neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    // BFS to clone graph
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }

        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.offer(node);
        map.put(node, new Node(node.val));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            map.get(cur).neighbors = new ArrayList<>();
            // copy the nodes
            for (Node neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                // copy the edges
                map.get(cur).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}
