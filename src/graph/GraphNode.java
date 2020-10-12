package graph;

import java.util.ArrayList;

public class GraphNode {

    int label;

    List<GraphNode> neighbors;

    public GraphNode(int label) {
        this.label = label;
        neighbors = new ArrayList<>();
    }
}
