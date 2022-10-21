package Level3.P92345_사라지는_발판;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution2 {
    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static ArrayList<ResultInfo>[] resultInfo;
    static Player p1, p2;
    static PriorityQueue<ResultInfo> pq;

    public static void main(String[] args) {
//        답 4
//        int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
//        int[] aloc = {1, 0};
//        int[] bloc = {1, 2};

//        답 5
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc = {1, 0};
        int[] bloc = {1, 2};

//        답 4
//        int[][] board = {{1, 1, 1, 1, 1}};
//        int[] aloc = {0, 0};
//        int[] bloc = {0, 4};
        System.out.println(solution(board, aloc, bloc));
    }

    static public int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        p1 = new Player(aloc[0], aloc[1]);
        p2 = new Player(bloc[0], bloc[1]);
        map = board;
        pq = new PriorityQueue<>();
        resultInfo = new ArrayList[2];
        resultInfo[0] = new ArrayList<>();
        resultInfo[1] = new ArrayList<>();

        playerMove(0);
        System.out.println("A가 승리한 횟수 : " + resultInfo[0].size());
        System.out.println("B가 승리한 횟수 : " + resultInfo[1].size());
        int cnt = pq.peek().cnt;
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        return cnt;
    }

    static void viewTest() {
        System.out.println();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (p1.r == r && p1.c == c) {
                    System.out.print("A ");
                } else if (p2.r == r && p2.c == c) {
                    System.out.print("B ");
                } else {
                    System.out.print(map[r][c] + " ");
                }
            }
            System.out.println();
        }
    }

    static void playerMove(int dep) {
        int idx = dep % 2;
        Player now = idx == 0 ? p1 : p2;
        int r = now.r;
        int c = now.c;

        if (map[r][c] == 0) {
            resultInfo[1].add(new ResultInfo(idx, dep));
            pq.add(new ResultInfo(idx, dep));
        }

        boolean flag = true;
        map[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (chkOut(nr, nc)) continue;
            flag = false;
            p1.r = nr;
            p1.c = nc;

            playerMove(dep + 1);
        }
        map[r][c] = 1;
        p1.r = r;
        p1.c = c;
        if (flag) {
            resultInfo[1].add(new ResultInfo(idx, dep));
            pq.add(new ResultInfo(idx, dep));
        }
    }


    static boolean chkOut(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C || map[r][c] == 0;
    }

    static class Player {
        int r, c;

        public Player(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class ResultInfo implements Comparable<ResultInfo> {
        int winner; // a가 0, b가 1
        int cnt;

        public ResultInfo(int winner, int cnt) {
            this.winner = winner;
            this.cnt = cnt;

        }


        @Override
        public int compareTo(ResultInfo o) {
//            return Integer.compare(this.winLog.length() + this.loseLog.length(), o.winLog.length() + o.loseLog.length());
//            return this.winLog.length() != o.winLog.length() ? Integer.compare(this.winLog.length(), o.winLog.length()) : Integer.compare(this.loseLog.length(), o.loseLog.length());
            return this.winner != o.winner ? Integer.compare(this.winner, o.winner) : Integer.compare(this.cnt, o.cnt);
        }

        public String toString() {
            return winner + "/" + cnt;
        }

    }
}
