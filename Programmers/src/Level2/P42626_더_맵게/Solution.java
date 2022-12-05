package Level2.P42626_더_맵게;


import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        Solution tmp = new Solution();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        System.out.println(tmp.solution(scoville, k));

    }

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville)
            pq.add(s);

        int answer = 0;
        while(pq.peek() < K && pq.size() > 1){
            pq.add(pq.poll() + pq.poll()*2);
            ++answer;
        }
        if(pq.peek() < K)
            return -1;
        else
            return answer;
    }
}
