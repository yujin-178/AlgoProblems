package PriorityQueue.마지막으로_남은_숫자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++)
            pq.add(Integer.parseInt(st.nextToken()));

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a == b)
                continue;
            pq.add(a - b);
        }

        System.out.println(pq.isEmpty() ? "-1" : pq.peek());

    }
}
