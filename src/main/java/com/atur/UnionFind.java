package com.atur;

public class UnionFind {
    private int[] parent;
    private int[] rank;
    public long ops;
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        ops = 0;
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    public int find(int x) {
        ops++;
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }
    public boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        ops++;
        if (ra == rb) return false;
        if (rank[ra] < rank[rb]) parent[ra] = rb;
        else if (rank[rb] < rank[ra]) parent[rb] = ra;
        else {
            parent[rb] = ra;
            rank[ra]++;
        }
        return true;
    }
}