package PriorityQueue.앞에서부터_삭제하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_메모리초과 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> pq = null;

        int N = Integer.parseInt(st.nextToken());
        int[] array = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            array[n] = Integer.parseInt(st.nextToken());
        }
        double max = 0;
        for (int k = 1; k < N - 1; k++) {
            pq = new PriorityQueue<>();
            double sum = 0;
            for (int n = k; n < N; n++) {
                pq.add(array[n]);
                sum += array[n];
            }
            double calc = (sum - pq.peek()) / (double) (N - k - 1);
            if (max < calc)
                max = calc;
        }
        System.out.printf("%.2f", max);
    }
}
