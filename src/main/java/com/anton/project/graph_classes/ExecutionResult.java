package com.anton.project.graph_classes;

public class ExecutionResult {
    private String inputFile;
    private int inputSize;
    private double executionTime;

    public ExecutionResult(String inputFile, int inputSize, double executionTime) {
        this.inputFile = inputFile;
        this.inputSize = inputSize;
        this.executionTime = executionTime;
    }

    public String getInputFile() {
        return inputFile;
    }

    public int getInputSize() {
        return inputSize;
    }

    public double getExecutionTime() {
        return executionTime;
    }
}
