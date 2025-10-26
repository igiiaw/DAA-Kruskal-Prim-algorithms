package com.atur;
import java.util.*;

public class KruskalMST {
    public static Result run(Graph g) {
        List<Edge> edges = new ArrayList<>(g.edges);
        Collections.sort(edges);
        UnionFind uf = new UnionFind(g.nodes);
        List<Edge> res = new ArrayList<>();
        long start = System.nanoTime();
        for (Edge e : edges) {
            boolean merged = uf.union(e.from, e.to);
            if (merged) {
                res.add(e);
            }
        }
        long end = System.nanoTime();
        double total = 0;
        for (Edge e : res) total += e.weight;
        return new Result(res, total, g.nodes, g.edges.size(), uf.ops, (end - start) / 1000000.0);
    }
}