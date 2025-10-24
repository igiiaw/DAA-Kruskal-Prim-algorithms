package com.atur;
import java.util.*;

public class Graph {
    public int nodes;
    public List<Edge> edges;
    public Graph(int nodes) {
        this.nodes = nodes;
        this.edges = new ArrayList<>();
    }
    public void addEdge(int from, int to, double weight) {
        edges.add(new Edge(from, to, weight));
    }
}
