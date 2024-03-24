import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bipartite {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder output = new StringBuilder();

    while (true) {
      String in = reader.readLine();
      if (in.equals("0") || in.isEmpty()) {
        break;
      }
      int order = Integer.parseInt(in);
      ArrayList<Integer>[] adjList = new ArrayList[order];

      for (int i = 0; i < order; i++) {
        adjList[i] = new ArrayList<>();
      }

      for (int i = 0; i < order; i++) {
        String input = reader.readLine();

        if (!input.isEmpty()) {
          StringTokenizer tokenizer = new StringTokenizer(input);
          while (tokenizer.hasMoreTokens()) {
            int val = Integer.parseInt(tokenizer.nextToken());
            adjList[i].add(val);
          }
        }
      }

      if (isBipartite(order, adjList)) {
        output.append("1\n");
      } else {
        output.append("0\n");
      }
    }

    System.out.print(output.toString());
  }

  public static boolean isBipartite(int order, ArrayList<Integer>[] adjList) {
    int[] colour = new int[order];
    Arrays.fill(colour, -1);

    for (int i = 0; i < order; i++) {
      if (colour[i] == -1 && !bipartiteBFS(i, colour, adjList)) {
        return false;
      }
    }

    return true;
  }

  private static boolean bipartiteBFS(int src, int[] colour, ArrayList<Integer>[] adjList) {
    colour[src] = 1;

    Queue<Integer> q = new LinkedList<>();
    q.add(src);

    while (!q.isEmpty()) {
      int u = q.poll();

      for (int v : adjList[u]) {
        if (colour[v] == -1) {  
          colour[v] = 1 - colour[u];
          q.add(v);
        }

        else if (colour[v] == colour[u]) {
          return false;
        }
      }

    }
    return true;
  }
}
