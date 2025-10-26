package com.atur;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        List<Graph> graphs = JsonIO.readGraphs("input.json");
        List<Result> primResults = new ArrayList<>();
        List<Result> kruskalResults = new ArrayList<>();
        for (Graph g : graphs) {
            primResults.add(PrimMST.run(g));
            kruskalResults.add(KruskalMST.run(g));
        }
        JsonIO.writeResult("output_prim.json", primResults);
        JsonIO.writeResult("output_kruskal.json", kruskalResults);
        writeCSV("results.csv", primResults, kruskalResults);
        System.out.println("done");
    }
    public static void writeCSV(String fileName, List<Result> prim, List<Result> kruskal) throws IOException {
        try (PrintWriter w = new PrintWriter(new FileWriter(fileName))) {
            w.println("graph_id,algorithm,vertices,edges,total_cost,steps,time_ms");
            for (int i = 0; i < prim.size(); i++) {
                Result k = kruskal.get(i);
                Result p = prim.get(i);
                w.printf("%d,kruskal,%d,%d,%.3f,%d,%.3f%n",
                        i + 1, k.vertices, k.edges, k.costSum, k.steps, k.timeMs);
                w.printf("%d,prim,%d,%d,%.3f,%d,%.3f%n",
                        i + 1, p.vertices, p.edges, p.costSum, p.steps, p.timeMs);
            }
        }
    }
}