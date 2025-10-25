package com.atur;

public class UnionFind {
    private int[] parent;
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }
    public int find(int x) {
        while (parent[x] != x) x = parent[x];
        return x;
    }
    public void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        parent[ra] = rb;
    }
}
