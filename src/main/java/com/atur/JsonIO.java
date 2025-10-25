package com.atur;
import java.util.*;
import com.google.gson.*;
import java.io.*;

public class JsonIO {
    public static List<Graph> readGraphs(String filename) throws IOException {
        Reader r = new FileReader(filename);
        JsonElement je = JsonParser.parseReader(r);
        List<Graph> graphs = new ArrayList<>();
        JsonObject root = je.getAsJsonObject();
        JsonArray graphsArray = root.getAsJsonArray("graphs");
        for (JsonElement ge : graphsArray) {
            JsonObject go = ge.getAsJsonObject();
            JsonArray nodesArray = go.getAsJsonArray("nodes");
            int V = nodesArray.size();
            Graph g = new Graph(V);
            Map<String, Integer> nodeIndex = new HashMap<>();
            for (int i = 0; i < V; i++) {
                nodeIndex.put(nodesArray.get(i).getAsString(), i);
            }
            JsonArray edges = go.getAsJsonArray("edges");
            for (JsonElement e : edges) {
                JsonObject eo = e.getAsJsonObject();
                String from = eo.get("from").getAsString();
                String to = eo.get("to").getAsString();
                double weight = eo.get("weight").getAsDouble();
                int u = nodeIndex.get(from);
                int v = nodeIndex.get(to);
                g.addEdge(u, v, weight);
            }
            graphs.add(g);
        }
        r.close();
        return graphs;
    }
    public static void writeResult(String filename, List<Result> results) throws IOException {
        JsonArray arr = new JsonArray();
        for (Result r : results) {
            JsonObject ro = new JsonObject();
            ro.addProperty("vertices", r.vertices);
            ro.addProperty("edges", r.edges);
            ro.addProperty("totalCost", r.costSum);
            ro.addProperty("operations", r.steps);
            ro.addProperty("timeMs", r.timeMs);
            JsonArray eds = new JsonArray();
            for (Edge e : r.mstEdges) {
                JsonObject eo = new JsonObject();
                eo.addProperty("from", e.from);
                eo.addProperty("to", e.to);
                eo.addProperty("weight", e.weight);
                eds.add(eo);
            }
            ro.add("mstEdges", eds);
            arr.add(ro);
        }
        Writer w = new FileWriter(filename);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(arr, w);
        w.close();
    }
}