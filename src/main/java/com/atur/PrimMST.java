package com.atur;
import java.util.*;

public class PrimMST {
    public static Result run(Graph g) {
        int nodes = g.nodes;
        boolean[] used = new boolean[nodes];
        double[] minEdge = new double[nodes];
        int[] selEdge = new int[nodes];
        for (int i = 0; i < nodes; i++) {
            minEdge[i] = Double.POSITIVE_INFINITY;
            selEdge[i] = -1;
        }
        List<Edge> result = new ArrayList<>();
        minEdge[0] = 0;
        long ops = 0;
        double start = System.nanoTime();
        for (int i = 0; i < nodes; i++) {
            int v = -1;
            for (int j = 0; j < nodes; j++) {
                if (!used[j] && (v == -1 || minEdge[j] < minEdge[v])) {
                    v = j;
                }
                ops++;
            }
            if (minEdge[v] == Double.POSITIVE_INFINITY)
                break;
            used[v] = true;
            if (selEdge[v] != -1) {
                result.add(new Edge(selEdge[v], v, minEdge[v]));
            }
            for (Edge e : g.edges) {
                if (e.from == v) {
                    if (!used[e.to] && e.weight < minEdge[e.to]) {
                        minEdge[e.to] = e.weight;
                        selEdge[e.to] = v;
                    }
                    ops++;
                } else if (e.to == v) {
                    if (!used[e.from] && e.weight < minEdge[e.from]) {
                        minEdge[e.from] = e.weight;
                        selEdge[e.from] = v;
                    }
                    ops++;
                }
            }
        }
        double end = System.nanoTime();
        double total = 0;
        for (Edge e : result) total += e.weight;
        return new Result(result, total, g.nodes, g.edges.size(), ops, (end - start) / 1000000);
    }
}