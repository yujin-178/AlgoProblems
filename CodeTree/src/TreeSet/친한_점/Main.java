package TreeSet.친한_점;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        TreeSet<Pos> set = new TreeSet<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            set.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            Pos find = set.ceiling(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            sb.append(find == null ? "-1 -1" : find).append("\n");
        }
        System.out.println(sb);
    }

    static class Pos implements Comparable<Pos> {
        int y, x;

        public Pos(int x, int y) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Pos o) {
            return this.x == o.x ? Integer.compare(this.y, o.y) : Integer.compare(this.x, o.x);
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
