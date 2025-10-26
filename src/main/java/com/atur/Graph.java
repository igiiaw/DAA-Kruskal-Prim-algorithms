package com.atur;
import java.util.*;

public class Graph {
    public int nodes;
    public List<Edge>[] adj;
    public List<Edge> edges;
    @SuppressWarnings("unchecked")
    public Graph(int nodes) {
        this.nodes = nodes;
        adj = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) adj[i] = new ArrayList<>();
        edges = new ArrayList<>();
    }
    public void addEdge(int u, int v, double w) {
        Edge e = new Edge(u, v, w);
        edges.add(e);
        adj[u].add(e);
        adj[v].add(e);
    }
}