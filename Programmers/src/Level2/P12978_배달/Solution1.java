package Level2.P12978_배달;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int N = 5;
        int[][] road = new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}};
        int K = 3;
        System.out.println(s.solution(N, road, K));
    }

    static int N, K;
    static LinkedList<Node>[] edges;
    static boolean[] chks;
    static int[] minLens;

    public int solution(int n, int[][] road, int k) {
        N = n;
        K = k;
        edges = new LinkedList[N + 1];
        chks = new boolean[N + 1];
        minLens = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new LinkedList<>();
            minLens[i] = Integer.MAX_VALUE;
        }
        int len = road.length;
        for(int i = 0; i < len; i++){
            int s = road[i][0];
            int d = road[i][1];
            int l = road[i][2];
            edges[s].add(new Node(d, l));
            edges[d].add(new Node(s, l));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        minLens[1] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.i;
            int nowLen = now.w;

            if (chks[nowIdx]) continue;

            chks[nowIdx] = true;
            for (Node next : edges[nowIdx]) {
                if (!chks[next.i] && minLens[next.i] > next.w + minLens[nowIdx]) {
                    minLens[next.i] = next.w + nowLen;
                    pq.add(new Node(next.i, minLens[next.i]));
                }
            }

        }
        int ans = 0;
        for (int i = 1; i <= N; i++) {
//            System.out.println(i+"번쨰 : " + minLens[i]);
            if (minLens[i] <= K) ans++;
        }

        return ans;
    }

    static class Node implements Comparable<Node> {
        int i, w;

        public Node(int i, int w) {
            this.i = i;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }


}
