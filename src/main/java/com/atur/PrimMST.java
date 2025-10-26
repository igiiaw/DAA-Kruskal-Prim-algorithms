package com.atur;
import java.util.*;

public class PrimMST {
    public static Result run(Graph g) {
        int nodes = g.nodes;
        boolean[] used = new boolean[nodes];
        double[] dist = new double[nodes];
        int[] parent = new int[nodes];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(parent, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        dist[0] = 0;
        pq.add(new int[]{0, 0});
        long ops = 0;
        long start = System.nanoTime();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            if (used[v]) continue;
            used[v] = true;
            for (Edge e : g.adj[v]) {
                int to = e.from == v ? e.to : e.from;
                ops++;
                if (!used[to] && e.weight < dist[to]) {
                    dist[to] = e.weight;
                    parent[to] = v;
                    pq.add(new int[]{to, (int)e.weight});
                }
            }
        }
        long end = System.nanoTime();
        List<Edge> res = new ArrayList<>();
        double total = 0;
        for (int i = 0; i < nodes; i++) {
            if (parent[i] != -1) {
                double w = dist[i];
                res.add(new Edge(parent[i], i, w));
                total += w;
            }
        }
        int edgeCount = g.edges.size();
        return new Result(res, total, g.nodes, edgeCount, ops, (end - start) / 1000000.0);
    }
}