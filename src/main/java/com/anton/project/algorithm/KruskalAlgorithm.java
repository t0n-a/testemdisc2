package com.anton.project.algorithm;

import com.anton.project.graph_classes.Edge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {

    public static class MSTResult {
        public List<Edge> edges;
        public int totalCost;

        public MSTResult(List<Edge> edges, int totalCost) {
            this.edges = edges;
            this.totalCost = totalCost;
        }
    }

    public MSTResult kruskalMST(List<Edge> edges, int numberOfVertices, IMap vertexMap) {
        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        // Sort edges by distance
        edges.sort(Comparator.comparingInt(Edge::getDistance));

        UnionFind uf = new UnionFind(numberOfVertices);

        for (Edge edge : edges) {
            int rootA = uf.find(vertexMap.get(edge.getPointA()));
            int rootB = uf.find(vertexMap.get(edge.getPointB()));

            if (rootA != rootB) {
                mst.add(edge);
                totalCost += edge.getDistance();
                uf.union(rootA, rootB);
                if (mst.size() == numberOfVertices - 1) break;
            }
        }
        return new MSTResult(mst, totalCost);
    }

    static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int item) {
            if (parent[item] != item) {
                parent[item] = find(parent[item]);
            }
            return parent[item];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
}


