package dataStructureAlgorithm.graph.prim;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {

    public void primMST(Graph graph) {

        boolean[] visited = new boolean[graph.vertices];
        int[] minDists = new int[graph.vertices];
        int[] parents = new int[graph.vertices];

        // Init the 3 variables
        Arrays.fill(minDists, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);

        /**
         *  Find the min value of the path of a node or a compressed node,
         *  we use the priority queue to sort the weight of paths.
         *
         *  Pair: weight, vertex
         */
        PriorityQueue<Pair<Integer, Integer>> priorityQueue =
                new PriorityQueue<>(graph.vertices, new Comparator<Pair<Integer, Integer>>() {
                    @Override
                    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                        int key1 = o1.getKey();
                        int key2 = o2.getKey();
                        return key1 - key2;
                    }
                });

        minDists[0] = 0;
        priorityQueue.offer(new Pair<>(minDists[0], 0));
        // Traverse the graph
        while (!priorityQueue.isEmpty()) {
            // Check the current node
            Pair<Integer, Integer> pair = priorityQueue.poll();
            int vertex = pair.getValue();
            visited[vertex] = true;

            // Check all the edges of the current node
            List<Edge> list = graph.list[vertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //
                if (!visited[edge.end]) {
                    int destination = edge.end;
                    int curDist = edge.weight;
                    if (curDist < minDists[destination]) {
                        priorityQueue.offer(new Pair<>(curDist, destination));
                        parents[destination] = vertex;
                        minDists[destination] = curDist;
                    }
                }
            }
        }
        print(parents, minDists, graph.vertices);
    }

    public void print(int[] parents, int[] minDists, int vertices) {
        int total = 0;
        System.out.println("MST : ");
        for (int i = 0; i < parents.length; i++) {
            System.out.println("Edge : " + i + "-" + parents[i] + " minDist : " + minDists[i]);
            total += minDists[i];
        }
        System.out.println("Total : " + total);
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 4, 6);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 6);

        Prim prim = new Prim();
        prim.primMST(graph);

    }
}
