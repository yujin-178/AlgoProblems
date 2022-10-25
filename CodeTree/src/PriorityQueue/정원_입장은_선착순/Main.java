package PriorityQueue.정원_입장은_선착순;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static PriorityQueue<Person> queue, order;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.arrival, o2.arrival));
        order = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.idx, o2.idx)));

        N = Integer.parseInt(st.nextToken());
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine());
            queue.add(new Person(n, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int time = 0;
        int maxStay = 0;
        Queue<Person> garden = new LinkedList<>();
        while (!(queue.isEmpty() && order.isEmpty())) {
            // 만약 대기 인원이 없다면 다음 사람이 도착한 시점으로 셋팅
            if (order.isEmpty() && !queue.isEmpty()) {
                order.add(queue.poll());
                time = Math.max(order.peek().arrival, time);
            }

            // 현재 시간보다 먼저 도착한 인원이 있다면 전부 대기열에 삽입
            while (!queue.isEmpty() && time > queue.peek().arrival) {
                order.add(queue.poll());
            }

            // 가든이 비어 있다면 사람을 넣었다가 빼버리면서 시간 업데이트
            if (garden.isEmpty() && !order.isEmpty()) {
                int now = time;
                garden.add(order.poll());
                Person tmp = garden.poll();
                time = now + tmp.stay;
                maxStay = Math.max(maxStay, now - tmp.arrival);
//                System.out.println(time +", " + maxStay);
            }

        }

        System.out.println(maxStay);


    }

    static class Person {
        int idx, arrival, stay;

        public Person(int idx, int arrival, int stay) {
            this.idx = idx;
            this.arrival = arrival;
            this.stay = stay;
        }
    }
}
