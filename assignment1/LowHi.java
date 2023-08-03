import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;

public class LowHi {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int n = in.nextInt();
    int a[] = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int m = in.nextInt();
    for (int i = 0; i < m; i++) {
      int low = in.nextInt();
      int hi = in.nextInt();
      out.println(count_between(a, low, hi));
    }
    out.close();
  }

  public static int count_between(int a[], int lo, int hi) {
    int left = 0;
    int right = a.length - 1;
    int lowerBound = -1;
    int upperBound = -1;

    while (left <= right) {
      int mid = (left + right) / 2;

      // if mid is greater or equal to low, shift right bound to before mid. set lower
      // bound as mid.
      if (a[mid] >= lo) {
        right = mid - 1;
        lowerBound = mid;
      } else {
        // else mid is less than low. therefore move the left pointer to after mid.
        left = mid + 1;
      }
    }

    left = 0;
    right = a.length - 1;

    while (left <= right) {
      int mid = (left + right) / 2;

      // if mid is less than or equal to high, we know that we must move the left
      // pointer to one after mid
      if (a[mid] <= hi) {
        left = mid + 1;
        upperBound = mid;
      } else {
        // else mid is greater than high. move the right pointer to one before mid
        right = mid - 1;
      }
    }

    // if any bounds are invalid, return a 0
    if (lowerBound == -1 || upperBound == -1 || lowerBound > upperBound) {
      return 0;
    }

    return upperBound - lowerBound + 1;
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
