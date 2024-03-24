import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cycle {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    while (true) {
      String in = reader.readLine();
      if (in.equals("0") || in.isEmpty()) {
        break;
      }
      int order = Integer.parseInt(in);
      Graph graph = new Graph(order);

      for (int i = 0; i < order; i++) {
        String input = reader.readLine();
        if (!input.isEmpty()) {
          String[] numbers = input.split(" ");
          for (String number : numbers) {
            int val = Integer.parseInt(number);
            graph.addEdge(i, val);
          }
        }
      }

      if (graph.hasCycle()) {
        output.append("1\n");
      } else {
        output.append("0\n");
      }
    }

    System.out.print(output.toString());
  }
}

class Graph {
  private int order;
  private List<Integer>[] adjList;

  public Graph(int order) {
    this.order = order;
    this.adjList = new ArrayList[order];
    for (int i = 0; i < order; i++) {
      adjList[i] = new ArrayList<>();
    }
  }

  public void addEdge(int level, int dest) {
    adjList[level].add(dest);
  }

  public boolean hasCycle() {
    boolean[] visited = new boolean[order];
    boolean[] rec = new boolean[order];

    for (int i = 0; i < order; i++) {
      if (!visited[i] && hasCycleUtil(i, visited, rec)) {
        return true;
      }
    }

    return false;
  }

  private boolean hasCycleUtil(int i, boolean[] visited, boolean[] rec) {
    if (rec[i])
      return true;

    if (visited[i])
      return false;

    visited[i] = true;
    rec[i] = true;

    for (int c : adjList[i]) {
      if (hasCycleUtil(c, visited, rec))
        return true;
    }

    rec[i] = false;

    return false;
  }
}
