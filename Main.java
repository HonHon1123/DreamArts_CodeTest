import java.util.*;
import java.io.*;

public class Main {
    static Map<Integer, List<Edge>> graph = new HashMap<>();
    static List<Integer> longestPath = new ArrayList<>();
    static double maxDist = 0.0;
    static final int MAX_ID = 20001;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",");
            if (parts.length != 3) continue;

            int from = Integer.parseInt(parts[0].trim());
            int to = Integer.parseInt(parts[1].trim());
            double distance = Double.parseDouble(parts[2].trim());

            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, distance));
        }

        scanner.close();

        for (int start : graph.keySet()) {
            boolean[] visited = new boolean[MAX_ID];
            dfs(start, visited, new ArrayList<>(), 0.0);
        }

        for (int node : longestPath) {
            System.out.println(node);
        }
    }

    static void dfs(int current, boolean[] visited, List<Integer> path, double distance) {
        visited[current] = true;
        path.add(current);

        if (distance > maxDist) {
            maxDist = distance;
            longestPath = new ArrayList<>(path);
        }

        if (graph.containsKey(current)) {
            for (Edge edge : graph.get(current)) {
                if (!visited[edge.to]) {
                    dfs(edge.to, visited, path, distance + edge.weight);
                }
            }
        }

        path.remove(path.size() - 1);
        visited[current] = false;
    }

    static class Edge {
        int to;
        double weight;

        Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
