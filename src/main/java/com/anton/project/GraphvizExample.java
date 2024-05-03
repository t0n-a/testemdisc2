package com.anton.project;

import com.anton.project.algorithm.KruskalAlgorithm.MSTResult;
import com.anton.project.graph_classes.Edge;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GraphvizExample {

    public static void createAndShowGraphvizGraph(List<Edge> allEdges, MSTResult mstResult, String fileName) {
        StringBuilder dotFormat = new StringBuilder("graph G {\n");
        dotFormat.append(String.format("label=\"Graph Order: %d, Graph Dimension: %d, MST Cost: %d\";\n",
                mstResult.edges.size() + 1, allEdges.size(), mstResult.totalCost));

        for (Edge edge : allEdges) {
            String style = mstResult.edges.contains(edge) ? "color=blue, penwidth=2.0" : "color=gray";
            dotFormat.append(String.format("\"%s\" -- \"%s\" [label=\"%d\", %s];\n",
                    edge.getPointA(), edge.getPointB(), edge.getDistance(), style));
        }
        dotFormat.append("}");

        try {
            MutableGraph graph = new Parser().read(dotFormat.toString());
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new File(fileName));
            System.out.println("Graph image created as " + fileName);
        } catch (IOException e) {
            System.err.println("Error generating graph: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

