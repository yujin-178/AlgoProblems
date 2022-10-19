package Level2.P42583_다리를_지나는_트럭;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_1 {
    public static void main(String[] args) {
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = new int[]{7, 4, 5, 6};
//        int bridge_length = 100;
//        int weight = 10;
//        int[] truck_weights = new int[]{10};
//        int bridge_length = 100;
//        int weight = 100;
//        int[] truck_weights = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        System.out.println(solution(bridge_length, weight, truck_weights));

    }

    static int sumWeight, len, limitWeight, idx, time, total, onCnt;
    static int[] weights;
    static Queue<Truck> bridge;

    static public int solution(int bridge_length, int weight, int[] truck_weights) {
        time = 1;
        idx = 0;
        total = truck_weights.length;
        limitWeight = weight;
        weights = truck_weights;
        len = bridge_length;
        bridge = new LinkedList<>();
        while (idx < total || !bridge.isEmpty()) {
            simul();
        }
        return time;
    }

    static public void simul() {
        // 트럭이 다리에 올라갈 수 있다면 올려보낸다.
        if (idx < total && onCnt < len && sumWeight + weights[idx] <= limitWeight) {
            onCnt++;
            sumWeight += weights[idx];
            bridge.add(new Truck(time, weights[idx]));
//            System.out.println(weights[idx] + ", in : " + time);
            time++;
            idx++;
        }


        // 트럭이 계속 다리에 올라올 수 있다면 나가는 행위는 생략한다.
        if (idx < total && onCnt < len && sumWeight + weights[idx] <= limitWeight) return;

        // 트럭이 나간다.
        Truck outTruck = bridge.poll();
        onCnt--;
        time = time < outTruck.t + len ? outTruck.t + len : time;
        sumWeight -= outTruck.w;

//        System.out.println(outTruck.w + ", out : " + time);

    }

    static class Truck {
        int t, w; // 다리 진입 시간, 무게

        public Truck(int t, int w) {
            this.t = t;
            this.w = w;
        }
    }
}
