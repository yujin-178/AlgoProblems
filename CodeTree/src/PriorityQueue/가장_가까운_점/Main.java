package PriorityQueue.가장_가까운_점;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int m = 0; m < M; m++) {
            Pos tmp = pq.poll();
            tmp.x += 2;
            tmp.y += 2;
            pq.add(tmp);
        }

        System.out.println(pq.peek());
    }

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            return Math.abs(this.x) + Math.abs(this.y)==  Math.abs(o.x) + Math.abs(o.y)? this.x != o.x ? Integer.compare(this.x, o.x) : Integer.compare(this.y, o.y): Integer.compare(Math.abs(this.x) + Math.abs(this.y), Math.abs(o.x) + Math.abs(o.y));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            return sb.append(this.x).append(" ").append(this.y).append("\n").toString();
        }
    }
}
