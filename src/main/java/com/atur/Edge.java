package com.atur;

public class Edge implements Comparable<Edge> {
    public int from;
    public int to;
    public double weight;
    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
    public String toString() {
        return String.format("%d-%d:%.2f", from, to, weight);
    }
}
