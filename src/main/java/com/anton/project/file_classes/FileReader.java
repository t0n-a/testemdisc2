package com.anton.project.file_classes;

import com.anton.project.graph_classes.Edge;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<Edge> readGraphFromCSV(String filePath) {
        List<Edge> edges = new ArrayList<>();  // Use a List to manage edges dynamically
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String pointA = parts[0].trim();
                    String pointB = parts[1].trim();
                    int distance;
                    try {
                        distance = Integer.parseInt(parts[2].trim());
                        edges.add(new Edge(pointA, pointB, distance));
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing distance value: " + parts[2]);
                    }
                } else {
                    System.out.println("Incorrect format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return edges;
    }
}



