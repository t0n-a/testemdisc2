package com.anton.project;

import com.anton.project.algorithm.*;
import com.anton.project.file_classes.FileReader;
import com.anton.project.file_classes.FileWriter;
import com.anton.project.graph_classes.Edge;
import com.anton.project.GraphvizExample;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the path to the CSV file containing the graph data:");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String filePath;
        try {
            filePath = consoleReader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading from console: " + e.getMessage());
            return;
        }

        List<Edge> edges = FileReader.readGraphFromCSV(filePath);
        if (edges == null || edges.isEmpty()) {
            System.out.println("No edges read from the file or file not found.");
            return;
        }

        IMap verticeMap = new IMap(edges.size()); // Assuming the map can hold all vertices
        int verticeIndex = 0;

        for (Edge edge : edges) {
            if (!verticeMap.contains(edge.getPointA())) {
                verticeMap.put(edge.getPointA(), verticeIndex++);
            }
            if (!verticeMap.contains(edge.getPointB())) {
                verticeMap.put(edge.getPointB(), verticeIndex++);
            }
        }

        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
        KruskalAlgorithm.MSTResult mstResult = kruskalAlgorithm.kruskalMST(edges, verticeMap.size(), verticeMap);

        String outputDir = "output_folder";
        new File(outputDir).mkdirs();  // Create the directory if it does not exist

        // Output the CSV file
        String csvOutputPath = outputDir + "/mst_output.csv";
        FileWriter.writeMSTToCSV(mstResult.edges, csvOutputPath, mstResult.totalCost);

        // Visualize the graphs
        String graphOutputPath = outputDir + "/full_graph.png";
        String mstOutputPath = outputDir + "/mst_graph.png";
        GraphvizExample.createAndShowGraphvizGraph(edges, mstResult, graphOutputPath);
        GraphvizExample.createAndShowGraphvizGraph(mstResult.edges, mstResult, mstOutputPath);

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mstResult.edges) {
            System.out.println(edge.getPointA() + " - " + edge.getPointB() + " : " + edge.getDistance());
        }

        System.out.println("Total Cost of MST: " + mstResult.totalCost);

        System.out.println("Outputs are saved in: " + outputDir);
    }
}







