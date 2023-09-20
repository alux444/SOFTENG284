import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Maze {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int n = in.nextInt();
    int m = in.nextInt();

    UnionFind union = new UnionFind(n);

    for (int i = 0; i < m; i++) {
      int cellA = in.nextInt();
      int cellB = in.nextInt();
      union.verifyWall(cellA, cellB);
    }

    // print out result
    StringBuilder horizontal = new StringBuilder();
    horizontal.append("+");

    for (int i = 0; i < n; i++) {
      horizontal.append("-");
      horizontal.append("+");
    }

    System.out.println(horizontal.toString());

    for (int j = 0; j < n; j++) {
      StringBuilder vert = new StringBuilder();
      vert.append("|");

      for (int x = 0; x < n; x++) {
        int cellA = x + (j * n);
        int cellB = x + 1 + (j * n);

        if (union.containsPair(cellA, cellB)) {
          vert.append("  ");
        } else {
          vert.append(" |");
        }

      }

      System.out.println(vert);

      StringBuilder horiz = new StringBuilder();
      horiz.append("+");

      for (int y = 0; y < n; y++) {
        int cellA = y + (j * n);
        int cellB = y + n + (j * n);

        if (j < n - 1 && union.containsPair(cellA, cellB)) {
          horiz.append(" +");
        } else {
          horiz.append("-+");
        }
      }

      System.out.println(horiz);
    }

    out.close();
  }

  static class UnionFind {
    int size;
    int[] parent;
    int[] height;
    HashSet<Integer> brokenWalls;

    public UnionFind(int n) {
      this.size = n * n;
      this.brokenWalls = new HashSet<>();
      parent = new int[size];
      height = new int[size];

      for (int i = 0; i < size; i++) {
        parent[i] = i;
        height[i] = 0;
      }
    }

    public void verifyWall(int cellA, int cellB) {
      int rootA = find(cellA);
      int rootB = find(cellB);

      if (rootA != rootB) {
        if (height[rootA] < height[rootB]) {
          parent[rootA] = rootB;
        } else if (height[rootA] > height[rootB]) {
          parent[rootB] = rootA;
        } else {
          parent[rootB] = rootA;
          height[rootA]++;
        }

        int pairHashCode = computePairHashCode(cellA, cellB);
        brokenWalls.add(pairHashCode);
      }
    }

    public int find(int cell) {
      if (parent[cell] != cell) {
        parent[cell] = find(parent[cell]);
      }
      return parent[cell];
    }

    public boolean containsPair(int cellA, int cellB) {
      int pairHashCode = computePairHashCode(cellA, cellB);
      return brokenWalls.contains(pairHashCode);
    }

    private int computePairHashCode(int cellA, int cellB) {
      return cellA > cellB ? cellA * size + cellB : cellB * size + cellA;
    }
  }

  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");

    String next() {
      while (!st.hasMoreTokens())
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
        }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
