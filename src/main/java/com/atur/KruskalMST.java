package com.atur;
import java.util.*;

public class KruskalMST {
    public static Result run(Graph g) {
        List<Edge> edges = new ArrayList<>(g.edges);
        Collections.sort(edges);
        UnionFind uf = new UnionFind(g.nodes);
        List<Edge> res = new ArrayList<>();
        long ops = 0;
        double start = System.nanoTime();
        for (Edge e : edges) {
            int ru = uf.find(e.from);
            int rv = uf.find(e.to);
            ops++;
            if (ru != rv) {
                res.add(e);
                uf.union(ru, rv);
            }
        }
        double end = System.nanoTime();
        double total = 0;
        for (Edge e : res) total += e.weight;
        return new Result(res, total, g.nodes, g.edges.size(), ops, (end - start) / 1000000);
    }
}