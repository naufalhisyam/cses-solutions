import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Missing Number
// https://cses.fi/problemset/task/1083/

public class MissingNumber {
    static PrintWriter pw;

    public static void main(String[] args) throws IOException {
        String input = InputUtils.readFileToString("solutions/introductory-problems/1083-missing-number/input1.txt");
        InputUtils.setInputFromString(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());

        int[] arr = new int[N+1];

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            int j = Integer.parseInt(st2.nextToken());

            arr[j] = 1;
        }

        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0) {
                pw.println(i);
                break;
            }
        }

        pw.flush();
        br.close();
        pw.close();
    }
}