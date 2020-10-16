package dataStructureAlgorithm.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {

    int label;

    List<GraphNode> neighbors;

    public GraphNode(int label) {
        this.label = label;
        neighbors = new ArrayList<>();
    }

    public void add(GraphNode node) {
        neighbors.add(node);
    }

}
