package com.anton.project.graph_classes;

public class Edge {
    String pointA;
    String pointB;
    int distance;

    public Edge(String pointA, String pointB, int distance) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.distance = distance;
    }

    public String getPointA() {
        return pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public int getDistance() {
        return distance;
    }
}
