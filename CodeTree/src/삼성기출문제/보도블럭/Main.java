package 삼성기출문제.보도블럭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;
    static boolean[][] chkRow, chkCol;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);

        int cnt = simulRow();
        cnt += simulCol();

        System.out.println(cnt);
    }

    static boolean outMap(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N) return true;
        return false;
    }

    static boolean buildCol(int r, int c) {

        if (map[r][c] > map[r][c + 1]) {
            // 아래로 경사로룰 설치해야 하는 경우
            int h = map[r][c + 1];
            for (int j = c + 1; j <= c + L; j++) {
                if (outMap(r, j) || map[r][j] != h || chkCol[r][j])
                    return false;
            }
            for (int j = c + 1; j <= c + L; j++) {
                chkCol[r][j] = true;
            }
        } else {
            // 위로 경사로를 설치해야 하는 경우
            int h = map[r][c];
            for (int j = c; j > c - L; j--) {
                if (outMap(r, j) || map[r][j] != h || chkCol[r][j])
                    return false;
            }
            for (int j = c; j > c - L; j--) {
                chkCol[r][j] = true;
            }
        }

        return true;
    }

    static boolean buildRow(int r, int c) {

        if (map[r][c] > map[r + 1][c]) {
            // 아래로 경사로룰 설치해야 하는 경우
            int h = map[r + 1][c];
            for (int i = r + 1; i <= r + L; i++) {
                if (outMap(i, c) || map[i][c] != h || chkRow[i][c])
                    return false;
            }
            for (int i = r + 1; i <= r + L; i++) {
                chkRow[i][c] = true;
            }
        } else {
            // 위로 경사로를 설치해야 하는 경우
            int h = map[r][c];
            for (int i = r; i > r - L; i--) {
                if (outMap(i, c) || map[i][c] != h || chkRow[i][c])
                    return false;
            }
            for (int i = r; i > r - L; i--) {
                chkRow[i][c] = true;
            }
        }

        return true;
    }

    static int simulRow() {
        int cnt = 0;

        for (int c = 0; c < N; c++) {
            boolean flag = true;
            for (int r = 0; r < N - 1; r++) {
                if (!flag) break;
                if (map[r][c] == map[r + 1][c]) continue;
                if (Math.abs(map[r][c] - map[r + 1][c]) == 1) {
                    if (buildRow(r, c)) {
                        r += L - 1;
                    } else {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }

            }
            if (flag) cnt++;
        }

        return cnt;
    }

    static int simulCol() {
        int cnt = 0;

        for (int r = 0; r < N; r++) {
            boolean flag = true;
            for (int c = 0; c < N - 1; c++) {
                if (!flag) break;
                if (map[r][c] == map[r][c + 1]) continue;
                if (Math.abs(map[r][c] - map[r][c + 1]) == 1) {
                    if (buildCol(r, c)) {
                        c += L - 1;
                    } else {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }

            }
            if (flag) cnt++;
        }

        return cnt;
    }

//    static void mapRotate() {
//        for (int r = 0; r < N; r++) {
//            for (int c = 0; c < N; c++) {
//                map2[r][c] = map[N - 1 - c][r];
//            }
//        }
//    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chkCol = new boolean[N][N];
        chkRow = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
