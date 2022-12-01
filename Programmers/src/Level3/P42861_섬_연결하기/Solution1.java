package Level3.P42861_섬_연결하기;

import java.util.Arrays;

public class Solution1 {

    static Road[] roads;
    static int[] parents;
    static int len;

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

        int n = 4;
        int[][] costs = new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1}};

        System.out.println(solution1.solution(n, costs));
    }

    public int solution(int n, int[][] costs) {
        len = costs.length;
        roads = new Road[len];
        make(n);
        for (int i = 0; i < len; i++) {
            roads[i] = new Road(costs[i][0], costs[i][1], costs[i][2]);
        }

        Arrays.sort(roads);

        int cnt = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            if (union(roads[i].a, roads[i].b)) {
                cnt++;
                ans += roads[i].l;
            }

            if (cnt == n - 1) break;
        }

        return ans;
    }

    static void make(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        if (a == parents[a]) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }

    static class Road implements Comparable<Road> {
        int a, b, l;

        public Road(int a, int b, int l) {
            this.a = a < b ? a : b;
            this.b = b > a ? b : a;
            this.l = l;
        }

        public int compareTo(Road o) {
            return Integer.compare(this.l, o.l);
        }
    }

}
