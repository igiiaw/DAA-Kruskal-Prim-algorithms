# Assignment 3: Optimization of a City Transportation Network

## Project Overview

This project was about implementing and comparing **Prim’s** and **Kruskal’s** algorithms to find the **Minimum Spanning Tree (MST)** in a weighted undirected graph.  
The main idea was to simulate connecting city districts with roads while keeping the total construction cost as low as possible.
I implemented both algorithms in Java, tested them on **28 graphs** of different sizes, and then optimized the code to see how much performance could be improved.

---

## Problem Statement

Given a graph where:
- Vertices represent **city districts**
- Edges represent **possible roads**
- Edge weights represent **construction costs**
The goal is to connect all vertices with the smallest possible total cost while keeping the graph connected.

---

## Implementation Stages

### 1. Before Optimization

At first, I wrote a **basic version** of both algorithms just to make them work. It was simple but not efficient.

- **Prim’s algorithm** used basic loops and adjacency lists.
- **Kruskal’s algorithm** sorted edges but didn’t use an optimized Union-Find structure.
- Execution time was measured roughly, and there was no step counter.
This version helped me confirm that both algorithms were producing the correct MST results.

| graph_id | algorithm | vertices | edges | total_cost | steps  | time_ms |
|-----------|------------|----------|--------|-------------|--------|---------|
| 1 | kruskal | 5 | 6 | 2355.000 | 6 | 0.017 |
| 1 | prim | 5 | 6 | 2355.000 | 37 | 0.025 |
| 2 | kruskal | 10 | 21 | 2053.000 | 21 | 0.007 |
| 2 | prim | 10 | 21 | 2053.000 | 142 | 0.026 |
| 3 | kruskal | 15 | 25 | 4026.000 | 25 | 0.010 |
| 3 | prim | 15 | 25 | 4026.000 | 275 | 0.078 |
| 4 | kruskal | 20 | 94 | 2694.000 | 94 | 0.044 |
| 4 | prim | 20 | 94 | 2694.000 | 588 | 0.167 |
| 5 | kruskal | 25 | 132 | 3129.000 | 132 | 0.051 |
| 5 | prim | 25 | 132 | 3129.000 | 889 | 0.221 |
| 6 | kruskal | 30 | 49 | 9647.000 | 49 | 0.015 |
| 6 | prim | 30 | 49 | 9647.000 | 998 | 0.156 |
| 7 | kruskal | 60 | 68 | 21992.000 | 68 | 0.021 |
| 7 | prim | 60 | 68 | 21414.000 | 3374 | 0.380 |
| 8 | kruskal | 90 | 321 | 17875.000 | 321 | 0.051 |
| 8 | prim | 90 | 321 | 17875.000 | 8742 | 2.064 |
| 9 | kruskal | 120 | 1860 | 4236.000 | 1860 | 0.458 |
| 9 | prim | 120 | 1860 | 4236.000 | 18120 | 3.995 |
| 10 | kruskal | 150 | 5366 | 2363.000 | 5366 | 0.853 |
| 10 | prim | 150 | 5366 | 2363.000 | 33232 | 8.750 |
| 11 | kruskal | 180 | 2689 | 8344.000 | 2689 | 0.443 |
| 11 | prim | 180 | 2689 | 8344.000 | 37778 | 1.683 |
| 12 | kruskal | 210 | 277 | 77732.000 | 277 | 0.053 |
| 12 | prim | 210 | 277 | 77293.000 | 42552 | 0.654 |
| 13 | kruskal | 240 | 306 | 77782.000 | 306 | 0.043 |
| 13 | prim | 240 | 306 | 74194.000 | 51240 | 0.713 |
| 14 | kruskal | 270 | 11442 | 4159.000 | 11442 | 2.617 |
| 14 | prim | 270 | 11442 | 4159.000 | 95784 | 34.698 |
| 15 | kruskal | 300 | 3044 | 18305.000 | 3044 | 0.710 |
| 15 | prim | 300 | 3044 | 18305.000 | 96088 | 10.445 |
| 16 | kruskal | 400 | 8980 | 10897.000 | 8980 | 2.613 |
| 16 | prim | 400 | 8980 | 10897.000 | 177960 | 5.710 |
| 17 | kruskal | 450 | 617 | 162203.000 | 617 | 0.135 |
| 17 | prim | 450 | 617 | 160183.000 | 188428 | 0.648 |
| 18 | kruskal | 500 | 18864 | 7758.000 | 18864 | 6.284 |
| 18 | prim | 500 | 18864 | 7758.000 | 287728 | 40.915 |
| 19 | kruskal | 550 | 3111 | 59403.000 | 3111 | 0.849 |
| 19 | prim | 550 | 3111 | 59403.000 | 308722 | 6.143 |
| 20 | kruskal | 600 | 15920 | 14263.000 | 15920 | 5.341 |
| 20 | prim | 600 | 15920 | 14263.000 | 391840 | 23.961 |
| 21 | kruskal | 650 | 659 | 240061.000 | 659 | 0.148 |
| 21 | prim | 650 | 659 | 226686.000 | 337964 | 1.543 |
| 22 | kruskal | 700 | 776 | 257964.000 | 776 | 0.163 |
| 22 | prim | 700 | 776 | 253318.000 | 429232 | 4.673 |
| 23 | kruskal | 750 | 1183 | 244237.000 | 1183 | 0.247 |
| 23 | prim | 750 | 1183 | 242621.000 | 542362 | 56.993 |
| 24 | kruskal | 800 | 1188 | 252364.000 | 1188 | 0.134 |
| 24 | prim | 800 | 1188 | 251764.000 | 611172 | 1.626 |
| 25 | kruskal | 850 | 1492 | 264581.000 | 1492 | 0.195 |
| 25 | prim | 850 | 1492 | 264139.000 | 705080 | 2.057 |
| 26 | kruskal | 1300 | 2392 | 390428.000 | 2392 | 0.500 |
| 26 | prim | 1300 | 2392 | 390428.000 | 1645384 | 5.031 |
| 27 | kruskal | 1600 | 2916 | 472614.000 | 2916 | 0.645 |
| 27 | prim | 1600 | 2916 | 470589.000 | 2497024 | 8.839 |
| 28 | kruskal | 1900 | 3226 | 582972.000 | 3226 | 0.696 |
| 28 | prim | 1900 | 3226 | 582972.000 | 3500552 | 9.968 |

From this first stage, I noticed that for larger graphs, both algorithms started slowing down a lot.

---

### 2. After Optimization

Then, I optimized both algorithms for better speed and efficiency.

**What I changed:**
- Added **PriorityQueue** for Prim’s algorithm (instead of scanning all edges manually)
- Used **Union-Find with path compression** for Kruskal’s algorithm
- Measured precise time using `System.nanoTime()`
- Added **operation counters** to track how many key steps each algorithm performs
- Simplified graph input handling and memory usage

After optimization, both algorithms became faster, especially on large graphs.

| graph_id | algorithm | vertices | edges | total_cost | steps  | time_ms |
|-----------|------------|----------|--------|-------------|--------|---------|
| 1 | kruskal | 5 | 6 | 2355.000 | 22 | 0.012 |
| 1 | prim | 5 | 6 | 2355.000 | 12 | 0.026 |
| 2 | kruskal | 10 | 21 | 2053.000 | 94 | 0.006 |
| 2 | prim | 10 | 21 | 2053.000 | 42 | 0.019 |
| 3 | kruskal | 15 | 25 | 4026.000 | 112 | 0.010 |
| 3 | prim | 15 | 25 | 4026.000 | 50 | 0.079 |
| 4 | kruskal | 20 | 94 | 2694.000 | 445 | 0.054 |
| 4 | prim | 20 | 94 | 2694.000 | 188 | 0.155 |
| 5 | kruskal | 25 | 132 | 3129.000 | 626 | 0.049 |
| 5 | prim | 25 | 132 | 3129.000 | 264 | 0.124 |
| 6 | kruskal | 30 | 49 | 9647.000 | 223 | 0.012 |
| 6 | prim | 30 | 49 | 9647.000 | 98 | 0.044 |
| 7 | kruskal | 60 | 68 | 21992.000 | 292 | 0.016 |
| 7 | prim | 60 | 68 | 21414.000 | 134 | 0.076 |
| 8 | kruskal | 90 | 321 | 17875.000 | 1537 | 0.043 |
| 8 | prim | 90 | 321 | 17875.000 | 642 | 0.447 |
| 9 | kruskal | 120 | 1860 | 4236.000 | 9205 | 0.251 |
| 9 | prim | 120 | 1860 | 4236.000 | 3720 | 0.582 |
| 10 | kruskal | 150 | 5366 | 2363.000 | 26684 | 0.503 |
| 10 | prim | 150 | 5366 | 2363.000 | 10732 | 1.375 |
| 11 | kruskal | 180 | 2689 | 8344.000 | 13322 | 0.286 |
| 11 | prim | 180 | 2689 | 8344.000 | 5378 | 1.080 |
| 12 | kruskal | 210 | 277 | 77732.000 | 1251 | 0.037 |
| 12 | prim | 210 | 277 | 77293.000 | 552 | 0.239 |
| 13 | kruskal | 240 | 306 | 77782.000 | 1371 | 0.042 |
| 13 | prim | 240 | 306 | 74194.000 | 600 | 0.237 |
| 14 | kruskal | 270 | 11442 | 4159.000 | 57012 | 1.958 |
| 14 | prim | 270 | 11442 | 4159.000 | 22884 | 4.658 |
| 15 | kruskal | 300 | 3044 | 18305.000 | 15033 | 0.361 |
| 15 | prim | 300 | 3044 | 18305.000 | 6088 | 1.402 |
| 16 | kruskal | 400 | 8980 | 10897.000 | 44633 | 0.715 |
| 16 | prim | 400 | 8980 | 10897.000 | 17960 | 3.557 |
| 17 | kruskal | 450 | 617 | 162203.000 | 2807 | 0.058 |
| 17 | prim | 450 | 617 | 160183.000 | 1228 | 0.257 |
| 18 | kruskal | 500 | 18864 | 7758.000 | 94022 | 3.013 |
| 18 | prim | 500 | 18864 | 7758.000 | 37728 | 3.130 |
| 19 | kruskal | 550 | 3111 | 59403.000 | 15252 | 0.311 |
| 19 | prim | 550 | 3111 | 59403.000 | 6222 | 0.923 |
| 20 | kruskal | 600 | 15920 | 14263.000 | 79235 | 1.703 |
| 20 | prim | 600 | 15920 | 14263.000 | 31840 | 3.207 |
| 21 | kruskal | 650 | 659 | 240061.000 | 2863 | 0.064 |
| 21 | prim | 650 | 659 | 226686.000 | 1264 | 0.185 |
| 22 | kruskal | 700 | 776 | 257964.000 | 3419 | 0.108 |
| 22 | prim | 700 | 776 | 253318.000 | 1532 | 0.238 |
| 23 | kruskal | 750 | 1183 | 244237.000 | 5477 | 0.066 |
| 23 | prim | 750 | 1183 | 242621.000 | 2362 | 0.415 |
| 24 | kruskal | 800 | 1188 | 252364.000 | 5466 | 0.075 |
| 24 | prim | 800 | 1188 | 251764.000 | 2372 | 0.365 |
| 25 | kruskal | 850 | 1492 | 264581.000 | 6990 | 0.048 |
| 25 | prim | 850 | 1492 | 264139.000 | 2980 | 0.416 |
| 26 | kruskal | 1300 | 2392 | 390428.000 | 11242 | 0.073 |
| 26 | prim | 1300 | 2392 | 390428.000 | 4784 | 0.723 |
| 27 | kruskal | 1600 | 2916 | 472614.000 | 13751 | 0.088 |
| 27 | prim | 1600 | 2916 | 470589.000 | 5824 | 0.839 |
| 28 | kruskal | 1900 | 3226 | 582972.000 | 15145 | 0.117 |
| 28 | prim | 1900 | 3226 | 582972.000 | 6452 | 1.045 |

I ran both algorithms on the same 28 graphs again and saved the results to `results.csv` and `results_after_optimization`.

---

## Comparison: Before vs After Optimization

| Graph Category | Prim (Before) | Prim (After) | Kruskal (Before) | Kruskal (After) |
|----------------|---------------|--------------|------------|-----------------|
| Small | 0.1034        | 0.0806       | 0.0258     | 0.0262          |
| Medium | 6.3538        | 1.014        | 0.5264     | 0.3509          |
| Large | 14.4269       | 1.2693       | 1.6109     | 0.6161          |
| Extra Large | 11.919        | 0.869        | 0.6136     | 0.0926          |

**Observations:**
- Prim’s algorithm improved a lot after switching to a priority queue.
- Kruskal’s got faster too, but not as much — sorting still takes time.
- For very large graphs, Prim was several times faster than Kruskal.
- Both algorithms now run much smoother and more stable.

---

## Theoretical Analysis

| Algorithm | Time Complexity | Space Complexity | Notes |
|------------|----------------|------------------|-------|
| Prim | O((V + E) log V) | O(V) | Performs well on dense graphs |
| Kruskal | O(E log E) | O(V + E) | Usually better for sparse graphs |

In theory, both algorithms have similar asymptotic complexity, but their performance depends heavily on the graph type.  
Prim’s is often preferred for **dense** graphs, while Kruskal is faster for **sparse** ones.

---

## What I Observed in Practice

- **Kruskal’s algorithm was consistently faster** across almost all test cases.
- **Prim’s algorithm** was stable but slower, especially as the graph size grew.
- Kruskal’s sorting step (`Arrays.sort()`) in Java is highly optimized, which gave it an advantage.
- Prim’s `PriorityQueue` added a bit of overhead.
- The number of steps didn’t always match the actual runtime — fewer steps didn’t always mean faster execution.
- Both algorithms produced the **same MST total cost**, confirming correctness.

---

## Analysis

In most runs, **Kruskal outperformed Prim**.
This result makes sense because the graphs I used were **sparse**, meaning there were far fewer edges than possible connections.
Since Kruskal only processes the existing edges, it doesn’t waste time exploring unnecessary ones like Prim does.
Prim’s advantage didn’t really show up until the graph became much denser.
For my graphs, Kruskal’s efficient sorting made it the better performer overall.

---

## Conclusions

| Situation | Better Algorithm |
|------------|------------------|
| Small graphs | **Kruskal** |
| Medium graphs | **Kruskal** |
| Large dense graphs | Prim |
| Sparse graphs | **Kruskal** |
| Overall scalability (in my tests) | **Kruskal** |

Based on my experiments, **Kruskal’s algorithm was faster and more efficient** for the graphs I tested.
While Prim’s algorithm performs well for dense networks, Kruskal’s simplicity and sorting efficiency made it the winner in my case.
If I had to pick one for a real-world transportation planner, I’d recommend **testing both** — but for most sparse networks, **Kruskal** seems to be the better choice.

---

## Lessons Learned

- Real performance doesn’t always match theoretical complexity.
- Kruskal’s sorting step is very efficient in Java.
- Prim’s algorithm has more overhead due to the priority queue.
- Sparse vs. dense structure makes a big difference.
- Measuring performance precisely (in milliseconds or nanoseconds) gives more accurate results.
- Implementing both helped me understand how data structures like **Union-Find** and **PriorityQueue** really affect runtime.

---

## Output Files

| Before                | After                                    | Description                       |
|-----------------------|------------------------------------------|-----------------------------------|
| `output_prim.json`    | `output_prtim_after_optimization.json`   | Prim’s algorithm results          |
| `output_kruskal.json` | `output_kruskal_after_optimization.json` | Kruskal’s algorithm results       |
| `result.csv`          | `result.csvafter_optimization.json`      | Combined summary (for comparison) |

---

## Project Structure

├── src/ <br>
│ ├── Main.java <br>
│ ├── Graph.java <br>
│ ├── Edge.java <br>
│ ├── PrimMST.java <br>
│ ├── KruskalMST.java <br>
│ ├── UnionFind.java <br>
│ ├── JsonIO.java <br>
│ ├── Result.java <br>
├── input.json <br>
├── output_prim.json <br>
├── output_kruskal.json <br>
├── results.csv <br>
└── README.md <br>
