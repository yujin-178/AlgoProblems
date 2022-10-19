package Level2.P86971_전력망을_둘로_나누기;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_1 {
    public static void main(String[] args) {
        int n = 4;
        int[][] wires = new int[][]{{1,2}, {2,3},{3,4}};
        System.out.println(solution(n, wires));
    }

    static boolean[][] chk;
    static boolean[] visited;
    static int N;

    static public int solution(int n, int[][] wires) {
        chk = new boolean[n + 1][n + 1];
        N = n;
        for (int i = 0; i < n - 1; i++) {
            chk[wires[i][0]][wires[i][1]] = true;
            chk[wires[i][1]][wires[i][0]] = true;
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i < n - 1; i++) {
            int s = wires[i][0];
            int e = wires[i][1];

            chk[s][e] = false;
            chk[e][s] = false;
            answer = Math.min(Math.abs(bfs(s) - bfs(e)), answer);
            chk[s][e] = true;
            chk[e][s] = true;

        }

        return answer;
    }

    static int bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited = new boolean[N + 1];
        visited[s] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int tmp = q.poll();
            for (int i = 1; i <= N; i++) {
                int next = -1;
                if (chk[tmp][i])
                    next = i;
                else
                    continue;
                if (visited[next])
                    continue;

                q.add(next);
                visited[next] = true;
                cnt++;
            }
        }

        return cnt;

    }
}
