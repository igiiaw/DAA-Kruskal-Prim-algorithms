package com.atur;
import java.util.*;

public class Result {
    public List<Edge> mstEdges;
    public double costSum;
    public int vertices;
    public int edges;
    public long steps;
    public double timeMs;

    public Result(List<Edge> mstEdges, double costSum, int vertices, int edges, long steps, double timeMs) {
        this.mstEdges = mstEdges;
        this.costSum = costSum;
        this.vertices = vertices;
        this.edges = edges;
        this.steps = steps;
        this.timeMs = timeMs;
    }
}