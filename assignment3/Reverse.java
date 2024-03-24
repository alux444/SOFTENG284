import java.util.ArrayList;
import java.util.Scanner;

public class Reverse {

  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    ArrayList<ArrayList<Integer>[]> results = new ArrayList<>();

    while (scan.hasNextLine()) {
      String in = scan.nextLine();
      if (in.equals("0") || in.isEmpty()) {
        break;
      }
      int order = Integer.parseInt(in);
      ArrayList<Integer>[] reversed = new ArrayList[order];

      for (int i = 0; i < order; i++) {
        reversed[i] = new ArrayList<Integer>();
      }

      for (int i = 0; i < order; i++) {
        String input = scan.nextLine();
        if (!input.isEmpty()) {
          String[] numbers = input.split(" ");
          for (int j = 0; j < numbers.length; j++) {
            int val = Integer.parseInt(numbers[j]);
            reversed[val].add(i);
          }
        }
      }
      results.add(reversed);
    }

    StringBuilder output = new StringBuilder();

    for (ArrayList<Integer>[] res : results) {
      output.append(res.length).append("\n");

      for (int i = 0; i < res.length; i++) {
        ArrayList<Integer> list = res[i];
        for (int j = 0; j < list.size(); j++) {
          output.append(list.get(j)).append(" ");
        }

        if (i != res.length - 1) {
          output.append("\n");
        }
      }
      output.append("\n");
    }

    output.append("0\n");
    System.out.print(output.toString());

    scan.close();
  }

}
