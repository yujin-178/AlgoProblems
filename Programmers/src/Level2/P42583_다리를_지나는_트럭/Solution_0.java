package Level2.P42583_다리를_지나는_트럭;

import java.util.LinkedList;

public class Solution_0 {

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

    static LinkedList<Truck> bridgeOn;

    static public int solution(int bridge_length, int weight, int[] truck_weights) {
        int len = truck_weights.length;
        int cnt = 0;
        int idx = 0;
        int time = 0;
        int bridge_weight = 0;
        bridgeOn = new LinkedList<Truck>();
        while (cnt != len) {
            if (idx < len && bridge_weight + truck_weights[idx] <= weight) {
                time++;
                System.out.println("in time : " + time);
                bridge_weight += truck_weights[idx];
                bridgeOn.add(new Truck(truck_weights[idx++], time));
            } else {
                Truck tmp = bridgeOn.poll();
                System.out.println("out time : " + (tmp.t + bridge_length));
                //time = Math.max(tmp.t + bridge_length,time);
                time = tmp.t + bridge_length;
                bridge_weight -= tmp.w;
                cnt++;
                // 나가는 순간 다리에 여유가 있으면 새로운 차가 들어올 수 있다.
                if (idx < len && bridge_weight + truck_weights[idx] <= weight) {
                    if (!bridgeOn.isEmpty() && time <= bridgeOn.get(bridgeOn.size() - 1).t)
                        time = bridgeOn.get(bridgeOn.size() - 1).t + 1;
                    System.out.println("in time : " + time);
                    bridge_weight += truck_weights[idx];
                    bridgeOn.add(new Truck(truck_weights[idx++], time));
                }
            }
        }

        return time;
    }

    static class Truck {
        int w, t;

        public Truck(int w, int t) {
            this.w = w;
            this.t = t;
        }
    }
}
