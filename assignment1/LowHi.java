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

