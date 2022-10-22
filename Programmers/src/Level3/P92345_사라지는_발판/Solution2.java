package Level3.P92345_사라지는_발판;

public class Solution2 {

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

    static int R, C;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static public int solution(int[][] board, int[] aloc, int[] bloc) {
        R = board.length;
        C = board[0].length;
        Player p1 = new Player(aloc[0], aloc[1]);
        Player p2 = new Player(bloc[0], bloc[1]);
        map = board;
        ResultInfo ans = p1Move(0, p1, p2);

        return ans.cnt;
    }

    static ResultInfo p1Move(int dep, Player p1, Player p2) {

        int maxNum = 0;
        int minNum = Integer.MAX_VALUE;
        boolean playerStatus = false;

        // 발판이 없다면 패배 및 0
        if (map[p1.r][p1.c] == 0)
            return new ResultInfo(false, 0);

        map[p1.r][p1.c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = p1.r + dr[d];
            int nc = p1.c + dc[d];
            if (chkOut(nr, nc)) continue;


            ResultInfo tmp = p2Move(dep + 1, new Player(nr, nc), p2);
            playerStatus |= !tmp.winner;
            if (playerStatus) {
                minNum = Math.min(minNum, tmp.cnt + 1);
            }
            else {
                maxNum = Math.max(maxNum, tmp.cnt + 1);
            }
//            System.out.println("p1 : " +tmp);

        }
        map[p1.r][p1.c] = 1;
//        viewTest(p1, p2, minNum, maxNum, dep+1);
        return new ResultInfo(playerStatus, playerStatus ? minNum : maxNum);
    }

    static ResultInfo p2Move(int dep, Player p1, Player p2) {

        int maxNum = 0;
        int minNum = Integer.MAX_VALUE;
        boolean playerStatus = false;

        // 발판이 없다면 패배 및 0
        if (map[p2.r][p2.c] == 0)
            return new ResultInfo(false, 0);

        map[p2.r][p2.c] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = p2.r + dr[d];
            int nc = p2.c + dc[d];
            if (chkOut(nr, nc)) continue;


            ResultInfo tmp = p1Move(dep + 1, p1, new Player(nr, nc));
            playerStatus |= !tmp.winner;

            if (playerStatus) {
                minNum = Math.min(minNum, tmp.cnt + 1);
            }
            else {
                maxNum = Math.max(maxNum, tmp.cnt + 1);
            }
//            System.out.println("p2 : " +tmp);

        }
        map[p2.r][p2.c] = 1;
//        viewTest(p1, p2, minNum, maxNum, dep+1);
        return new ResultInfo(playerStatus, playerStatus ? minNum : maxNum);
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

    static class ResultInfo {
        boolean winner; // a가 0, b가 1
        int cnt;

        public ResultInfo(boolean winner, int cnt) {
            this.winner = winner;
            this.cnt = cnt;
        }

        public String toString() {
            return winner + "/" + cnt;
        }
    }

    static void viewTest(Player p1, Player p2, int min, int max, int dep) {
        System.out.println();
        System.out.println(dep + ":" + min + "," + max);
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
}
