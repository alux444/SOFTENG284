import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Middle {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int m = in.nextInt();

    // a heap ordered by max
    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    // a heap ordered by min
    PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

    for (int i = 0; i < m; i++) {
      int val = in.nextInt();
      if (val == 0) {
        // middle value will always be the max of the left heap
        out.println(leftMaxHeap.peek());
      } else {
        int x = in.nextInt();
        // if heap is empty, or less than the max of the left, place in the left
        if (leftMaxHeap.isEmpty() || x <= leftMaxHeap.peek()) {
          leftMaxHeap.offer(x);
        } else {
          // else we know it belongs in the right heap.
          rightMinHeap.offer(x);
        }

        // if the left is greater than the right size + 1, move the max value of the
        // left to the right
        if (leftMaxHeap.size() > rightMinHeap.size() + 1) {
          rightMinHeap.offer(leftMaxHeap.poll());
        } else if (rightMinHeap.size() > leftMaxHeap.size()) {
          // otherwise if the right side is larger than the left, move the min value of
          // the right to the left.
          leftMaxHeap.offer(rightMinHeap.poll());
        }
      }
    }
    out.close();
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
