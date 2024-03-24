import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Degree {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();

        while (true) {
            String in = reader.readLine();
            if (in.equals("0") || in.isEmpty()) {
                break;
            }

            int order = Integer.parseInt(in);
            List<Integer>[] adjList = new ArrayList[order];

            for (int i = 0; i < order; i++) {
                adjList[i] = new ArrayList<>();
                String input = reader.readLine();
                if (!input.isEmpty()) {
                    String[] numbers = input.split(" ");
                    for (String number : numbers) {
                        int val = Integer.parseInt(number);
                        adjList[i].add(val);
                    }
                }
            }

            int shortestDist = -1;

            for (int i = 0; i < order; i++) {
                Queue<Integer> q = new LinkedList<>();
                int[] colour = new int[order];
                int[] d = new int[order];
                int[] p = new int[order];
                for (int x = 0; x < order; x++) {
                    colour[i] = 0;
                    d[i] = 0;
                    p[i] = -1;
                }

                colour[i] = 1;
                d[i] = 0;
                q.add(i);
                while (!q.isEmpty()) {
                    int current = q.poll();

                    for (int j = 0; j < adjList[current].size(); j++) {
                        int n = adjList[current].get(j);

                        if (colour[n] == 0) {
                            colour[n] = 1;
                            p[n] = i;
                            d[n] = d[current] + 1;
                            q.add(n);
                            shortestDist = Math.max(shortestDist, d[n]);
                        }
                    }

                    colour[current] = 2;
                }
            }

            output.append(shortestDist + "\n");
        }
        System.out.println(output.toString());
    }
}
