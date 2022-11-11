package 삼성기출문제.예술성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, group;
    static boolean[][] chk;
    static HashMap<Integer, Group> groupInfo;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        System.out.println(simul());
    }


    static int simul() {
        int score = 0;
        // 그룹 결정 bfs && 그룹 정보 저장
        groupInfo = new HashMap<>();
        chk = new boolean[N][N];
        group = new int[N][N];
        int idx = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (chk[r][c]) continue;
                setGroup(r, c, ++idx);
            }
        }
        // 인접한 그룹 확인 및 예술성 점수 기록
        int tmp = 0;
        chk = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (chk[r][c]) continue;
                tmp += chkAnother(r, c, group[r][c]);
            }
        }
        score += tmp / 2;

        for (int i = 0; i < 3; i++) {
            // 메인 십자 회전

            // 4분할 사각형 회전

            // 그룹 결정 bfs && 그룹 정보 저장
            groupInfo = new HashMap<>();
            chk = new boolean[N][N];
            group = new int[N][N];
            idx = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (chk[r][c]) continue;
                    setGroup(r, c, ++idx);
                }
            }
            // 인접한 그룹 확인 및 예술성 점수 기록
            tmp = 0;
            chk = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (chk[r][c]) continue;
                    tmp += chkAnother(r, c, group[r][c]);
                }
            }
            score += tmp / 2;

        }
        return score;
    }

    static void setGroup(int r, int c, int idx) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r, c));
        chk[r][c] = true;
        group[r][c] = idx;
        int cnt = 0;
        while (!q.isEmpty()) {
            Pos tmp = q.poll();
            cnt++;
            for (int d = 0; d < 4; d++) {
                int nr = tmp.r + dr[d];
                int nc = tmp.c + dc[d];

                if (chkOut(nr, nc) || chk[nr][nc] || map[nr][nc] != map[tmp.r][tmp.c]) continue;

                q.add(new Pos(nr, nc));
                chk[nr][nc] = true;
                group[nr][nc] = idx;

            }
        }

        groupInfo.put(idx, new Group(map[r][c], cnt));

    }

    static boolean chkOut(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N) return true;
        return false;
    }

    static int chkAnother(int r, int c, int type) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r, c));
        boolean[][] localChk = new boolean[N][N];
        localChk[r][c] = true;
        chk[r][c] = true;
        while (!q.isEmpty()) {
            Pos tmp = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = tmp.r + dr[d];
                int nc = tmp.c + dc[d];

                if (chkOut(nr, nc) || localChk[nr][nc]) continue;

                if (group[nr][nc] == type) {
                    // 같은 경우
                    chk[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                    localChk[nr][nc] = true;
                } else {
                    // 다른 경우
                    if (cntMap.containsKey(group[nr][nc])) {
                        cntMap.put(group[nr][nc], cntMap.get(group[nr][nc]) + 1);
                    } else {
                        cntMap.put(group[nr][nc], 1);
                    }
                }
            }
        }
        int nowIdx = groupInfo.get(type).num;
        int nowCnt = groupInfo.get(type).cnt;
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : cntMap.entrySet()) {
            int otherIdx = groupInfo.get(e.getKey()).num;
            int otherCnt = groupInfo.get(e.getKey()).cnt;
            int side = e.getValue();

            sum += (nowCnt + otherCnt) * nowIdx * otherIdx * side;
        }
        return sum;

    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Group {
        int num, cnt;

        public Group(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
