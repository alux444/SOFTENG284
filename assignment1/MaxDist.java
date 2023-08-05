import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxDist {

  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);

    int t = in.nextInt();
    for (int i = 0; i < t; i++) {
      int n = in.nextInt();
      int k = in.nextInt();

      int a[] = new int[n];
      for (int j = 0; j < n; j++)
        a[j] = in.nextInt();
      out.println(solve(a, k));
    }

    out.close();
  }

  public static int solve(int a[], int k) {
    int lowerBound = 0;
    // maximum distance is last element - first element
    int upperBound = a[a.length - 1] - a[0];

    while (lowerBound <= upperBound) {
      int dist = (lowerBound + upperBound) / 2;

      // if the distance is valid we try search for a higher distance by increasing
      // lower bound
      if (searchForDist(a, dist, k)) {
        lowerBound = dist + 1;
      } else {
        // else the distance is invalid. look for a smaller dist by reducing upperbound.
        upperBound = dist - 1;
      }
    }

    return upperBound;
  }

  public static boolean searchForDist(int a[], int dist, int k) {
    int valuesWithDistance = 0;
    int previous = a[0];

    // compare each value with its previous to find dist
    for (int i = 0; i < a.length; i++) {
      // if the distance is valid, increment the amount of values with the distance
      // desired. set the previous value to this current new value.
      if (a[i] - previous >= dist) {
        valuesWithDistance++;
        previous = a[i];
      }
      // if there is k values, we know the dist is valid. return true.
      if (valuesWithDistance == k - 1) {
        return true;
      }
    }

    // if there is less than k values with dist k, return false
    return false;
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
