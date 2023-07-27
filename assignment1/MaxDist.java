import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
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
      for (int j = 0; j < n; j++) a[j] = in.nextInt();
      out.println(solve(a, k));
    }

    out.close();
	}

  public static int solve(int a[], int k) {
    // WRITE YOUR CODE HERE
  }

  static class FastScanner {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st=new StringTokenizer("");

      String next() {
        while (!st.hasMoreTokens())
          try { 
            st=new StringTokenizer(br.readLine());				              
          } catch (IOException e) {}
        return st.nextToken();
      }
      
      int nextInt() {
        return Integer.parseInt(next());
      }
  }
}

