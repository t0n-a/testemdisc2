package com.anton.project.file_classes;

import com.anton.project.graph_classes.Edge;
import java.io.PrintWriter;
import java.util.List;

public class FileWriter {

    public static void writeMSTToCSV(List<Edge> mst, String filePath, int totalCost) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Edge edge : mst) {
                writer.printf("%s,%s,%d\n", edge.getPointA(), edge.getPointB(), edge.getDistance());
            }
            writer.printf("Total MST Cost,%d\n", totalCost);
        } catch (Exception e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
