package 삼성기출문제.코디빵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, Q, mx, my;
    static TreeSet<Info> infos;
    static TreeMap<Integer, Pos> moves;
    static TreeSet<Pos> house;
    static PriorityQueue<Integer> breads;

    static PriorityQueue<Pos> moneys;

    static int[] ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            init();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            ans = new int[N + 1];
            for (int n = 1; n <= N; n++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                Pos p = new Pos(n, x, y, m);
                house.add(p.clonePos());
                moneys.add(p.clonePos());
            }

            for (int q = 0; q < Q; q++) {
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                if (type == 1) {
                    infos.add(new Info(type, time, k));
                } else {
                    int m = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    infos.add(new Info(type, time, k, m, x, y));
                }
            }
            simul();
            sb.append("#").append(t);
            for (int n = 1; n <= N; n++) {
                sb.append(" " + ans[n]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void setMover(int t) {
        // 구매하러 이동 중인 사람 중 t보다 빠르게 도착하면 모두 정렬
        while (!moves.isEmpty() && moves.firstEntry().getKey() <= t) {
            Map.Entry<Integer, Pos> e = moves.pollFirstEntry();
            moneys.add(e.getValue().clonePos());
            house.add(e.getValue().clonePos());
        }

    }

    static void goStore(int time) {
        if (house.isEmpty())
            return;

        Pos human = house.pollFirst();
        moneys.remove(human);
        int k = breads.poll();
        ans[human.order] += k;
        moves.put(time + human.calcTime(0, 0) * 2, human);
        System.out.println("편의점  " + human.order + " : buy " + k + ", time : " + time);
    }

    static void goBuy(int time, Info event) {
        if (house.isEmpty())
            return;

        if (!breads.isEmpty()) {
            goStore(time);
            return;
        }

        int money = event.m;
        if (moneys.peek().m < money)
            return;

        Pos human = moneys.poll();
        house.remove(human);
        human.m -= money;

        int x = event.x;
        int y = event.y;
        moves.put(time + human.calcTime(x, y) * 2, human);
        ans[human.order] += event.k;
        System.out.println("당근 " + human.order + " : buy " + event.k + ", time : " + time);
    }

    static void simul() {
        // 이벤트가 남아 있다면 전부 수행
        int time = 0;
        while (!infos.isEmpty()) {
            Info event = infos.pollFirst();
            setMover(event.t);
            time = event.t;

            if (event.type == 1) {
                breads.add(event.k);
                goStore(time);
            } else {
                goBuy(time, event);
            }

        }

        // 이동중인 사람이 있다면 전부 이동
//        while (!moves.isEmpty()) {
//
//        }
    }

    static void init() {
        infos = new TreeSet<>();
        breads = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1, o2));
        moves = new TreeMap<>();
        house = new TreeSet<>((o1, o2) ->
                o1.x + o1.y != o2.x + o2.y ?
                        Integer.compare(o1.x + o1.y, o2.x + o2.y) : o1.x != o2.x ?
                        Integer.compare(o1.x, o2.x) : Integer.compare(o1.y, o2.y)
        );
        moneys = new PriorityQueue<>((o1, o2) -> o1.m != o2.m ?
                Integer.compare(o1.m, o2.m) : Integer.compare(o1.order, o2.order)
        );
    }

    static class Pos {
        int order, x, y, m;

        public Pos(int order, int x, int y, int m) {
            this.order = order;
            this.x = x;
            this.y = y;
            this.m = m;
        }

        public Pos clonePos() {
            return new Pos(this.order, this.x, this.y, this.m);
        }

        public int calcTime(int x, int y) {
            return Math.abs(this.x - x) + Math.abs(this.y - y);
        }

    }

    static class Info implements Comparable<Info> {
        int type, t, k, m, x, y;

        public Info(int type, int t, int k) {
            this.type = type;
            this.t = t;
            this.k = k;
        }

        public Info(int type, int t, int k, int m, int x, int y) {
            this.type = type;
            this.t = t;
            this.k = k;
            this.m = m;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Info o) {
            return Integer.compare(this.t, o.t);
        }

    }
}
