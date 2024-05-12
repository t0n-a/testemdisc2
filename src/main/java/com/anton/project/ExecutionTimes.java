package com.anton.project;

import com.anton.project.algorithm.IMap;
import com.anton.project.algorithm.KruskalAlgorithm;
import com.anton.project.file_classes.FileReader;
import com.anton.project.graph_classes.Edge;
import com.anton.project.graph_classes.ExecutionResult;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ExecutionTimes {

    String outputDir = "output_folder";

    public List<String> getFileNames(String baseFileName, int numberOfFiles, String baseDirectory) {
        List<String> fileNames = new ArrayList<>();
        for (int i = 1; i <= numberOfFiles; i++) {
            String fileName = baseDirectory + "/" + baseFileName + "_" + i + ".csv";
            fileNames.add(fileName);
        }
        return fileNames;
    }

    // Method to run the Kruskal Algorithm
    public KruskalAlgorithm.MSTResult runAlgorithm(List<Edge> edges) {
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
        return kruskalAlgorithm.kruskalMST(edges, verticeMap.size(), verticeMap);
    }

    public void getExecutionTimes (List<String> inputFiles) {
        List<ExecutionResult> results = new ArrayList<>();
        try (
                PrintWriter writer = new PrintWriter(new FileWriter(outputDir + "/execution_times.csv"))) {
            writer.println("Input Size,Execution Time (s)");
            for (String inputFile : inputFiles) {
                List<Edge> edges = FileReader.readGraphFromCSV(inputFile);
                long startTime = System.nanoTime();
                this.runAlgorithm(edges);
                long endTime = System.nanoTime();
                double executionTime = (endTime - startTime) / 1_000_000_000.0;
                int inputSize = edges.size();
                results.add(new ExecutionResult(inputFile, inputSize, executionTime));
                writer.println(inputSize + "," + executionTime);
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        plotExecutionTimes(results);
        System.out.println("Execution times have been plotted. Check the output folder for the results.");
    }

    public void plotExecutionTimes(List<ExecutionResult> results) {
        try {
            String csvFilename = outputDir + "/execution_times.csv";
            String outputImageFile = outputDir + "/execution_times.png";

            ProcessBuilder processBuilder = new ProcessBuilder("gnuplot");
            Process process = processBuilder.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write("set terminal png\n");
            writer.write("set output '" + outputImageFile + "'\n");
            writer.write("set xlabel 'File Size'\n"); // Use File Size as x-axis label
            writer.write("set ylabel 'Execution Time (s)'\n");
            writer.write("set title 'Execution Time vs. File Size'\n");
            writer.write("set datafile separator ','\n");
            writer.write("plot '" + csvFilename + "' using 1:2 with points title 'Execution Time', '' using 1:2 smooth bezier notitle\n");
            writer.write("exit\n");
            writer.flush();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IOException("Gnuplot process exited with non-zero status: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
