package com.anton.project.file_classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanner {
    private Scanner scanner;

    public FileScanner(String filename) throws FileNotFoundException {
        this.scanner = new Scanner(new File(filename));
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public String next() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }

    public List<String> scanCsvFiles(String directory) {
        List<String> fileList = new ArrayList<>();
        File dir = new File(directory);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".csv"));
        if (files != null) {
            for (File file : files) {
                fileList.add(file.getAbsolutePath());
            }
        }
        return fileList;
    }
}

