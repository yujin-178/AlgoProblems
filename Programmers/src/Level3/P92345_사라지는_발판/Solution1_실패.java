package Level3.P92345_사라지는_발판;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solution1_실패 {
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

        p1Move("", "", 0);
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

    static void p1Move(String log1, String log2, int dep) {
//        viewTest();
        if (map[p1.r][p1.c] == 0) {
            resultInfo[1].add(new ResultInfo(new Player(p2.r, p2.c), new Player(p1.r, p1.c), log2, log1, dep));
            pq.add(new ResultInfo(new Player(p2.r, p2.c), new Player(p1.r, p1.c), log2, log1, dep));
        }
        boolean flag = true;
        int r = p1.r;
        int c = p1.c;
        map[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (chkOut(nr, nc)) continue;
            flag = false;
            p1.r = nr;
            p1.c = nc;

            p2Move(log1 + d, log2, dep + 1);
        }
        map[r][c] = 1;
        p1.r = r;
        p1.c = c;
        if (flag) {
            resultInfo[1].add(new ResultInfo(new Player(p2.r, p2.c), new Player(p1.r, p1.c), log2, log1, dep));
            pq.add(new ResultInfo(new Player(p2.r, p2.c), new Player(p1.r, p1.c), log2, log1, dep));
        }
    }

    static void p2Move(String log1, String log2, int dep) {
//        viewTest();
        if (map[p2.r][p2.c] == 0) {
            resultInfo[0].add(new ResultInfo(new Player(p1.r, p1.c), new Player(p2.r, p2.c), log1, log2, dep));
            pq.add(new ResultInfo(new Player(p1.r, p1.c), new Player(p2.r, p2.c), log1, log2, dep));
        }
        boolean flag = true;
        int r = p2.r;
        int c = p2.c;
        map[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (chkOut(nr, nc)) continue;

            flag = false;
            p2.r = nr;
            p2.c = nc;

            p1Move(log1, log2 + d, dep+1);
        }
        map[r][c] = 1;
        p2.r = r;
        p2.c = c;
        if (flag) {
            resultInfo[0].add(new ResultInfo(new Player(p1.r, p1.c), new Player(p2.r, p2.c), log1, log2, dep));
            pq.add(new ResultInfo(new Player(p1.r, p1.c), new Player(p2.r, p2.c), log1, log2, dep));
        }
    }

    static boolean dontMove(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (chkOut(nr, nc)) continue;
            return false;
        }
        return true;
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
        Player winner, loser;
        String winLog, loseLog;
        int cnt;

        public ResultInfo(Player winner, Player loser, String winLog, String loseLog, int cnt) {
            this.winner = winner;
            this.loser = loser;
            this.winLog = winLog;
            this.loseLog = loseLog;
            this.cnt = cnt;

        }


        @Override
        public int compareTo(ResultInfo o) {
//            return Integer.compare(this.winLog.length() + this.loseLog.length(), o.winLog.length() + o.loseLog.length());
            return this.winLog.length() != o.winLog.length() ? Integer.compare(this.winLog.length(), o.winLog.length()) : Integer.compare(this.loseLog.length(), o.loseLog.length());
        }

        public String toString() {
            return winLog + "/" + loseLog + "/" + cnt;
        }

    }
}
