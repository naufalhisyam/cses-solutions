import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

// Weird Algorithms
// https://cses.fi/problemset/task/1068

public class WeirdAlgorithms {
    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        String input = InputUtils.readFileToString("solutions/introductory-problems/1068-weird-algorithms/input1.txt");
        InputUtils.setInputFromString(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);

        long N = Long.parseLong(br.readLine());
        pw.print(N);
        while (N != 1) {
            pw.print(" ");
            N = calculate(N);
        }

        pw.flush();
        br.close();
        pw.close();
    }

    private static long calculate(long n) {
        long result;
        if (n % 2 == 0) {
            result = n/2;
        } else {
            result = (n * 3) + 1;
        }
        pw.print(result);
        return result;
    }
}