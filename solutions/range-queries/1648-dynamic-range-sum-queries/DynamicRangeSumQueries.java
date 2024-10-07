import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Dynamic Range Sum Queries
// https://cses.fi/problemset/task/1648

public class DynamicRangeSumQueries {
    static PrintWriter pw;

    static int MAX_N = (int) 2e5;
    static int CHUNK_SIZE;

    static long N; //numOfValues
    static long numOfQueries;

    static long[] arrOfValues = new long[MAX_N+1];
    static long[] arrayOfChunksSum;


    public static void main(String[] args) throws IOException {
        String input = InputUtils.readFileToString("solutions/range-queries/1648-dynamic-range-sum-queries/input1.txt");
        InputUtils.setInputFromString(input);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);

        StringTokenizer inputTokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(inputTokenizer.nextToken());
        numOfQueries = Integer.parseInt(inputTokenizer.nextToken());

        CHUNK_SIZE = (int) Math.sqrt(N);
        arrayOfChunksSum = new long[(MAX_N/CHUNK_SIZE)+1];
//        pw.println("N=" + N + " q=" + numOfQueries + " chunk size=" + CHUNK_SIZE);

        StringTokenizer arrayTokenizer = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            long val = Integer.parseInt(arrayTokenizer.nextToken(" "));

            arrOfValues[i] = val;
            arrayOfChunksSum[getChunkIndex(i)] += val;
        }

        for (int i = 0; i < numOfQueries; i++) {
            StringTokenizer queryTokenizer = new StringTokenizer(br.readLine());
//            pw.println("queryNum: " + i);
            if (Integer.parseInt(queryTokenizer.nextToken()) == 1) {
                int k = Integer.parseInt(queryTokenizer.nextToken())-1;
                int u = Integer.parseInt(queryTokenizer.nextToken());
//                pw.println(k + " " + u);

                updateArray(k, u);
            }
            else {
                int a = Integer.parseInt(queryTokenizer.nextToken())-1;
                int b = Integer.parseInt(queryTokenizer.nextToken())-1;
//                pw.println("a=" + a + " b=" + b + " diff=" + (b-a));

                pw.println(getSum(a, b));
            }
        }

        pw.flush();
        br.close();
        pw.close();
    }

    private static long getSum(int a, int b) {
        if (a == b) {
            return arrOfValues[a];
        }

        long sum = 0;

        int ls = a;
        int le = getChunkEndIndex(getChunkIndex(a));
//        pw.println("ls" + ls + " le" + le);

        int rs = getChunkStartIndex(getChunkIndex(b));
        int re = b;
//        pw.println("rs" + rs + " re" + re);

        if (le > MAX_N) {
            return sumArray(arrOfValues, a, b+1, false);
        }

        if (le > rs) {
            return sumArray(arrOfValues, a, b+1, false);
        }

        if (ls == rs && le == re) {
            sum += sumArray(arrOfValues, rs, re+1, false);
        } else {
            sum += sumArray(arrOfValues, ls, le+1, false);
            sum += sumArray(arrOfValues, rs, re+1, false);

            if ((rs-le) != 1) {
                int cs = getChunkIndex(le+1);
                int ce = getChunkIndex(rs-1);
//                pw.println("cs" + cs + " ce" + ce);
                sum += sumArray(arrayOfChunksSum, cs , ce+1, true);
            }
        }

//        pw.println(sum);
        return sum;
    }

    private static void updateArray(int index, int value) {
        long diff = value - arrOfValues[index];
        arrOfValues[index] = value;
        arrayOfChunksSum[getChunkIndex(index)] += diff;
    }

    private static long sumArray(long[] arr, int start, int end, boolean isChunkSumArray) {
        if (isChunkSumArray) {
            if (start == end) {
                return arrayOfChunksSum[start];
            }
        }

        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += arr[i];
        }
//        pw.println("sum"+sum);
        return sum;
    }

    private static int getChunkStartIndex(int chunkIndex) {
        return chunkIndex * CHUNK_SIZE;
    }

    private static int getChunkEndIndex(int chunkIndex) {
        return getChunkStartIndex(chunkIndex+1) - 1;
    }

    private static int getChunkIndex(int index) {
        return index / CHUNK_SIZE;
    }
}