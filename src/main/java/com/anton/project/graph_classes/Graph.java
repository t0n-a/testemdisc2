package com.anton.project.graph_classes;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertice> vertices;
    private List<Edge> edges;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void addEdge(String pointA, String pointB, int distance) {
        Edge edge = new Edge(pointA, pointB, distance);
        edges.add(edge);
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
