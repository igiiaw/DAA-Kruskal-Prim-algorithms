package com.atur;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Graph> graphs = JsonIO.readGraphs("input.json");
        List<Result> primResults = new ArrayList<>();
        List<Result> kruskalResults = new ArrayList<>();
        for (Graph g : graphs) {
            primResults.add(PrimMST.run(g));
            kruskalResults.add(KruskalMST.run(g));
        }
        JsonIO.writeResult("output_prim.json", primResults);
        JsonIO.writeResult("output_kruskal.json", kruskalResults);
    }
}